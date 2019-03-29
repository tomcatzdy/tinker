package cn.chinapost.com.tinker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.chinapost.com.tinker.view.ScrollLayout;
import cn.chinapost.com.tinker.view.SimpleImageView;

public class FragmentViewPagerActivity extends AppCompatActivity {

    private SimpleImageView simpleImageView;
    private ScrollLayout scrollLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view_pager);
        simpleImageView = findViewById(R.id.simple);
        scrollLayout = findViewById(R.id.scroll_layout);
    }

    public void roate(View view) {
        scrollLayout.scrollTo(200,200);
    }
}
