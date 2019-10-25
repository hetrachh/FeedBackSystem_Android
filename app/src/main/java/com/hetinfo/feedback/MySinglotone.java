package com.hetinfo.feedback;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySinglotone {
    private static MySinglotone minstance;
    private RequestQueue requestQueue;
    private static Context mCntx;

    private MySinglotone(Context context)
    {
        mCntx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue()
    {
        if (requestQueue==null)
        {
            requestQueue = Volley.newRequestQueue(mCntx.getApplicationContext());

        }
        return  requestQueue;
    }
    public static synchronized MySinglotone getInstance (Context context)
    {
        if(minstance == null)
        {
            minstance = new MySinglotone(context);
        }
        return minstance;
    }
    public<T> void addToRequestQueue(Request<T> request)
    {
        getRequestQueue().add(request);

    }
}
