package cn.chinapost.com.tinker;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class GitTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_text);
        initSplashAnim();
    }

    private void initSplashAnim() {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim);
            ImageView imageView = findViewById(R.id.splash_image_view);
            imageView.setImageDrawable(drawable);
            drawable.start();
        }
    }
}
