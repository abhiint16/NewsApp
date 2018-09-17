package com.sixteenmb.abhishekint.liberty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;


/**
 * Created by abhishek on 03-06-2017.
 */

public class Sources extends Fragment {

    ProgressDialog progressDialog;
    Sources_Adapter sources_adapter=new Sources_Adapter();
    Sources_Data sources_data;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.sources,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.sources_recycler);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        sources_data=new Sources_Data(sources_adapter,recyclerView);
        progressDialog =new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading ...");
        progressDialog.show();
        //Log.e("diaolog start","start");
        sources_data.getIcon();
        sources_data.setItem();

        return view;
    }



    public class Sources_Adapter extends  RecyclerView.Adapter<Sources_Adapter.ViewHolder>{
       /* List<Search_Get_Set> search_get_setList=new ArrayList<>();

        Sources_Adapter(List<Search_Get_Set> list)
        {
            search_get_setList
        }*/
       /*RecyclerView.LayoutManager layoutManager1;
       private  static  final int HEAD=0;
        private  static  final  int REST=1;
        Hori_Recycler_Get_Data hori_recycler_get_data;
        Hori_recycler_Adapter hori_recycler_adapter=new Hori_recycler_Adapter();
*/
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       //     Log.e("in create view holder","in create view holder");
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sources_recycler_item,parent,false);
                return new ViewHolder(view);




        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

//Log.e("in bind view holder","in bind");
                holder.name.setText(sources_data.nameList.get(position));
                holder.des.setText(sources_data.descriptionList.get(position));
                holder.conutry.setText(sources_data.countryList.get(position));
                holder.category.setText(sources_data.categoryList.get(position));
            holder.networkImageView.setImageUrl(sources_data.icon_link_list.get(position),AppController.getmInstance().getmImageLoader());
            holder.networkImageView.setDefaultImageResId(R.drawable.icon_back);
            holder.networkImageView.setErrorImageResId(R.drawable.icon_back);
       //     Log.e("befre cancelling dialog","cancelling dialog");
                progressDialog.cancel();



        }

        @Override
        public int getItemCount() {
            return sources_data.idList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


            TextView name,des,conutry,category;
            NetworkImageView networkImageView;
           // RecyclerView recyclerView_hori;
            public ViewHolder(View itemView) {
                super(itemView);

            //    Log.e("in viewholder class","veiw holder class");
                    name=(TextView)itemView.findViewById(R.id.sources_recycler_item_textView_name);
                    des=(TextView)itemView.findViewById(R.id.sources_recycler_item_textView_description);
                    category=(TextView)itemView.findViewById(R.id.sources_recycler_item_textView_category);
                    conutry=(TextView)itemView.findViewById(R.id.sources_recycler_item_textView_country);
                networkImageView=(NetworkImageView)itemView.findViewById(R.id.icon_image);

                    itemView.setOnClickListener(this);




              }

            @Override
            public void onClick(View view) {
                int position=getAdapterPosition();
                Intent intent=new Intent(getActivity(),Source_click.class);
                intent.putExtra("size",sources_data.sortByList.get(position).size());
                intent.putExtra("name",sources_data.nameList.get(position));
                intent.putExtra("id",sources_data.idList.get(position));
                startActivity(intent);
            }


        }


   /*     class Hori_recycler_Adapter extends RecyclerView.Adapter<Hori_recycler_Adapter.ViewHolder1>
        {

            @Override
            public Hori_recycler_Adapter.ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
                View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.hori_source_recycler_item,parent,false);
                return  new ViewHolder1(v);
            }

            @Override
            public void onBindViewHolder(Hori_recycler_Adapter.ViewHolder1 holder, int position) {

                holder.textView.setText(hori_recycler_get_data.titleList.get(position));
                holder.networkImageView.setImageUrl(hori_recycler_get_data.imageList.get(position),hori_recycler_get_data.imageLoader);
            }

            @Override
            public int getItemCount() {
                return hori_recycler_get_data.idList.size();
            }

            public class ViewHolder1 extends RecyclerView.ViewHolder {
                NetworkImageView networkImageView;
                TextView textView;
                public ViewHolder1(View itemView) {
                    super(itemView);
                    networkImageView=(NetworkImageView)itemView.findViewById(R.id.hori_item_image);
                    textView=(TextView)itemView.findViewById(R.id.hori_item_title);
                }
            }
        }
*/    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppController.getmInstance().getRequestQueue().cancelAll("source_data");
    }
}
