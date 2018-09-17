package com.sixteenmb.abhishekint.liberty;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by abhishekint on 09-05-2017.
 */

public class LruBitmapCache extends LruCache implements ImageLoader.ImageCache{
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public LruBitmapCache(int maxSize) {
        super(maxSize);
    }

    public  LruBitmapCache()
    {
        this(getDefaultLruCacheSize());

    }


    public static int getDefaultLruCacheSize() {
        final int maxMemory=(int)(Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize=maxMemory/8;
        return cacheSize;
    }
    /*   @Override
       protected int sizeOf(Object key, Object value) {
           return value.getRowBytes()*value.getHeight()/1024;
       }
   */
    @Override
    public Bitmap getBitmap(String url)
    {
        return (Bitmap) get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {

        put(url,bitmap);
    }

}
