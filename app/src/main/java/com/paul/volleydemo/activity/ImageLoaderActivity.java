/*
 * Copyright © 2015 Paul Chang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.paul.volleydemo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.paul.volleydemo.MainActivity;
import com.paul.volleydemo.R;
import com.paul.volleydemo.extra.ImageLoaderManager;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageLoaderActivity extends Activity {

    @Bind(R.id.lv_pics)
    ListView mListView;
    List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        ButterKnife.bind(this);
        initData();
        BaseAdapter adapter = new NetWorkImageAdapter(this,0,mDatas);
        mListView.setAdapter(adapter);

    }

    private void initData() {
        if (mDatas == null) {
            mDatas = new LinkedList<>();
        }

        BufferedReader reader = null;
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("out.txt");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String len = null;

            while ((len = reader.readLine()) != null) {
                if (len.trim().length() != 0)
                    mDatas.add(len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeSilently(reader);
            closeSilently(inputStream);
        }
    }

    private void closeSilently(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class NetWorkImageAdapter extends ArrayAdapter<String> {


        public NetWorkImageAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), R.layout.item_image_loader, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.ivImageLoader.setDefaultImageResId(R.mipmap.loading);
            holder.ivImageLoader.setErrorImageResId(R.mipmap.error);
            holder.ivImageLoader.setScaleType(ImageView.ScaleType.FIT_XY);
            holder.ivImageLoader.setImageUrl(mDatas.get(position),ImageLoaderManager.getInstance
                    (parent.getContext()).getImageLoader());
            return convertView;
        }

        /**
         * This class contains all butterknife-injected Views & Layouts from layout file
         * 'item_image_loader.xml'
         * for easy to all layout elements.
         *
         * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers
         *         (http://github.com/avast)
         */
        class ViewHolder {
            @Bind(R.id.iv_image_loader)
            NetworkImageView ivImageLoader;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
