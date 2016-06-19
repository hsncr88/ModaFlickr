package com.modanisa.modaflickr.activity;

import android.graphics.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.modanisa.flickrapiclient.entity.Photo;
import com.modanisa.flickrapiclient.entity.Photos;
import com.modanisa.flickrapiclient.entity.Result;
import com.modanisa.modaflickr.R;
import com.modanisa.modaflickr.receiver.NetworkStateChangeReceiver;
import com.modanisa.modaflickr.util.EndlessRecyclerOnScrollListener;
import com.modanisa.modaflickr.adapter.PhotoAdapter;
import com.modanisa.modaflickr.flickr.FlickrClient;
import com.modanisa.modaflickr.util.EventRecyclerView;
import com.modanisa.modaflickr.util.OnDetectScrollListener;
import com.modanisa.modaflickr.util.ToastUtils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrimaryActivity extends AppCompatActivity {

    public static float PARALLAX_VALUE = 2f;

    @BindView(R.id.recycler_photo_list)
    EventRecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private PhotoAdapter adapter;
    EndlessRecyclerOnScrollListener endlessListener;

    @Subscribe
    public void onEvent(NetworkStateChangeReceiver.NetworkConnectivityChanged.Online event) {
        if(adapter.getItemCount() == 0)
            loadPage(1);
    };

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
        setContentView(R.layout.activity_primary);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PhotoAdapter(this, new ArrayList<Photo>(0));
        recyclerView.setAdapter(adapter);

        endlessListener = new EndlessRecyclerOnScrollListener(layoutManager){
            @Override
            public void onLoadMore() {
                int current_page = adapter.getItemCount() / 15;
                loadPage(current_page + 1);
            }
        };

        recyclerView.addOnScrollListener(endlessListener);

        recyclerView.setOnDetectScrollListener(new OnDetectScrollListener() {
            public void onUpScrolling() {
                int first = layoutManager.findFirstVisibleItemPosition();
                int last = layoutManager.findLastVisibleItemPosition();
                for (int i = 0; i < (last - first); i++) {
                    ImageView imageView = ((PhotoAdapter.ViewHolder) recyclerView.getLayoutManager().getChildAt(i).getTag()).photoView;
                    Matrix imageMatrix = imageView.getImageMatrix();
                    imageMatrix.postTranslate(0, -PARALLAX_VALUE);
                    imageView.setImageMatrix(imageMatrix);
                    imageView.invalidate();
                }
            }

            public void onDownScrolling() {
                int first = layoutManager.findFirstVisibleItemPosition();
                int last = layoutManager.findLastVisibleItemPosition();
                for (int i = 0; i < (last - first); i++) {
                    ImageView imageView = ((PhotoAdapter.ViewHolder) layoutManager.getChildAt(i).getTag()).photoView;
                    Matrix imageMatrix = imageView.getImageMatrix();
                    imageMatrix.postTranslate(0, PARALLAX_VALUE);
                    imageView.setImageMatrix(imageMatrix);
                    imageView.invalidate();
                }
            }
        });

        loadPage(1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    private void loadPage(int page)
    {
        FlickrClient.get().getPhotoListByTag("moda", 15, page, new Callback<Result<Photos>>() {
            @Override
            public void onResponse(Call<Result<Photos>> call, Response<Result<Photos>> response) {
                if(response.isSuccessful())
                {
                    Result<Photos> result = response.body();
                    if(result.isSuccess())
                    {
                        adapter.add(response.body().getPhotos().getPhoto());
                        return;
                    }
                    else
                    {
                        if(result.getMessage() != null)
                            ToastUtils.TOAST_SHORT.show(PrimaryActivity.this, result.getMessage());
                    }
                }

                endlessListener.setLoading(false);
            }

            @Override
            public void onFailure(Call<Result<Photos>> call, Throwable t) {
                ToastUtils.TOAST_SHORT.show(PrimaryActivity.this, R.string.failed_to_load);
                //Delay next load 5 secs if it failed
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        endlessListener.setLoading(false);
                    }
                }, 5000);
            }
        });
    }
}
