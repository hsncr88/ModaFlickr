package com.modanisa.modaflickr.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Transformation;

/**
 * Created by Hasan ACAR on 07.05.2015.
 */
public class BitmapTransformation implements Transformation {
    int maxWidth;
    int maxHeight;
    ImageView imageView;

    public BitmapTransformation(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        this.maxWidth = imageView.getWidth();
        this.maxHeight = imageView.getHeight();

        int targetWidth, targetHeight;
        double aspectRatio;

        if (source.getWidth() > source.getHeight()) {
            targetWidth = maxWidth;
            aspectRatio = (double) source.getHeight() / (double) source.getWidth();
            targetHeight = (int) (targetWidth * aspectRatio);
        } else {
            targetHeight = maxHeight;
            aspectRatio = (double) source.getWidth() / (double) source.getHeight();
            targetWidth = (int) (targetHeight * aspectRatio);
        }

        Bitmap result = null;
        if (targetHeight <= 0 || targetWidth <= 0)
            result = Bitmap.createBitmap(source);
        else
            result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override
    public String key() {
        return maxWidth + "x" + maxHeight;
    }
}