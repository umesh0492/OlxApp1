package olx.com.olxapp1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.desmond.squarecamera.CameraActivity;
import com.rey.material.ui.TypefaceTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by umesh0492 on 26/09/15.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ItemViewHolder> {


    private static final int REQUEST_CAMERA = 0;

    List<Uri> imageUrl = new ArrayList<>();

    MainActivity activity;


    public ImageListAdapter(Activity activityContext) {

        this.activity = (MainActivity) activityContext;

    }

    public void addImage(Uri photo_uri){

        imageUrl.add(photo_uri);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageUrl.size()+1;
    }


    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        Log.d("position checking", position + "");

        if(position==0){
            holder.image.setVisibility(View.GONE);
        }
        else {
            holder.image.setVisibility(View.VISIBLE);

        }

        holder.add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCustomCameraIntent = new Intent(activity, CameraActivity.class);
                activity.startActivityForResult(startCustomCameraIntent, REQUEST_CAMERA);

            }
        });
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view based on type.
        View itemLayoutView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.image_adapter, null);

        return new ItemViewHolder(itemLayoutView);
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TypefaceTextView add_image;
        public View view;

        public ItemViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            add_image = (TypefaceTextView) itemLayoutView.findViewById(R.id.add_image);

            image = (ImageView) itemLayoutView.findViewById(R.id.image);

            view = itemLayoutView;
        }

    }


}
