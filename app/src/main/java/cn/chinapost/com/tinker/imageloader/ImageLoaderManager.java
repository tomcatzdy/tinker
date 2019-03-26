package cn.chinapost.com.tinker.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import cn.chinapost.com.tinker.R;

/**
 * ImageLoader管理类
 * Created by thinkpad on 2019/3/21.
 */

public class ImageLoaderManager {


    private static final int THREAD_COUNT = 4;
    private static final int PROPRITY = 2;
    private static final int DISK_CACHE_SIZE = 50*1024;
    private static final int CONNECT_TIME_OUT = 5*1000;
    private static final int READ_TIME_OUT = 3*1000;

    private static ImageLoader mImageLoader = null;
    private static ImageLoaderManager mInstance = null;

    public static ImageLoaderManager getInstance(Context context){
        if (mInstance==null){
            synchronized (ImageLoaderManager.class){
                if (mInstance==null){
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }

        return mInstance;
    }



    private ImageLoaderManager (Context context){

        ImageLoaderConfiguration configuration =  new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(THREAD_COUNT)
                .threadPriority(Thread.NORM_PRIORITY-PROPRITY)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(DISK_CACHE_SIZE)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//使用MD5命名
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .defaultDisplayImageOptions(getDefaultOptions())
                .imageDownloader(new BaseImageDownloader(context,CONNECT_TIME_OUT,READ_TIME_OUT))
                .writeDebugLogs()//debug模式下输出日志
                .build();

        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();
    }

    private DisplayImageOptions getDefaultOptions() {

        DisplayImageOptions options = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .decodingOptions(new BitmapFactory.Options())
                .build();

        return options;
    }



    public void displayImage(ImageView imageView, String url,
                             DisplayImageOptions options,
                             ImageLoadingListener loadingListener){

        if (mImageLoader!=null){
            mImageLoader.displayImage(url,imageView,options,loadingListener);
        }

    }

    public void displayImage(ImageView imageView, String url,
                             DisplayImageOptions options){

        displayImage(imageView,url,options,null);
    }

    public void displayImage(ImageView imageView, String url){
        displayImage(imageView,url,null);
    }
}
