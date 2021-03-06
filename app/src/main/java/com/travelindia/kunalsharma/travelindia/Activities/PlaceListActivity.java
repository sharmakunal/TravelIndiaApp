package com.travelindia.kunalsharma.travelindia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import com.travelindia.kunalsharma.travelindia.Adapter.PlaceRecyclerViewAdapter;
import com.travelindia.kunalsharma.travelindia.DownloadStatus;
import com.travelindia.kunalsharma.travelindia.JsonParsing.GetPlaceJsonData;
import com.travelindia.kunalsharma.travelindia.PojoClasses.Category;
import com.travelindia.kunalsharma.travelindia.PojoClasses.Place;
import com.travelindia.kunalsharma.travelindia.R;
import com.travelindia.kunalsharma.travelindia.RecyclerItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class PlaceListActivity extends BaseActivity implements GetPlaceJsonData.OnDataAvailable,
        RecyclerItemClickListener.OnRecyclerClickListener {

    private static final String TAG = "PlaceList Activity";
    private PlaceRecyclerViewAdapter mPlaceRecyclerViewAdapter;
    private static String cat_id = "";
    private String type="PlaceOnBasisOfCategory";
    private android.widget.SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);
        activateToolbar(true);

        Intent intent = getIntent();
        Category cat = (Category) intent.getSerializableExtra(PHOTO_TRANSFER);
        int id = cat.getCatid();
        cat_id = String.valueOf(id);


        RecyclerView recyclerView;

        GetPlaceJsonData getplacejsondata = new GetPlaceJsonData(this,type,cat_id);
        getplacejsondata.execute();

        recyclerView = (RecyclerView)findViewById(R.id.recyler_place_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, this));

        mPlaceRecyclerViewAdapter = new PlaceRecyclerViewAdapter(this, new ArrayList<Place>());
        recyclerView.setAdapter(mPlaceRecyclerViewAdapter);

   }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public void onDataAvailable(List<Place> data, DownloadStatus status) {
        Log.d(TAG, "onDataAvailable: starts");
        if(status == DownloadStatus.OK) {
            mPlaceRecyclerViewAdapter.loadNewData(data);
        } else {
            // download or processing failed
            Log.e(TAG, "onDataAvailable failed with status " + status);
        }

        Log.d(TAG, "onDataAvailable: ends");
    }


    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "onItemClick: starts");
        Intent intent = new Intent(this, PlaceDetailActivity.class);
        intent.putExtra(PHOTO_TRANSFER,mPlaceRecyclerViewAdapter.getPhoto(position));
        startActivity(intent);
    }

}
