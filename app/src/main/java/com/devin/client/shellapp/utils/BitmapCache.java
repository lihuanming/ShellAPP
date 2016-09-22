package com.devin.client.shellapp.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Yatu on 2016/5/16.
 */
public class BitmapCache implements ImageLoader.ImageCache {
    private LruCache<String,Bitmap> cache;
    public final int MAX_SIZE=10*10*1024;

    public BitmapCache(){
        cache = new LruCache<String, Bitmap>(MAX_SIZE){
            @Override
            protected int sizeOf(String key, Bitmap value)
            {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
    }
}
