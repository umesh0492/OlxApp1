package olx.com.olxapp1.category;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.rey.material.ui.TypefaceTextView;

import java.util.ArrayList;

import olx.com.olxapp1.CustomActivity;
import olx.com.olxapp1.R;

/**
 * Created by umesh0492 on 26/09/15.
 */
public class CategoryActivity extends CustomActivity {

    ArrayList<ArrayList<String>> category = new ArrayList<ArrayList<String>>(3);

    private Toolbar mToolbar;
    private TypefaceTextView tv_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_activity_with_fragment_with_action_bar);

        initActionbar();
        setTitle("Select Category");
        {
            ArrayList<String> categoryLev2 = new ArrayList<String>();
            categoryLev2.add("Windows");
            categoryLev2.add("Nokia");
            categoryLev2.add("Iphone");
            categoryLev2.add("BlackBerry");
            categoryLev2.add("Android");
            category.add(categoryLev2);
        }

        {
            ArrayList<String> categoryLev2 = new ArrayList<String>();
            categoryLev2.add("Fait");
            categoryLev2.add("Honda");
            categoryLev2.add("Maruti");
            categoryLev2.add("Hyundai");
            categoryLev2.add("Volkswagon");
            categoryLev2.add("Skoda");
            category.add(categoryLev2);
        }

        {
            ArrayList<String> categoryLev2 = new ArrayList<String>();
            categoryLev2.add("Air Conditioner");
            categoryLev2.add("TV");
            categoryLev2.add("Refrigator");
            categoryLev2.add("Camera");
            categoryLev2.add("Washing Machine");
            category.add(categoryLev2);
        }

        if(savedInstanceState!=null){
            return;
        }
        else {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment,new CategoryLevelOneFragment()).commit();
        }
    }

    private void initActionbar() {
        mToolbar = (Toolbar) findViewById(R.id.action_bar);
        tv_title = (TypefaceTextView) findViewById(R.id.actionbar_title);

        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onBackPressed();
                } catch (Exception e) {
                    Log.d("nav up", e.toString());
                }
            }
        });
    }

    void setTitle(String title){

        tv_title.setText(title);
    }

}
