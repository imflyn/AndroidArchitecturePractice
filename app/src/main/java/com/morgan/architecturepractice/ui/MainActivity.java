package com.morgan.architecturepractice.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.morgan.architecturepractice.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Glide.with(getContext()).load(promotion.getImage()).asBitmap().into(new BitmapImageViewTarget(iv_promotion) {

            @Override
            protected void setResource(Bitmap resource) {
                iv_promotion.setImageBitmap(resource);
            }
        });
    }
}
