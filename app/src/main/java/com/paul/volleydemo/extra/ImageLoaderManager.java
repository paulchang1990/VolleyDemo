/*
 * Portions of this page are modifications based on work created and shared by the Android Open
 * Source Project and used according to terms described in the Creative Commons 2.5 Attribution
 * License.
 */
package com.paul.volleydemo.extra;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Reference:<a href="https://developer.android.com/training/volley/index.html"
 * >Transmitting Network Data Using Volley</a><br/>
 * <p/>
 *
 * 自定义的ImageLoader管理类
 * 采用单例模式管理：自定义磁盘缓存大小的RequestQueue和内存缓存的ImageLoader.
 * Created by Paul Chang on 2015/11/12.
 */
public class ImageLoaderManager {
    private volatile static ImageLoaderManager ourInstance;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static Context mContext;

    public static ImageLoaderManager getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (ImageLoaderManager.class) {
                if (ourInstance == null) {
                    ourInstance = new ImageLoaderManager(context);
                }
            }
        }

        return ourInstance;
    }

    private ImageLoaderManager(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext(),50*1024*1024);
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        mRequestQueue.add(request);
    }
}
