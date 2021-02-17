package com.example.hellomodernandroidapplication.view;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hellomodernandroidapplication.R;

public class Util {

    public static void loadImage(ImageView view, String url, CircularProgressDrawable circularProgressDrawable){

        RequestOptions options = new RequestOptions().placeholder(circularProgressDrawable).error(R.mipmap.ic_launcher_round);
        Glide.with(view.getContext()).setDefaultRequestOptions(options).load(url).into(view);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context){

        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();
        return progressDrawable;
    }
}
