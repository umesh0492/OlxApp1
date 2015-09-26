package olx.com.olxapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.rey.material.ui.UiUtil;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import icepick.State;
import olx.com.olxapp1.category.CategoryActivity;
import olx.com.olxapp1.category.CategoryListAdapterLevelOne;
import olx.com.olxapp1.utils.MyLocation;
import olx.com.olxapp1.utils.Preferences;

public class FragmentPostAdd extends Fragment {

    EditText category,price,old,title,description,location,email,phone_no,user_name;
    Button submit_button;
    private  View root;

    private LocationManager lm;

    private SuperRecyclerView mRecyclerView_images;
    CategoryListAdapterLevelOne categoryListAdapterLevelOne;
    ImageListAdapter imageListAdapter;

//    private static final String SEARCH_LOCATION_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?key=AIzaSyA6dAr6XfXYV_E2HB6uyAxTJPvmGFOtIdA&input=%s&radius=100000&language=en&components=country:in";

    @State
    String s_category_1 = "",s_category_2 =  "", s_location = "", s_price = "", s_old = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_post_add, container, false);

        init();

        initImageListView();

        return root;
    }

    void init(){

        email = (EditText) root.findViewById(R.id.email);
        phone_no = (EditText) root.findViewById(R.id.phone_no);
        user_name = (EditText) root.findViewById(R.id.user_name);
        location = (EditText) root.findViewById(R.id.location);

        email.setText(Preferences.getEmail(getContext()));
        phone_no.setText(Preferences.getPhoneNumber(getContext()));
        user_name.setText(Preferences.getFullname(getContext()));
        location.setText(Preferences.getCurrentLocationAddress(getContext()));

        category = (EditText) root.findViewById(R.id.category);
        price = (EditText) root.findViewById(R.id.price);
        old = (EditText) root.findViewById(R.id.old);
        title = (EditText) root.findViewById(R.id.title);
        description = (EditText) root.findViewById(R.id.description);
        submit_button = (Button) root.findViewById(R.id.submit_button);

        price.setText("Rs.");

        //location.setClickable(true);
        category.setClickable(true);

        Selection.setSelection(price.getText(), price.getText().length());

        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().contains("Rs.")) {
                    price.setText("Rs.");
                    Selection.setSelection(price.getText(), price.getText().length());
                }
                s_price = price.getText().toString();

                updateTitleAndDes();

            }
        });

        old.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                s_old = old.getText().toString();
                updateTitleAndDes();
            }

        });

        root.findViewById(R.id.select_category).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(getActivity(), CategoryActivity.class), 981);
            }
        });


        root.findViewById(R.id.change_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UiUtil.showCustomProgressNoIcon(getActivity(), "Getting your current location..");
                seekLocation();

            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (price.getText().length() < 2) {
                    Toast.makeText(getContext(), "price should be greater than Rs.9", Toast.LENGTH_LONG).show();

                } else if (category.getText().length() < 1) {
                    Toast.makeText(getContext(), "Should select category", Toast.LENGTH_LONG).show();


                } else if (old.getText().length() < 1) {

                    Toast.makeText(getContext(), "Should enter product age", Toast.LENGTH_LONG).show();

                } else if (title.getText().length() < 10) {

                    Toast.makeText(getContext(), "Title should be greater than 10 letter", Toast.LENGTH_LONG).show();

                } else if (description.getText().length() < 10) {

                    Toast.makeText(getContext(), "description should be greater than 10 letter", Toast.LENGTH_LONG).show();


                } else if (location.getText().length() < 10) {
                    Toast.makeText(getContext(), "Should select location", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getContext(), "Advertisment posted Successfully", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        String cat[] ={"Mobile", "Cars", "Electronics"};

        if (resultCode != -1) return;

        if(requestCode==981){
            int cat_id = Integer.parseInt(data.getStringExtra("cat_lev_one"));

            s_category_1 = cat[cat_id];
            s_category_2 = data.getStringExtra("cat_lev_two");

            category.setText(s_category_1+" > "+s_category_2);

            updateTitleAndDes();
        }

        if (requestCode == 0) {
            Uri photoUri = data.getData();

            imageListAdapter.addImage(photoUri);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void seekLocation(){

        if (areLocationServicesEnabled()) {

            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                @Override
                public void gotLocation(Location loc){

                    if(location!=null){

                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        List<Address> addressList;
                        try {
                            addressList =  geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 10);

                            // Handle case where no address was found.
                            if (addressList == null || addressList.size()  == 0) {

                                Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
                                UiUtil.hideProgress();
                                //deliverResultToReceiver(Constants.FAILURE_RESULT, errorMessage);
                            } else {
                                Address address = addressList.get(0);
                                ArrayList<String> addressFragments = new ArrayList<String>();

                                // Fetch the address lines using getAddressLine,
                                // join them, and send them to the thread.
                                for(int i = 0; i < address.getMaxAddressLineIndex()-1; i++) {
                                    addressFragments.add(address.getAddressLine(i));
                                }


                                s_location = TextUtils.join(System.getProperty("line.separator"),
                                        addressFragments);
                                location.setText(s_location);

                                UiUtil.hideProgress();
                            }

                        } catch (IOException e) {
                            UiUtil.hideProgress();
                            e.printStackTrace();
                        }
                    }
                }
            };
            MyLocation myLocation = new MyLocation();
            myLocation.getLocation(getContext(), locationResult);
        }
        else {
            UiUtil.hideProgress();
        }
    }

    private boolean areLocationServicesEnabled() {
        lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean gpsAvailable = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean networkAvailable = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!gpsAvailable && !networkAvailable) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setCancelable(false);
            dialog.setMessage(getString(R.string.location_not_enabled));
            dialog.setPositiveButton(getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(myIntent, 111);
                    UiUtil.animActivityChange(getActivity());
                }
            });
            dialog.show();
            return false;
        } else {
            return true;
        }
    }

    void updateTitleAndDes(){

        title.setText("Selling " + s_category_2 + " product_name " + s_category_1 + " at " + s_price + ", " + s_old + " year old, in " + s_location);

        description.setText("Selling " + s_category_2 + " product_name " + s_category_1 + " at " + s_price + ", " + s_old + " year old, in " + s_location
                + ". Grab it ");
    }

    private void initImageListView() {

        mRecyclerView_images = (SuperRecyclerView) root.findViewById(R.id.recycler_list);
        mRecyclerView_images.setLayoutManager(new LinearLayoutManager(getActivity()));

        populateViewList();

    }

    private void populateViewList() {

        imageListAdapter = new ImageListAdapter(getActivity());
        mRecyclerView_images.setAdapter(imageListAdapter);

    }
}
