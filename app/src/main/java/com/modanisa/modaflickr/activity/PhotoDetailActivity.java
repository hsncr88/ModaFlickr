package com.modanisa.modaflickr.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.modanisa.modaflickr.R;
import com.modanisa.modaflickr.util.BitmapTransformation;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoDetailActivity extends AppCompatActivity {

    public static final String PHOTO_URL_KEY = "PHOTO_URL_KEY";

    @BindView(R.id.image_photo_detail)
    ImageView photoDetailImage;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            String photoURL = extras.getString(PHOTO_URL_KEY);
            Picasso.with(this).load(photoURL).transform(new BitmapTransformation(photoDetailImage)).placeholder(R.drawable.placeholder_gallery)
                    .error(R.drawable.placeholder_gallery).into(photoDetailImage);
        }
    }
}
