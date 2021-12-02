package ir.smmh.fy.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class Network {

    public static String ENCODING = "utf-8";
    private final RequestQueue rq;

    public Network(Context context) {
        rq = Volley.newRequestQueue(context);
    }

    public interface ResponseCallback {
        void onResponse(String response);
    }

    public interface ErrorCallback {
        void onError(VolleyError e);
    }

    public void request(String url, ResponseCallback onResponse, @Nullable ErrorCallback onError, Map<String, String> headers) {
        rq.add(new StringRequest(Request.Method.GET, url, onResponse::onResponse, onError == null ? null : onError::onError) {
            @Override
            public Map<String, String> getHeaders() {
                return headers;
            }
        });
    }

    public static String buildURI(String url, Map<String, String> params) {
        StringBuilder builder = new StringBuilder(url.length() + params.size() * 16);
        builder.append(url);
        try {
            boolean firstParam = true;
            for (String key : params.keySet()) {
                builder.append(firstParam ? '?' : '&');
                firstParam = false;
                builder.append(key);
                builder.append('=');
                builder.append(URLEncoder.encode(params.get(key), ENCODING));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static Bitmap loadImage(java.lang.String url) throws IOException {
        return BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream());
    }
}