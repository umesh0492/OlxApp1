package olx.com.olxapp1.category;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rey.material.ui.UiUtil;
import com.rey.material.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import icepick.State;
import olx.com.olxapp1.R;

/**
 * Created by umesh0492 on 26/09/15.
 */
public class CategoryListAdapterLevelTwo extends RecyclerView.Adapter<CategoryListAdapterLevelTwo.ItemViewHolder> {
    List<String> category_lev_two = new ArrayList<>();

    CategoryActivity activity;
    @State String cat_id;

    public CategoryListAdapterLevelTwo(Activity activityContext, String cat_id, ArrayList<String> category_lev_two) {

        this.activity = (CategoryActivity) activityContext;
        this.cat_id = cat_id;
        this.category_lev_two = category_lev_two;
    }

    @Override
    public int getItemCount() {
        return category_lev_two.size();
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        Log.d("position checking", position + "");

        holder.category_text.setText(category_lev_two.get(position));

        holder.above.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent data = new Intent();
                data.putExtra("cat_lev_one",cat_id);
                data.putExtra("cat_lev_two",category_lev_two.get(position));
                activity.setResult(Activity.RESULT_OK, data);
                activity.finish();

                UiUtil.animActivityChange(activity);
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

        public TextView category_text;
        public LinearLayout above;
        public View view;

        public ItemViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            above = (LinearLayout) itemLayoutView.findViewById(R.id.above);
            category_text = (TextView) itemLayoutView.findViewById(R.id.category_text);

            view = itemLayoutView;
        }

    }
}
