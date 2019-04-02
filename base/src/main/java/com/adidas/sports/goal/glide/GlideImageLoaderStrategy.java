package com.adidas.sports.goal.glide;

import android.content.Context;
import android.content.res.Resources;
import android.os.Looper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.adidas.sports.goal.glide.transformation.GlideCircleTransform;

import java.io.File;

public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {

    @Override
    public void loadImage(String url, int placeholderId, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholderId)
                        .error(placeholderId)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, String url, int placeholderId, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholderId)
                        .error(placeholderId)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }


    @Override
    public void loadImage(String url, RequestOptions requestOptions, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadImage(String url, int placeholderId, int width, int height, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholderId)
                        .error(placeholderId)
                        .override(width, height)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(imageView.getDrawable())
                        .error(imageView.getDrawable())
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadFileImage(File file, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(file)
                .apply(new RequestOptions()
                        .placeholder(imageView.getDrawable())
                        .error(imageView.getDrawable()))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadResImage(int resId, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(resId)
                .apply(new RequestOptions()
                        .placeholder(imageView.getDrawable())
                        .error(imageView.getDrawable()))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadCircleImage(String url, int placeholderId, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholderId)
                        .error(placeholderId)
                        .transforms(new CenterCrop()
                                , new GlideCircleTransform())
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadCircleImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(imageView.getDrawable())
                        .error(imageView.getDrawable())
                        .transforms(new CenterCrop()
                                , new GlideCircleTransform())
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadCircleImage(int resId, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(resId)
                .apply(new RequestOptions()
                        .placeholder(imageView.getDrawable())
                        .error(imageView.getDrawable())
                        .transforms(new CenterCrop()
                                , new GlideCircleTransform())
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadRoundImage(int resId, int radius, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(resId)
                .apply(new RequestOptions()
                        .placeholder(imageView.getDrawable())
                        .error(imageView.getDrawable())
//                        .transforms(new CenterCrop()
//                                , new GlideRoundTransform(radius))
                        .transforms(new CenterCrop(), new RoundedCorners((int) (Resources.getSystem().getDisplayMetrics().density * radius)))
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadRoundImage(String url, int radius, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(imageView.getDrawable())
                        .error(imageView.getDrawable())
//                        .transforms(new CenterCrop()
//                                , new GlideRoundTransform(radius))
                        .transforms(new CenterCrop(), new RoundedCorners((int) (Resources.getSystem().getDisplayMetrics().density * radius)))
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadRoundImage(String url, int placeholderId, int radius, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholderId)
                        .error(placeholderId)
//                        .transforms(new CenterCrop()
//                                , new GlideRoundTransform(radius))
                        .transforms(new CenterCrop(), new RoundedCorners((int) (Resources.getSystem().getDisplayMetrics().density * radius)))
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @Override
    public void loadRoundImageWithoutAnim(String url, int placeholderId, int radius, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholderId)
                        .error(placeholderId)
                        .transforms(new CenterCrop(), new RoundedCorners((int) (Resources.getSystem().getDisplayMetrics().density * radius)))
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    @Override
    public void loadGifImage(String url, int placeholderId, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholderId)
                        .error(placeholderId)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    @Override
    public void loadGifImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
//                        .placeholder(imageView.getDrawable())
//                        .error(imageView.getDrawable())
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    @Override
    public void loadGifImage(int resId, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(resId)
                .apply(new RequestOptions()
//                        .placeholder(imageView.getDrawable())
//                        .error(imageView.getDrawable())
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    @Override
    public void loadGifImage(int resId, ImageView imageView, final int count) {
        Glide.with(imageView.getContext())
                .asGif()
                .load(resId)
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable gifDrawable, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        //设置循环播放次数为1次
                        gifDrawable.setLoopCount(count);
                        return false;
                    }
                })
                .into(imageView);
    }

    @Override
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context.getApplicationContext()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context.getApplicationContext()).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context.getApplicationContext()).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void trimMemory(Context context, int level) {
        Glide.get(context).trimMemory(level);
    }

}
