package olx.com.olxapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rey.material.ui.UiUtil;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import olx.com.olxapp1.utils.MyLocation;
import olx.com.olxapp1.utils.Preferences;

/**
 * Created by umesh0492 on 26/09/15.
 */
public class UserInfoFragment extends Fragment {

    EditText user_name,phone_no,email,location;
    Button submit_button;
    private View root;

    private LocationManager lm;

//    private static final String SEARCH_LOCATION_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?key=AIzaSyA6dAr6XfXYV_E2HB6uyAxTJPvmGFOtIdA&input=%s&radius=100000&language=en&components=country:in";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_user_info, container, false);

        init();

        return root;
    }

    void init(){

        user_name = (EditText) root.findViewById(R.id.user_name);
        phone_no = (EditText) root.findViewById(R.id.phone_no);
        email = (EditText) root.findViewById(R.id.email);
        location = (EditText) root.findViewById(R.id.location);
        submit_button = (Button) root.findViewById(R.id.submit_button);

        phone_no.setText("+91");

        Selection.setSelection(phone_no.getText(), phone_no.getText().length());

        phone_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().contains("Rs.")) {
                    phone_no.setText("Rs.");
                    Selection.setSelection(phone_no.getText(), phone_no.getText().length());
                }
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtil.showCustomProgressNoIcon(getActivity(), "Getting your current location..");

                seekLocation();
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_name.getText().length() < 2) {
                    Toast.makeText(getContext(), "username cant be empty", Toast.LENGTH_LONG).show();

                } else if (phone_no.getText().length() < 13) {
                    Toast.makeText(getContext(), "Phone no. should not be empty", Toast.LENGTH_LONG).show();


                } else if (email.getText().length() < 4) {

                    Toast.makeText(getContext(), "Email Should not be empty", Toast.LENGTH_LONG).show();

                } else if (location.getText().length() < 10) {
                    Toast.makeText(getContext(), "Should select location", Toast.LENGTH_LONG).show();
                }else{
                    Preferences.setEmail(getContext(), email.getText().toString());
                    Preferences.setFullname(getContext(), user_name.getText().toString());
                    Preferences.setPhoneNumber(getContext(), phone_no.getText().toString());
                    Preferences.setCurrentLocationAddress(getContext(), location.getText().toString());

                    Toast.makeText(getContext(), "User Info Saved  Successfully", Toast.LENGTH_LONG).show();

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new FragmentPostAdd()).commit();

                }
            }
        });

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

                                location.setText(TextUtils.join(System.getProperty("line.separator"),
                                        addressFragments));

                                UiUtil.hideProgress();
                            }

                        } catch (IOException e) {
                            UiUtil.hideProgress();
                            e.printStackTrace();
                        }
                    }
                }
            };
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
}
