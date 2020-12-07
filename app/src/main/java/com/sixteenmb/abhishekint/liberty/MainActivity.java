package com.sixteenmb.abhishekint.liberty;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.toolbox.NetworkImageView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    MyAdapter myAdapter;
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Hori_Recycler_Get_Data hori_recycler_get_data;
    Hori_Recycler_Adapter hori_recycler_adapter=new Hori_Recycler_Adapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView powerby_btn;
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        viewPager = (ViewPager) findViewById(R.id.main_pager);
        myAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);

        tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setScrimAnimationDuration(1200);


        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        powerby_btn=(TextView)findViewById(R.id.powerby_btn);
        powerby_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://newsapi.org/"));
                startActivity(i);
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.main_recyclerview);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        hori_recycler_get_data=new Hori_Recycler_Get_Data(hori_recycler_adapter,recyclerView);
        Log.e("leaaaving for hori","leaving for hori");
        hori_recycler_get_data.getId();
       /* Log.e("leavig fro getfinaldata","leaving fro getfinaldata");
        hori_recycler_get_data.getFinalData();*/
        //networkImageView.setImageUrl("http://abhishekint.16mb.com/img.jpg",imageLoader);
       /* Glide.with(getBaseContext()).load("http://abhishekint.16mb.com/img.jpg")
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);*/
        navigationView = (NavigationView) findViewById(R.id.drawer_navview);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menu_feedback:
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        String[] s = {"abhishekint16@gmail.com"};
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, s);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Suggestion | Liberty");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                        emailIntent.setType("message/rfc822");//message/rfc822   text/plain
                        startActivity(Intent.createChooser(emailIntent, "Send EMAIL using...."));

                        break;
                    case R.id.menu_share:
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Stack");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
                        shareIntent.setType("text/plain");
                        startActivity(Intent.createChooser(shareIntent, "Complete action using ...."));
                        break;

                    case R.id.menu_star:
                        try {
                            Uri uri = Uri.parse("market://details?id=" + getPackageName());
                            Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent1);


                        } catch (ActivityNotFoundException e) {

                            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                            Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent1);

                        }
                        break;
                    case R.id.menu_send:
                Intent sendIntent = new Intent(MainActivity.this, Send_direct_msg.class);
                startActivity(sendIntent);
                break;


                }

                return false;
            }

        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_feedback:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                String[] s = {"abhishekint16@gmail.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, s);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Suggestion | "+ R.string.app_name);
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                emailIntent.setType("message/rfc822");//message/rfc822   text/plain
                startActivity(Intent.createChooser(emailIntent, "Send EMAIL using...."));

                break;
            case R.id.menu_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Humorous");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName()+"\n\nIn this biased world , You need to authenticate every news . LIBERTY provides you more than 50 top news sources to see this world .\n\nNO NEWS CAN GO UNTAPPED .");
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "Complete action using ...."));
                break;

            case R.id.menu_star:
                try {
                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent1);


                } catch (ActivityNotFoundException e) {

                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent1);

                }
                break;

            /*case R.id.menu_send:
                Intent sendIntent = new Intent(MainActivity.this, Send_direct_msg.class);
                startActivity(sendIntent);
                break;
            */
        }

        return true;

    }


    class Hori_Recycler_Adapter extends RecyclerView.Adapter<Hori_Recycler_Adapter.ViewHolder>
    {

        @Override
        public Hori_Recycler_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.e("hori_viewholder","hori_viewholder");
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hori_source_recycler_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(Hori_Recycler_Adapter.ViewHolder holder, int position) {

            Log.e("hori_bind","hori_bind");
            String s=hori_recycler_get_data.titleList.get(position);
            if(s.trim().length()>80)
                s= (String) (s.subSequence(0,70)+" ...");
            holder.textView.setText(s);

            holder.networkImageView.setImageUrl(hori_recycler_get_data.imageList.get(position),hori_recycler_get_data.imageLoader);
            //holder.networkImageView.setDefaultImageResId(new Sources().source_image_list.get(position));
            //recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            holder.source.setText(hori_recycler_get_data.sourceList.get(position));

        }

        @Override
        public int getItemCount() {
            return hori_recycler_get_data.titleList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            NetworkImageView networkImageView;
            TextView textView,source;
            public ViewHolder(View itemView) {

                super(itemView);
                Log.e("hori class holdr","cla");
                networkImageView=(NetworkImageView)itemView.findViewById(R.id.hori_item_image);
                textView=(TextView)itemView.findViewById(R.id.hori_item_title);
                source=(TextView)itemView.findViewById(R.id.hori_item_source);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int pos=getAdapterPosition();
                Intent intent=new Intent(getApplicationContext(),WebView_Activity.class);
                intent.putExtra("url",hori_recycler_get_data.urlList.get(pos));
                startActivity(intent);
            }
        }
    }
}