package com.rey.material.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rey.material.demo.R;

/**
 * Created by umesh on 29/07/15.
 */
public class UiUtil {
    private static AlertDialog mProgressDialog;

    public static void animActivityChange(Activity act) {
        act.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public static void animOnBackPressed(Activity act) {
        act.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public static void showProgress(Activity activity, String message, int icon) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View progressLayout = activity.getLayoutInflater().inflate(R.layout.progress_icon_layout, null);
        ((TypefaceTextView) progressLayout.findViewById(R.id.progress_message)).setText(message);
        ((TypefaceTextView) progressLayout.findViewById(R.id.progress_icon)).setText(icon);
        builder.setView(progressLayout);
        builder.setCancelable(true);
        mProgressDialog = builder.show();
    }

    public static void showProgressStreamline(Activity activity, String message, int icon) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View progressLayout = activity.getLayoutInflater().inflate(R.layout.progress_icon_layout_streamline, null);
        ((TypefaceTextView) progressLayout.findViewById(R.id.progress_message)).setText(message);
        ((TypefaceTextView) progressLayout.findViewById(R.id.progress_icon)).setText(icon);
        builder.setView(progressLayout);
        builder.setCancelable(true);
        mProgressDialog = builder.show();
    }

    public static void showCustomProgressNoIcon(Activity activity, String message) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View progressLayout = activity.getLayoutInflater().inflate(R.layout.progress_icon_layout_streamline_no_progress_bar, null);
        ((TypefaceTextView) progressLayout.findViewById(R.id.progress_message)).setText(message);
        builder.setView(progressLayout);
        builder.setCancelable(true);
        mProgressDialog = builder.show();
    }

    public static void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public static void showInteractiveDialog(Activity activity, String message, int icon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View interactiveLayout = activity.getLayoutInflater().inflate(R.layout.no_results, null);
        interactiveLayout.setVisibility(View.VISIBLE);
        ((TypefaceTextView) interactiveLayout.findViewById(R.id.no_results_nearby_text)).setText(message);
        ((TypefaceTextView) interactiveLayout.findViewById(R.id.no_results_icon)).setText(icon);
        Log.d("UiUtil", "View is " + interactiveLayout);
        builder.setView(interactiveLayout);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                interactiveLayout.setVisibility(View.GONE);
                dialog.cancel();
            }
        });
        builder.show();
    }

    public static void showInteractiveDialogWithoutIcon(Activity activity, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View interactiveLayout = activity.getLayoutInflater().inflate(R.layout.no_results, null);
        interactiveLayout.setVisibility(View.VISIBLE);
        ((TypefaceTextView) interactiveLayout.findViewById(R.id.no_results_nearby_text)).setText(message);
        interactiveLayout.findViewById(R.id.no_results_icon).setVisibility(View.GONE);
        Log.d("UiUtil", "View is " + interactiveLayout);
        builder.setView(interactiveLayout);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                interactiveLayout.setVisibility(View.GONE);
                dialog.cancel();
            }
        });
        builder.show();
    }

    public static void showInteractiveDialogStreamline(Activity activity, String message, int icon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View interactiveLayout = activity.getLayoutInflater().inflate(R.layout.no_results_streamline, null);
        interactiveLayout.setVisibility(View.VISIBLE);
        ((TypefaceTextView) interactiveLayout.findViewById(R.id.no_results_nearby_text)).setText(message);
        ((TypefaceTextView) interactiveLayout.findViewById(R.id.no_results_icon)).setText(icon);
        Log.d("UiUtil", "View is " + interactiveLayout);
        builder.setView(interactiveLayout);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                interactiveLayout.setVisibility(View.GONE);
                dialog.cancel();
            }
        });
        builder.show();
    }

    public static void showUpdateAppDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View interactiveLayout = activity.getLayoutInflater().inflate(R.layout.no_results, null);
        interactiveLayout.setVisibility(View.VISIBLE);
        ((TypefaceTextView) interactiveLayout.findViewById(R.id.no_results_nearby_text)).setText("Kindly Update App");
        ((TypefaceTextView) interactiveLayout.findViewById(R.id.no_results_icon)).setText(R.string.fa_android);
        Log.d("UiUtil", "View is " + interactiveLayout);
        builder.setView(interactiveLayout);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity.getPackageName()));

                try {
                    activity.startActivity(goToMarket);
                    UiUtil.animActivityChange(activity);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(activity.getApplicationContext(), "Couldn't launch the market", Toast.LENGTH_SHORT).show();
                }
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                interactiveLayout.setVisibility(View.GONE);
                dialog.cancel();
            }
        });
        builder.show();
    }
}
