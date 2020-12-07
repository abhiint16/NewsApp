package com.sixteenmb.abhishekint.liberty;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.toolbox.NetworkImageView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

/**
 * Created by abhishek on 03-06-2017.
 */

public class Source_click extends AppCompatActivity {

    NewAdapter source_clicknewAdapter;
    Toolbar source_clicktoolbar;
    ViewPager source_clickviewPager;
    TabLayout source_clicktabLayout;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String idUrl;
    Source_click_image_data source_click_image_data;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @SuppressLint("WrongViewCast")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Window window = activity.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
       setContentView(R.layout.source_click);
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            this.getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_main_activity));
        }
*/
// finally change the color



        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.source_click_nested);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.source_click_collapsing);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setScrimAnimationDuration(1200);
        idUrl = getIntent().getStringExtra("id");
        scrollView.setFillViewport(true);
        source_clicktoolbar = (Toolbar) findViewById(R.id.source_click_toolbar);
        setSupportActionBar(source_clicktoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        source_clicktoolbar.setTitleTextColor(getResources().getColor(R.color.white));


        recyclerView = (RecyclerView) findViewById(R.id.source_click_recyclerview);
        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);


        source_clickviewPager = (ViewPager) findViewById(R.id.source_click_pager);
        source_clicknewAdapter = new NewAdapter(getSupportFragmentManager(), getIntent().getIntExtra("size", 1));
        source_clickviewPager.setAdapter(source_clicknewAdapter);

        source_clicktabLayout = (TabLayout) findViewById(R.id.source_click_tab);
        source_clicktabLayout.setTabTextColors(getResources().getColor(R.color.tabTextUnselected), getResources().getColor(R.color.white));
        source_clicktabLayout.setupWithViewPager(source_clickviewPager);
recyclerView.setAdapter(new GridViewAdapter());
        source_click_image_data = new Source_click_image_data(idUrl, recyclerView, new GridViewAdapter());
        source_click_image_data.getImageUrl();
    }

    public String getId() {
        return idUrl;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppController.getmInstance().getRequestQueue().cancelAll("source_click_image_data");
    }


    class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> {

        // int pos;
        @Override
        public GridViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_click_recycler_view_onlyimage_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final GridViewAdapter.ViewHolder holder, final int position) {


            holder.networkImageView.setImageUrl(source_click_image_data.list.get(position), source_click_image_data.imageLoader1);

           // holder.networkImageView.setImageUrl(new Sources().source_image_list.get(position),AppController.getmInstance().getmImageLoader());

            /*holder.networkImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog=new Dialog(getApplicationContext());
                    dialog.setContentView(R.layout.dialog_image);

                    NetworkImageView networkImageView1=(NetworkImageView)dialog.findViewById(R.id.dialog_network_image);
                    networkImageView1.setImageUrl(source_click_image_data.list.get(position),source_click_image_data.imageLoader1);
                }
            });
            *///pos=position;
        }

        @Override
        public int getItemCount() {
            return source_click_image_data.list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
            LinearLayout linearLayout;
            NetworkImageView networkImageView;

            public ViewHolder(View itemView) {
                super(itemView);
               networkImageView = (NetworkImageView) itemView.findViewById(R.id.source_click_grid_view_imageview);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.source_click_grid_view_linear);
                itemView.setOnTouchListener(this);

            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        view.setFitsSystemWindows(true);
                        view.setAlpha((float) 0.5);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.setAlpha((float) 1.0);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.setAlpha((float) 0.5);
                        break;
                }
                return true;
            }


        }
    }
}
