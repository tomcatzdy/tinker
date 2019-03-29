package cn.chinapost.com.tinker.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by thinkpad on 2019/3/28.
 */

public class ScrollLayout extends FrameLayout{


    private Scroller mScroller;
    private Paint mPaint;


    public ScrollLayout(@NonNull Context context) {
        this(context,null);
    }

    public ScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            this.scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            this.postInvalidate();
        }

    }


    public void scrollTo(int x, int y){
        mScroller.startScroll(getScrollX(),getScrollY(),x,y);
        this.invalidate();
    }


}
