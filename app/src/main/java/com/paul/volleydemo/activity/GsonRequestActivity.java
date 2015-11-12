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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.paul.volleydemo.R;
import com.paul.volleydemo.entity.WeatherData;
import com.paul.volleydemo.extra.GsonRequest;
import com.paul.volleydemo.extra.ImageLoaderManager;
import com.paul.volleydemo.util.Constant;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GsonRequestActivity extends AppCompatActivity {

    @Bind(R.id.tv_json)
    TextView tvJson;
    @Bind(R.id.tv_gson)
    TextView tvGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_request);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_request)
    void requestJson() {
        StringRequest jsonRequest = new StringRequest(Constant.WEATHER_URL, new Response
                .Listener<String>() {


            @Override
            public void onResponse(String response) {
                tvJson.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvJson.setText(error.toString());
            }
        });

        GsonRequest gsonRequest = new GsonRequest<WeatherData>(Constant.WEATHER_URL, WeatherData
                .class, new Response.Listener<WeatherData>() {

            @Override
            public void onResponse(WeatherData response) {
                tvGson.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvGson.setText(error.toString());
            }
        });


        ImageLoaderManager.getInstance(this).addToRequestQueue(jsonRequest);
        ImageLoaderManager.getInstance(this).addToRequestQueue(gsonRequest);
    }
}
