package olx.com.olxapp1.category;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rey.material.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import olx.com.olxapp1.R;

/**
 * Created by umesh0492 on 26/09/15.
 */
public class CategoryListAdapterLevelOne extends RecyclerView.Adapter<CategoryListAdapterLevelOne.ItemViewHolder> {
    List<String> category = new ArrayList<>();

    Activity activityContext;
    CategoryActivity activity;

    public CategoryListAdapterLevelOne(Activity activityContext) {

        this.activityContext = activityContext;
        this.activity = (CategoryActivity) activityContext;

        category.add("Mobile");
        category.add("Cars");
        category.add("Electronics");
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        Log.d("position checking", position + "");

        holder.category_text.setText(category.get(position));

        String category_lev_des = "";

        for (String category_lev : activity.category.get(0) ){

            category_lev_des.concat( category_lev + ",");
        }

        holder.category_description.setText(category_lev_des);

        holder.above.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CategoryLevelTwoFragment categoryLevelTwoFragment = new CategoryLevelTwoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("cat_id", category.get(position));
                categoryLevelTwoFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, categoryLevelTwoFragment ).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view based on type.
        View itemLayoutView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.category_adapter_level_one, null);

        Log.d("dd", "VIEW POPULATED FOR after product adapter");
        return new ItemViewHolder(itemLayoutView);
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView category_text,category_description;
        public LinearLayout above;
        public View view;

        public ItemViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            above = (LinearLayout) itemLayoutView.findViewById(R.id.above);
            category_text = (TextView) itemLayoutView.findViewById(R.id.category_text);
            category_description = (TextView) itemLayoutView.findViewById(R.id.category_description);

            view = itemLayoutView;
        }

    }
}
