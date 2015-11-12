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

package com.paul.volleydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.paul.volleydemo.activity.GsonRequestActivity;
import com.paul.volleydemo.activity.ImageLoaderActivity;
import com.paul.volleydemo.activity.SimpleRequestActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_simple)
    void goSimple() {
        startActivity(SimpleRequestActivity.class);
    }

    @OnClick(R.id.btn_gson)
    void goGson() {
        startActivity(GsonRequestActivity.class);
    }

    @OnClick(R.id.btn_image_loader)
    void goLoader() {
        startActivity(ImageLoaderActivity.class);
    }

    private <T> void startActivity(Class<T> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
