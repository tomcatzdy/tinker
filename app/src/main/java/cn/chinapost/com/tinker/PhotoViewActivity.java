package cn.chinapost.com.tinker;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import cn.chinapost.com.tinker.adapter.PhotoPagerAdapter;
import cn.chinapost.com.tinker.view.PhotoViewPager;

public class PhotoViewActivity extends AppCompatActivity {

    private ArrayList<String> photos;
    private PhotoViewPager mViewPager;
    private PhotoPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        initData();
        initViewPager();
    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setPageMargin(20);
        mAdapter = new PhotoPagerAdapter(this,photos);
        mViewPager.setAdapter(mAdapter);

    }

    private void initData() {
        Intent intent = getIntent();
        photos = intent.getStringArrayListExtra("photo");

    }
    
}
