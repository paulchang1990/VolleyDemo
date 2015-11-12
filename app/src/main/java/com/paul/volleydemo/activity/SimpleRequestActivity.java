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

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.paul.volleydemo.R;
import com.paul.volleydemo.util.Constant;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SimpleRequestActivity extends AppCompatActivity {

    @Bind(R.id.iv_sample_image)
    ImageView ivSampleImage;
    @Bind(R.id.tv_sample_string)
    TextView tvSampleString;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_request);
        ButterKnife.bind(this);

        mQueue = Volley.newRequestQueue(this);
    }

    @OnClick(R.id.btn_request)
    void requestImage() {
        ImageRequest imageRequest = new ImageRequest(Constant.IMAGE_URL, new Response
                .Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                ivSampleImage.setImageBitmap(response);
            }
        }, ivSampleImage.getWidth(), ivSampleImage.getHeight(), ImageView.ScaleType.CENTER, Bitmap
                .Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ivSampleImage.setImageResource(R.mipmap.ic_launcher);
            }
        });

        StringRequest stringRequest = new StringRequest(Constant.WEATHER_URL, new Response
                .Listener<String>() {

            @Override
            public void onResponse(String response) {
                tvSampleString.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvSampleString.setText(error.toString());
            }
        });

        mQueue.add(imageRequest);
        mQueue.add(stringRequest);
    }
}
