package com.sixteenmb.abhishekint.liberty;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by abhishekint on 09-05-2017.
 */

public class AppController extends Application{

    public static final String TAG=AppController.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public  static synchronized  AppController getmInstance()
    {
        return  mInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if(mRequestQueue==null)
        {
            mRequestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        return  mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag)
    {
        req.setTag(TextUtils.isEmpty(tag)?TAG:tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequest(Object tag)
    {
        if(mRequestQueue!=null)
        {
            mRequestQueue.cancelAll(tag);
        }
    }

    public ImageLoader getmImageLoader()
    {
        getRequestQueue();
        if(mImageLoader==null)
        {
            mImageLoader=new ImageLoader(this.mRequestQueue,new LruBitmapCache());
        }
        return  this.mImageLoader;
    }


}
