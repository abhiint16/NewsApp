package com.sixteenmb.abhishekint.liberty;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhishekint on 27-05-2017.
 */

public class Send_direct_msg extends AppCompatActivity {

     ProgressDialog progressDialog;
    EditText editText;
    FloatingActionButton floatingActionButton;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_direct_msg);

        editText=(EditText)findViewById(R.id.send_msg_editText);
        linearLayout =(LinearLayout)findViewById(R.id.send_msg_main_layout);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.fab)
                {
                    String s=editText.getText().toString();
                    if(checkEditText(s))
                    {

                       sendText(s,"http://abhishekint.16mb.com/webspluscomedy/messge_send/retrieve2.php");

                    }

                }
                }
            });
    }

    boolean checkEditText(String s)
    {
     if(s.equals("") || s.trim().equals(""))
     {
         Snackbar snackbar=Snackbar.make(linearLayout,"Your words are valuable , please write something ...",Snackbar.LENGTH_LONG);
         snackbar.getView().setBackgroundColor(ContextCompat.getColor(this,R.color.toolbar_main_activity));
         snackbar.show();
         return false;
     }
     return true;
    }


    void sendText(final String text, String link)
    {

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Sending ....");
        progressDialog.show();

        StringRequest stringRequest1=new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.cancel();
                Snackbar snackbar=Snackbar.make(linearLayout,"Your Message has been sent . Thanks!",Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.toolbar_main_activity));
                snackbar.show();
                editText.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("text", text);
                return params;
            }
        };

        AppController.getmInstance().addToRequestQueue(stringRequest1);

    }
}
