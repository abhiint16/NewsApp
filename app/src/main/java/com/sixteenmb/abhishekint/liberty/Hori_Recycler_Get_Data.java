package com.sixteenmb.abhishekint.liberty;


import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 14-06-2017.
 */

public class Hori_Recycler_Get_Data {

    MainActivity.Hori_Recycler_Adapter hori_recycler_adapter;
    RecyclerView recyclerView;
    List<String> idList = new ArrayList<>();
    String URL = "https://newsapi.org/v1/sources?language=en";
    List<String> imageList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    List<String> urlList=new ArrayList<>();
    List<String> sourceList=new ArrayList<>();
    ImageLoader imageLoader = AppController.getmInstance().getmImageLoader();
    int count=0;
    Hori_Recycler_Get_Data(MainActivity.Hori_Recycler_Adapter h, RecyclerView recycler) {

        this.hori_recycler_adapter = h;
        this.recyclerView = recycler;
    }

    void getId() {
        ////Log.e("inside getId", "inside getid");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

               // Log.e("calling idfinal data", "calling idfinal data");
                idfinalData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

       // Log.e("calling controller id","calling controller id");
        AppController.getmInstance().addToRequestQueue(stringRequest, "source_data");
    }

    void idfinalData(String s) {
      // Log.e("inside idfinaldata", "insdie idfinaldata");

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("sources");

            for (int i = 0; i < jsonArray.length(); i++) {
        //        Log.e("for lop begins", "for loops begins");
             //   ArrayList<String> childList = new ArrayList<String>();
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String id = jsonObject1.getString("id");

    //            Log.e("before adding to list", "before addign to list");
                idList.add(id);
                /*nameList.add(name);


*/

            }
         //   Log.e("leavig fro getfinaldata", "leaving fro getfinaldata");

          //  Log.e("value in listid"," "+idList);
            getFinalData("https://newsapi.org/v1/articles?source=" + idList.get(0) + "&sortBy=top&apiKey=e1f3c5ef9ff2421080cb1ece283078d2");

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    void getFinalData(String url) {

        //Log.e("inside getfinaldata", "inside getfinaldata");
        String URL1=url;

        //for (int i = 0; i < idList.size(); i++) {
         //   Log.e("foor getfinaldaat begin", "for getfinaldat begin");
            //URL1 = "https://newsapi.org/v1/articles?source=" + idList.get(i) + "&sortBy=top&apiKey=e1f3c5ef9ff2421080cb1ece283078d2";
          //  Log.e("url1", " " + URL1);
            if (URL1.equals("https://newsapi.org/v1/articles?source=the-next-web&sortBy=top&apiKey=e1f3c5ef9ff2421080cb1ece283078d2")) {
           //     Log.e("continueeeeeee", "continueeeeee");
                URL1="https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=e1f3c5ef9ff2421080cb1ece283078d2";
              //  continue;
            }

            StringRequest stringRequest1 = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
//Log.e("leaving for finaldat","final data");

                    finalData(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
         //   Log.e("for appcontroller", "for appcontroller");
            AppController.getmInstance().addToRequestQueue(stringRequest1);

        }
        /*Log.e("value in title", " " + titleList);
        Log.e("value in image", " " + imageList);
        Log.e("befoe set adapter hori", "before set adapetr hori;");
*/



    void finalData(String s) {
       // Log.e("inn finaldata","in finaladat");
        try {
            JSONObject jsonObject = new JSONObject(s);
            String source=jsonObject.getString("source");
            JSONArray jsonArray = jsonObject.getJSONArray("articles");



                JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
                String title = jsonObject1.getString("title");
                String urlToImage = jsonObject1.getString("urlToImage");
                String urlArticle = jsonObject1.getString("url");

            sourceList.add(source);
            titleList.add(title);
            imageList.add(urlToImage);
            urlList.add(urlArticle);

          /*  Log.e("value in titlelist","v"+titleList);
            Log.e("value in imagelist","v"+imageList);*/
            if(count==10)
            {
                recyclerView.setAdapter(hori_recycler_adapter);
            }
            if(count<59)
            {
                getFinalData("https://newsapi.org/v1/articles?source=" + idList.get(count+1) + "&sortBy=top&apiKey=e1f3c5ef9ff2421080cb1ece283078d2");
                count++;
            }
            else if(count>=59)
           recyclerView.setAdapter(hori_recycler_adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}