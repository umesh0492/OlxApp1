package olx.com.olxapp1.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class Preferences {

    private static final String PREF = "olx";
    private static final String PREF_PHONE_NUMBER = "PREF_PHONE_NUMBER";
    private static final String PREF_NAME = "PREF_NAME";
    private static final String PREF_EMAIL = "PREF_EMAIL";

    private static final String CURRENT_ADDRESS = "current_address";


    public static String getCurrentLocationAddress(Context context) {
        return getSharedPref(context).getString(CURRENT_ADDRESS, "");
    }

    public static void setCurrentLocationAddress(Context context, String loc) {
        Editor edit = getSharedPref(context).edit();
        edit.putString(CURRENT_ADDRESS, loc);
        edit.commit();
    }

    private static SharedPreferences getSharedPref(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static String getPhoneNumber(Context context) {
        return getSharedPref(context).getString(PREF_PHONE_NUMBER, "+91");
    }

    public static boolean hasPhoneNumber(Context context) {
        return !TextUtils.isEmpty(getPhoneNumber(context));
    }

    public static void setPhoneNumber(Context context, String number) {
        Editor edit = getSharedPref(context).edit();
        edit.putString(PREF_PHONE_NUMBER, number);
        edit.commit();
    }

    public static String getFullname(Context context) {
        return getSharedPref(context).getString(PREF_NAME, "");
    }

    public static void setFullname(Context context, String name) {
        Editor edit = getSharedPref(context).edit();
        edit.putString(PREF_NAME, name);
        edit.commit();
    }

    public static String getEmail(Context context) {
        return getSharedPref(context).getString(PREF_EMAIL, "");
    }

    public static void setEmail(Context context, String email) {
        Editor edit = getSharedPref(context).edit();
        edit.putString(PREF_EMAIL, email);
        edit.commit();
    }
}
