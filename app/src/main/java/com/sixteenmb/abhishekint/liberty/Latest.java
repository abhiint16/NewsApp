package com.sixteenmb.abhishekint.liberty;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by abhishek on 03-06-2017.
 */

public class Latest extends Fragment {

    Latest.Latest_Adapter latest_adapter=new Latest.Latest_Adapter();
    Latest_Data latest_data;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.top,container,false);


        recyclerView=(RecyclerView)view.findViewById(R.id.top_recyclerView);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        Source_click get=(Source_click)getActivity();
        String idForUrl=get.getId();
        latest_data=new Latest_Data(latest_adapter,recyclerView,idForUrl);
        latest_data.setItem();
        return view;
    }

    class Latest_Adapter extends RecyclerView.Adapter<Latest_Adapter.ViewHolder>
    {
        private int pos;
        @Override
        public Latest_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_recycler_item,parent,false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final Latest_Adapter.ViewHolder holder, int position) {
            //holder.networkImageView.setLayoutParams(new LinearLayout.LayoutParams(400,400));
            holder.networkImageView.setImageUrl(latest_data.urlImgList.get(position),latest_data.imageLoader);
            holder.title.setText(latest_data.titleList.get(position));
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    setPosition(holder.getAdapterPosition());
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return latest_data.urlImgList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
            TextView title ;
            NetworkImageView networkImageView;
            public ViewHolder(View itemView) {
                super(itemView);

                title=(TextView)itemView.findViewById(R.id.latest_recycler_item_textView_title);
                networkImageView=(NetworkImageView)itemView.findViewById(R.id.latest_recycler_item_textView_image);

                itemView.setOnClickListener(this);
                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onClick(View view) {
                int pos=getAdapterPosition();
                Intent intent=new Intent(getActivity(),WebView_Activity.class);
                intent.putExtra("url",latest_data.urlArticle.get(pos));

                startActivity(intent);

            }

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0, 25, 0, "Share");
            }


        }

        public int getPosition() {
            return pos;
        }

        public void setPosition(int position) {
            this.pos = position;
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==25)
        {

            Intent shareIntent=new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,"News");
            shareIntent.putExtra(Intent.EXTRA_TEXT,latest_data.urlArticle.get(latest_adapter.getPosition())+"\n\nIn this biased world , You need to authenticate every news . LIBERTY provides you more than 50 top news sources to see this world .\n\nNO NEWS CAN GO UNTAPPED .\n\n"+"https://play.google.com/store/apps/details?id="+getActivity().getPackageName());
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent,"Complete action using ...."));
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppController.getmInstance().getRequestQueue().cancelAll("latest");
    }
}
