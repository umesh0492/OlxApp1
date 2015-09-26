package olx.com.olxapp1;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.rey.material.ui.TypefaceTextView;

import olx.com.olxapp1.utils.Preferences;

public class MainActivity extends CustomActivity {


    private Toolbar mToolbar;
    private TypefaceTextView tv_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_activity_with_fragment_with_action_bar);

        initActionbar();
        setTitle("OLX bech de !!");

        if(savedInstanceState!=null){
            return;
        }
        else if(Preferences.getFullname(getApplicationContext()).length()>0){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment,new FragmentPostAdd()).commit();

        }
        else
        {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment, new UserInfoFragment()).commit();
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
