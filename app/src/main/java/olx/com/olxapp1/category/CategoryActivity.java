package olx.com.olxapp1.category;

import android.os.Bundle;
import android.os.PersistableBundle;
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

    ArrayList<ArrayList<String>> category = new ArrayList<ArrayList<String>>();

    private Toolbar mToolbar;
    private TypefaceTextView tv_title;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.blank_activity_with_fragment_with_action_bar);

        {
            ArrayList<String> categoryLev2 = new ArrayList<String>();
            category.set(0,categoryLev2).add("Windows");
            category.set(0,categoryLev2).add("Nokia");
            category.set(0,categoryLev2).add("Iphone");
            category.set(0,categoryLev2).add("BlackBerry");
            category.set(0,categoryLev2).add("Android");
        }

        {
            ArrayList<String> categoryLev2 = new ArrayList<String>();
            category.set(1,categoryLev2).add("Fait");
            category.set(1,categoryLev2).add("Honda");
            category.set(1,categoryLev2).add("Maruti");
            category.set(1,categoryLev2).add("Hyundai");
            category.set(1,categoryLev2).add("Volkswagon");
            category.set(1,categoryLev2).add("Skoda");
        }

        {
            ArrayList<String> categoryLev2 = new ArrayList<String>();
            category.set(2,categoryLev2).add("Air Conditioner");
            category.set(2,categoryLev2).add("TV");
            category.set(2,categoryLev2).add("Refrigator");
            category.set(2,categoryLev2).add("Camera");
            category.set(2,categoryLev2).add("Washing Machine");
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
