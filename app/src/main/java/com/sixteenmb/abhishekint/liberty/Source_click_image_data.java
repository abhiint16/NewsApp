package com.sixteenmb.abhishekint.liberty;


import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 05-06-2017.
 */

public class Source_click_image_data {
    RecyclerView recyclerView;
    List<String> list=new ArrayList<>();
    Source_click.GridViewAdapter gridViewAdapter;
    ImageLoader imageLoader1;
    //String sss="hhhhhhhhhhh";
    String idUrl;
    public  Source_click_image_data(String id, RecyclerView recyclerView,Source_click.GridViewAdapter gridViewAdapter)
    {
        imageLoader1=AppController.getmInstance().getmImageLoader();
        this.idUrl=id;
        this.recyclerView=recyclerView;
        this.gridViewAdapter=gridViewAdapter;

    }
    public  void getImageUrl()
    {

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://newsapi.org/v1/articles?source=" + idUrl + "&sortBy=top&apiKey=e1f3c5ef9ff2421080cb1ece283078d2", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                 //   Log.e("entry into onrespisne","done");
                    JSONArray jsonArray=response.getJSONArray("articles");
                    for(int j=0;j<9;j++)
                    {

                        JSONObject jsonObject=(JSONObject)jsonArray.get(j);
                        String urlToImage=jsonObject.getString("urlToImage");
                      //  Log.e("msg in urlToImage",urlToImage);

                        list.add(urlToImage);
                    }
                    //networkImageView.setImageB
                  //  networkImageView.setImageUrl(sss,imageLoader1);

                    recyclerView.setAdapter(gridViewAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonObjectRequest.setShouldCache(false);
        jsonObjectRequest.setTag("source_click_image_data");
        AppController.getmInstance().addToRequestQueue(jsonObjectRequest);
        }

}
