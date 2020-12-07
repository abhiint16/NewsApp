package com.sixteenmb.abhishekint.liberty;


import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 03-06-2017.
 */

public class Sources_Data {

    Sources.Sources_Adapter sources_adapter;
    RecyclerView recyclerView;
    List<String> idList=new ArrayList<>();
    List<String> nameList=new ArrayList<>();
    List<String> descriptionList=new ArrayList<>();
    List<String> countryList=new ArrayList<>();
    List<String> categoryList=new ArrayList<>();
    List<List<String>> sortByList = new ArrayList<List<String>>();
    List<String> icon_link_list=new ArrayList<>();

    //List<Search_Get_Set> search_get_setList=new ArrayList<>();

    String URL="https://newsapi.org/v1/sources?language=en";

    String URL_ICON="http://abhishekint.16mb.com/NewsApp/retrieve.php";
    Sources_Data(Sources.Sources_Adapter sources_adapter,RecyclerView recyclerView)
    {
       this.sources_adapter=sources_adapter;
       this.recyclerView=recyclerView;
    }

    void getIcon()
    {
        JsonObjectRequest jsonObjectRequest_icon=new JsonObjectRequest(Request.Method.GET, URL_ICON, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("server_response");
                    for(int j=0;j<jsonArray.length();j++)
                    {
                        JSONObject jsonObject1=(JSONObject)jsonArray.get(j);
                        String icon_link=jsonObject1.getString("img_id");
                        icon_link_list.add(icon_link);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getmInstance().addToRequestQueue(jsonObjectRequest_icon,"source_data");
    }


    void setItem()
    {
        //Log.e("setItem enter","setitementer");
         StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


              //  Log.e("leavif4finaldata source","leavng 4 finaldta source");
                finalData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

     //   Log.e("controller sourcedata","controller sourcedata");
        AppController.getmInstance().addToRequestQueue(stringRequest,"source_data");
    }

    void finalData(String s)
    {
     //   Log.e("in final dat sourcedata","in final data sourcedata");

        try {
            JSONObject jsonObject=new JSONObject(s);
            JSONArray jsonArray=jsonObject.getJSONArray("sources");

            if(jsonArray.length()>icon_link_list.size())
            {
                for(int k=0;k<jsonArray.length()-icon_link_list.size();k++)
                {
                    icon_link_list.add(null);
                }
            }
            for(int i=0;i<jsonArray.length();i++)
            {
                ArrayList<String> childList = new ArrayList<String>();
                JSONObject jsonObject1=(JSONObject)jsonArray.get(i);
                String id=jsonObject1.getString("id");
                String name=jsonObject1.getString("name");
                String description=jsonObject1.getString("description");
                String category=jsonObject1.getString("category");
                String country=jsonObject1.getString("country");
                JSONArray jsonArray1=jsonObject1.getJSONArray("sortBysAvailable");

                for(int j=0;j<jsonArray1.length();j++)
                {

                    String sort= (String) jsonArray1.get(j);
                    childList.add(sort);
                }

                idList.add(id);
                nameList.add(name);


                descriptionList.add(description);
                categoryList.add(category);
                countryList.add(country);
                sortByList.add(childList);


            }

           /* int count=0;
            for(String name:nameList)
            {
                search_get_setList.add(new Search_Get_Set(name,idList.get(count),categoryList.get(count),countryList.get(count),descriptionList.get(count)));
                count++;
            }*/


           // sources_adapter=new Sources.Sources_Adapter(search_get_setList);
       //     Log.e("just before set dapter","just before set dapter");
            recyclerView.setAdapter(sources_adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
