package com.example.trivia.controller;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

private static  AppController instance;
private RequestQueue requestQueue;

//singleton class

public static synchronized AppController getInstance(){

    return instance;
}
//method for checking if the object is created or not
public  RequestQueue getRequestQueue() {
    //if not created then do this
        if(requestQueue == null){

            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
       // if created then returned old only
        return requestQueue;
    }


public  <T> void addToRequestQueue(Request<T> req){
    getRequestQueue().add(req);
}

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
