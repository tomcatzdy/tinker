package cn.chinapost.com.tinker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

import cn.chinapost.com.tinker.imageloader.ImageLoaderManager;

/**
 * Created by thinkpad on 2019/3/25.
 */

public class PhotoPagerAdapter extends PagerAdapter{

    private Context context;
    private ArrayList<String> photos;
    private ImageLoaderManager mImageLoader;

    public PhotoPagerAdapter(Context context, ArrayList<String> photos) {
        this.context = context;
        this.photos = photos;
        this.mImageLoader = ImageLoaderManager.getInstance(context);
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView = new PhotoView(context);
        mImageLoader.displayImage(imageView,photos.get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
