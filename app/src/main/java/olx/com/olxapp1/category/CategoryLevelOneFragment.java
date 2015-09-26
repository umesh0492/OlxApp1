package olx.com.olxapp1.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import olx.com.olxapp1.R;

public class CategoryLevelOneFragment extends Fragment {

    private CategoryActivity mActivity;
    private ViewGroup mRootView;
    private SuperRecyclerView mRecyclerView_categories;
    CategoryListAdapterLevelOne categoryListAdapterLevelOne;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (CategoryActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(
                R.layout.layout_super_recyclerview, container, false);

        initListView();

        return mRootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initListView() {

        mRecyclerView_categories = (SuperRecyclerView) mRootView.findViewById(R.id.recycler_list);
        mRecyclerView_categories.setLayoutManager( new LinearLayoutManager(mActivity));

        populateViewList();

    }

    private void populateViewList() {

        categoryListAdapterLevelOne = new CategoryListAdapterLevelOne(mActivity);
        mRecyclerView_categories.setAdapter(categoryListAdapterLevelOne);

    }
}
