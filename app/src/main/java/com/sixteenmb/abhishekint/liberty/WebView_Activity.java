package com.sixteenmb.abhishekint.liberty;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by abhishek on 09-06-2017.
 */

public class WebView_Activity extends AppCompatActivity implements View.OnClickListener {

   // Set<String> set = new HashSet<String>();
    //SessionManager session;
    FloatingActionButton main, fab1,fab2,fab3,fab4;
    WebView webView;
    ProgressBar progressBar;
   Boolean isopen=false;
    Animation fab_open,fab_close,fab_clock,fab_anticlock;
    CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.webview_activity);

        webView=(WebView)findViewById(R.id.web_view);

        main=(FloatingActionButton)findViewById(R.id.fab);
        fab1=(FloatingActionButton)findViewById(R.id.fab_1);
        fab2=(FloatingActionButton)findViewById(R.id.fab_2);
       // fab3=(FloatingActionButton)findViewById(R.id.fab_3);
        fab4=(FloatingActionButton)findViewById(R.id.fab_4);
        main.setOnClickListener(this);
       // fab3.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab4.setOnClickListener(this);

        fab_open=new AnimationUtils().loadAnimation(getApplicationContext(),R.anim.floating_open);
        fab_close=new AnimationUtils().loadAnimation(getApplicationContext(),R.anim.floating_close);
        fab_clock=new AnimationUtils().loadAnimation(getApplicationContext(),R.anim.rotate_clock);
        fab_anticlock=new AnimationUtils().loadAnimation(getApplicationContext(),R.anim.rotate_anticlock);

        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.web_view_parent);
        //session=new SessionManager(getApplicationContext());

        progressBar=(ProgressBar)findViewById(R.id.progress);
        progressBar.setMax(100);
       // progressDialog.show();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebChromeClient(new WebChromeClient()
                                   {
                                       @Override
                                       public void onProgressChanged(WebView view, int newProgress) {
                                           progressBar.setProgress(newProgress);
                                           //progressDialog.show();
                                           if(newProgress==100)
                                           {
                                               progressBar.setVisibility(View.GONE);
                                              // progressDialog.cancel();
                                           }
                                           else {
                                             //  progressDialog.show();
                                           }
                                       }

                                   }
        );

        // When the user clicks a link from a web page in your WebView, the default behavior is for
        // Android to launch an application that handles URLs. Usually, the default web browser opens
        // and loads the destination URL. However, you can override this behavior for your WebView, so
        // links open within your WebView. You can then allow the user to navigate backward and forward
        // through their web page history that's maintained by your WebView.
        //To open links clicked by the user, simply provide a WebViewClient for your WebView,
        // using setWebViewClient(). For example:
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                //progressDialog.cancel();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                /*if(session.is_key())
                {
                    if(session.is_BookMarked(webView.getUrl()))
                    {
                        fab3.setImageResource(R.drawable.bookmarkactive);
                        book=true;
                    }
                }*/

                //progressDialog.cancel();

            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
               // progressDialog.cancel();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                progressBar.setMax(100);
                progressBar.setVisibility(View.VISIBLE);

                view.setWebChromeClient(new WebChromeClient()
                                           {
                                               @Override
                                               public void onProgressChanged(WebView view, int newProgress) {
                                                   progressBar.setProgress(newProgress);
                                                   //progressDialog.show();
                                                   if(newProgress==100)
                                                   {
                                                       progressBar.setVisibility(View.GONE);
                                                       // progressDialog.cancel();
                                                   }
                                                   else {
                                                       //  progressDialog.show();
                                                   }
                                               }

                                           }
                );
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
//True if the host application wants to leave the current WebView and handle the url itself, otherwise return false.
                return  false;
            }
        });
        /**
         * Enabling zoom-in controls
         * */
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.loadUrl(getIntent().getStringExtra("url"));
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fab)
        {
            if(isopen)
            {

                fab1.startAnimation(fab_close);
                fab2.startAnimation(fab_close);
                //fab3.startAnimation(fab_close);
                fab4.startAnimation(fab_close);
                main.startAnimation(fab_anticlock);
                isopen=false;
                fab1.clearAnimation();
                fab2.clearAnimation();
             //   fab3.clearAnimation();
                fab4.clearAnimation();

                fab1.setVisibility(View.GONE);
                fab2.setVisibility(View.GONE);
             //   fab3.setVisibility(View.GONE);
                fab4.setVisibility(View.GONE);
            }
            else
            {
                fab1.setVisibility(View.INVISIBLE);
                fab2.setVisibility(View.INVISIBLE);
              //  fab3.setVisibility(View.INVISIBLE);
                fab4.setVisibility(View.INVISIBLE);

                fab1.startAnimation(fab_open);
                fab2.startAnimation(fab_open);
             //   fab3.startAnimation(fab_open);
                fab4.startAnimation(fab_open);
                main.startAnimation(fab_clock);
                isopen=true;
            }
            /*Animation animation= AnimationUtils.loadAnimation(this,R.anim.floating_open);
            main.startAnimation(animation);
            fab1.setVisibility(View.VISIBLE);
            fab2.setVisibility(View.VISIBLE);
            fab3.setVisibility(View.VISIBLE);
            fab4.setVisibility(View.VISIBLE);
*/
           // fab3.setVisibility(View.VISIBLE);
        }
       /* if(view.getId()==R.id.fab_3)
        {

            if(book==true)
            {
                if(session.is_key())
                {
                    set=session.retrieve_key();
                    set.remove(webView.getUrl());
                    session.un_BookMark(set);
                    fab3.setImageResource(R.drawable.bookmark);
                    book=false;
                }

            }
            if(book==false)
            {
                if(session.is_key())
                {
                    set=session.retrieve_key();
                    set.add(webView.getUrl());
                    session.do_BookMark(set);
                    fab3.setImageResource(R.drawable.bookmarkactive);
                    book=true;
                }

                else
                {
                    set.add(webView.getUrl());
                    session.do_BookMark(set);
                    fab3.setImageResource(R.drawable.bookmarkactive);
                    book=true;
                }

            }
        }
*/
        if(view.getId()==R.id.fab_4)
        {
            Intent shareIntent=new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,webView.getTitle());
            shareIntent.putExtra(Intent.EXTRA_TEXT,webView.getUrl()+"\n\nIn this biased world , You need to authenticate every news . LIBERTY provides you more than 50 top news sources to see this world .\n\nNO NEWS CAN GO UNTAPPED .\n\n"+"https://play.google.com/store/apps/details?id="+getPackageName());
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent,"Complete action using ...."));
        }

        if(view.getId()==R.id.fab_1)
        {
            if(webView.canGoForward())
            {
                webView.goForward();
            }
            else  if(!webView.canGoForward())
            {
                Snackbar snackbar= Snackbar.make(coordinatorLayout,"No Forward Page To Visit ...",Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(this,R.color.main_tab));
                snackbar.show();
            }

        }
        if(view.getId()==R.id.fab_2)
        {
            if(webView.canGoBack())
            {
                webView.goBack();
            }
            else  if(!webView.canGoBack())
            {
                Snackbar snackbar=Snackbar.make(coordinatorLayout,"No Backward Page To Visit ...",Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(this,R.color.main_tab));
                snackbar.show();
            }
         }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }


}
