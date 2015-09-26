package olx.com.olxapp1;

import android.os.Bundle;

import olx.com.olxapp1.utils.Preferences;

public class MainActivity extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            return;
        }
        else if(Preferences.getFullname(getApplicationContext()).length()>0){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment,new FragmentPostAdd()).commit();

        }
        else
            getSupportFragmentManager().beginTransaction().add(R.id.fragment, new UserInfoFragment()).commit();

    }
}
