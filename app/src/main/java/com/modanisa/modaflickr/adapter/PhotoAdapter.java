package com.modanisa.modaflickr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.modanisa.flickrapiclient.entity.Photo;
import com.modanisa.modaflickr.R;
import com.modanisa.modaflickr.activity.PhotoDetailActivity;
import com.modanisa.modaflickr.util.BitmapTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by canavar on 6/19/2016.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{
    public static String TAG = "PhotoAdapter";

    private List<Photo> dataset;
    private Context context;

    public PhotoAdapter(Context context, List<Photo> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @BindView(R.id.image_photo)
        public ImageView photoView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            v.setEnabled(false);
            int position = getLayoutPosition();
            Photo photo =  dataset.get(position);
            Intent intent = new Intent(context, PhotoDetailActivity.class);
            intent.putExtra(PhotoDetailActivity.PHOTO_URL_KEY, photo.getPhotoUrlString(Photo.PhotoSize.MEDIUM));
            context.startActivity(intent);
            v.setEnabled(true);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_photo, parent, false);

        ViewHolder vh = new ViewHolder(v);
        v.setTag(vh);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Photo photo = dataset.get(position);
        Picasso.with(context).load(photo.getPhotoUrlString(Photo.PhotoSize.MEDIUM)).transform(new BitmapTransformation(holder.photoView))
                .placeholder(R.drawable.placeholder_gallery)
                .error(R.drawable.placeholder_gallery)
                .into(holder.photoView);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(List<Photo> items) {
        dataset.addAll(items);
        this.notifyDataSetChanged();
    }
}
