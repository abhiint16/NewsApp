package com.sixteenmb.abhishekint.liberty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by abhishek on 03-06-2017.
 */

public class Top extends Fragment {

    ProgressDialog progressDialog;
    Top.Top_Adapter top_adapter=new Top.Top_Adapter();
    Top_Data top_data;
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

        registerForContextMenu(recyclerView);
        Source_click get=(Source_click)getActivity();
        String idForUrl=get.getId();
        top_data=new Top_Data(top_adapter,recyclerView,idForUrl);
        progressDialog =new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading ...");
        progressDialog.show();
        top_data.setItem();

        return view;
    }

    class Top_Adapter extends RecyclerView.Adapter<Top_Adapter.ViewHolder>
    {
        private int pos;

        @Override
        public Top_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.top_recycler_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(Top_Adapter.ViewHolder holder, final int position) {
                    holder.networkImageView.setImageUrl(top_data.urlImgList.get(position),top_data.imageLoader);
            holder.title.setText(top_data.titleList.get(position));
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    setPosition(position);
                    return false;
                }
            });
            progressDialog.cancel();
        }

        @Override
        public int getItemCount() {
            return top_data.urlImgList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener {
            TextView title ;
            NetworkImageView networkImageView;
            public ViewHolder(View itemView) {
                super(itemView);
                title=(TextView)itemView.findViewById(R.id.top_recycler_item_textView_title);
                networkImageView=(NetworkImageView)itemView.findViewById(R.id.top_recycler_item_textView_image);

                itemView.setOnClickListener(this);
                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onClick(View view) {
                int pos=getAdapterPosition();
                Intent intent=new Intent(getActivity(),WebView_Activity.class);
                intent.putExtra("url",top_data.urlArticle.get(pos));
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
            shareIntent.putExtra(Intent.EXTRA_TEXT,top_data.urlArticle.get(top_adapter.getPosition())+"\n\nIn this biased world , You need to authenticate every news . LIBERTY provides you more than 50 top news sources to see this world .\n\nNO NEWS CAN GO UNTAPPED .\n\n"+"https://play.google.com/store/apps/details?id="+getActivity().getPackageName());
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent,"Complete action using ...."));
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppController.getmInstance().getRequestQueue().cancelAll("top_data");
    }
}
