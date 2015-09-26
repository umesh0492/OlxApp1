package olx.com.olxapp1.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import olx.com.olxapp1.R;

/**
 * Created by umesh0492 on 26/09/15.
 */
public class CategoryLevelTwoFragment extends Fragment {

    private CategoryActivity mActivity;
    private ViewGroup mRootView;
    private SuperRecyclerView mRecyclerView_categories;
    CategoryListAdapterLevelTwo categoryListAdapterLevelTwo;

    String cat_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (CategoryActivity) getActivity();
        cat_id = getArguments().getString("cat_id");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(
                R.layout.layout_super_recyclerview, container, false);

        initListView();

        return mRootView;
    }


    private void initListView() {

        mRecyclerView_categories = (SuperRecyclerView) mRootView.findViewById(R.id.recycler_list);
        mRecyclerView_categories.setLayoutManager( new LinearLayoutManager(mActivity));

        populateViewList();
    }

    private void populateViewList() {

        categoryListAdapterLevelTwo = new CategoryListAdapterLevelTwo(mActivity,cat_id,mActivity.category.get(Integer.parseInt(cat_id)));
        mRecyclerView_categories.setAdapter(categoryListAdapterLevelTwo);

    }
}
