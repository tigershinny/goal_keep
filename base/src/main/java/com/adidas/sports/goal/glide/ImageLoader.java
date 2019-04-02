package com.adidas.sports.goal.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;

import java.io.File;


public class ImageLoader {

    public static final int PIC_DEFAULT_TYPE = 0;

    public static final int LOAD_STRATEGY_DEFAULT = 0;

    private static ImageLoader mInstance;

    private BaseImageLoaderStrategy mStrategy;

    public ImageLoader() {
        mStrategy = new GlideImageLoaderStrategy();
    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    public void loadImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadImage(url, placeholder, imageView);
    }

    public void loadImage(String url, RequestOptions requestOptions, ImageView imageView) {
        mStrategy.loadImage(url, requestOptions, imageView);
    }

    public void loadImage(Context context, String url, int placeholder, ImageView imageView) {
        mStrategy.loadImage(context, url, placeholder, imageView);
    }

    public void loadImage(String url, int placeholder, int width, int height, ImageView imageView) {
        mStrategy.loadImage(url, placeholder, width, height, imageView);
    }

    public void loadImage(String url, ImageView imageView) {
        mStrategy.loadImage(url, imageView);
    }

    public void loadFileImage(File file, ImageView imageView) {
        mStrategy.loadFileImage(file, imageView);
    }

    public void loadResImage(int resId, ImageView imageView) {
        mStrategy.loadResImage(resId, imageView);
    }

    public void loadGifImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadGifImage(url, placeholder, imageView);
    }

    public void loadGifImage(int resId, ImageView imageView) {
        mStrategy.loadGifImage(resId, imageView);
    }

    public void loadGifImage(int resId, ImageView imageView, int count) {
        mStrategy.loadGifImage(resId, imageView, count);
    }

    public void loadGifImage(String url, ImageView imageView) {
        mStrategy.loadGifImage(url, imageView);
    }

    public void loadCircleImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadCircleImage(url, placeholder, imageView);
    }

    public void loadCircleImage(String url, ImageView imageView) {
        imageView.setBackground(null);
        imageView.setImageDrawable(null);
        imageView.setImageBitmap(null);
        mStrategy.loadCircleImage(url, imageView);
    }

    public void loadCircleImage(int resId, ImageView imageView) {
        imageView.setBackground(null);
        imageView.setImageDrawable(null);
        imageView.setImageBitmap(null);
        mStrategy.loadCircleImage(resId, imageView);
    }

    public void loadRoundImageWithoutAnim(String url, int placeholder, int radius, ImageView imageView) {
        mStrategy.loadRoundImageWithoutAnim(url, placeholder, radius, imageView);
    }

    public void loadRoundImage(String url, int placeholder, int radius, ImageView imageView) {
        mStrategy.loadRoundImage(url, placeholder, radius, imageView);
    }

    public void loadRoundImage(String url, int radius, ImageView imageView) {
        imageView.setBackground(null);
        imageView.setImageDrawable(null);
        imageView.setImageBitmap(null);
        mStrategy.loadRoundImage(url, radius, imageView);
    }

    public void loadRoundImage(int resId, int radius, ImageView imageView) {
        imageView.setBackground(null);//这里清除之前的缓存
        imageView.setImageDrawable(null);
        imageView.setImageBitmap(null);
        mStrategy.loadRoundImage(resId, radius, imageView);
    }

    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        mStrategy = strategy;
    }

    public void clearImageDiskCache(final Context context) {
        mStrategy.clearImageDiskCache(context);
    }

    public void clearImageMemoryCache(Context context) {
        mStrategy.clearImageMemoryCache(context);
    }

    public void trimMemory(Context context, int level) {
        mStrategy.trimMemory(context, level);
    }

    public void clearImageAllCache(Context context) {
        clearImageDiskCache(context.getApplicationContext());
        clearImageMemoryCache(context.getApplicationContext());
    }
}
