package com.paul.volleydemo.extra;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * 出处为：<a href='http://blog.csdn.net/guolin_blog/article/details/17612763'>郭霖博客</a><br/>
 */
public class XmlRequest extends Request<XmlPullParser> {

    private Response.Listener<XmlPullParser> mListener;

    public XmlRequest(int method, String url, Response.Listener listener, Response.ErrorListener
            errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    public XmlRequest(String url, Response.Listener listener, Response.ErrorListener
            errorListener) {
        this(Method.GET, url, listener, errorListener);
    }

    @Override
    protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse response) {
        try {
            String xmlResult = new String(response.data, HttpHeaderParser.parseCharset(response
                    .headers));
            XmlPullParserFactory pullFac = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = pullFac.newPullParser();
            pullParser.setInput(new StringReader(xmlResult));
            return Response.success(pullParser, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(response));
        } catch (XmlPullParserException e) {
            return Response.error(new ParseError(response));
        }
    }

    @Override
    protected void deliverResponse(XmlPullParser response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }
}
