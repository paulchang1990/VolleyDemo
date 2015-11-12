/*
 * Portions of this page are modifications based on work created and shared by the Android Open
 * Source Project and used according to terms described in the Creative Commons 2.5 Attribution
 * License.
 */
package com.paul.volleydemo.extra;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Reference:<a href="https://developer.android.com/training/volley/index.html"
 * >Transmitting Network Data Using Volley</a><br/>
 * Created by Paul Chang on 2015/11/11.
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> mClazz;
    private final Response.Listener<T> mListener;
    private final Map<String, String> mHeaders;


    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mHeaders = headers;
        this.mListener = listener;
        this.mClazz = clazz;
    }


    public GsonRequest(String url, Class<T> clazz, Response.Listener<T> listener, Response
            .ErrorListener errorListener) {
        this(Method.GET, url, clazz, null, listener, errorListener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response
                    .headers));
            return Response.success(gson.fromJson(json, mClazz), HttpHeaderParser
                    .parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders != null ? mHeaders : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
