package cn.chinapost.com.tinker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

import cn.chinapost.com.tinker.R;
import cn.chinapost.com.tinker.idcardcamera.utils.ImageUtils;

/**
 * Created by thinkpad on 2019/3/28.
 */

public class SimpleImageView extends View{


    private Paint mBitmapPaint;
    private int mWidth;
    private int mHeight;
    private Drawable mDrawable;
    private Bitmap mBitmap;
    private Scroller mScroller;


    public SimpleImageView(Context context) {
        this(context,null);
    }

    public SimpleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        initAttrs(attrs);
        initPaint();

    }

    private void initPaint() {
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
    }

    /**
     * 初始化控件的属性
     * @param attrs
     */
    private void initAttrs(AttributeSet attrs) {
        if (attrs!=null){
            TypedArray typedArray = null;
            try {
                typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleImageView);
                mDrawable = typedArray.getDrawable(R.styleable.SimpleImageView_src);
                measureDrawable();
            }finally {
                if (typedArray!=null){
                    typedArray.recycle();
                }
            }
        }
    }

    private void measureDrawable() {

        if (mDrawable==null){
            throw new RuntimeException("图片为空");
        }
        mWidth = mDrawable.getIntrinsicWidth();
        mHeight = mDrawable.getIntrinsicHeight();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //setMeasuredDimension(mWidth,mHeight);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        setMeasuredDimension(measureWidth(widthMode,widthSize),measureHeight(heightMode,heightSize));

    }



    private int measureWidth(int mode ,int width){
        switch (mode){
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                break;

            case MeasureSpec.EXACTLY:
                mWidth = width;
                break;
        }

        return mWidth;
    }



    private int measureHeight(int mode ,int height){
        switch (mode){
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                break;


            case MeasureSpec.EXACTLY:
                mHeight = height;
                break;
        }

        return mHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDrawable==null){
            return;
        }
        //
        // canvas.drawBitmap(ImageUtils.drawableToBitmap(mDrawable),getLeft(),getTop(),mBitmapPaint);
        if (mBitmap==null){
            mBitmap = Bitmap.createScaledBitmap(ImageUtils.drawableToBitmap(mDrawable),getMeasuredWidth(),getMeasuredHeight(),true);

        }

        canvas.drawBitmap(mBitmap,getLeft(),getTop(),mBitmapPaint);

        canvas.save();
        canvas.rotate(90);
        mBitmapPaint.setColor(Color.RED);
        mBitmapPaint.setTextSize(30);
        canvas.drawText("郑德赟",getLeft()+50,getTop()-50,mBitmapPaint);

        canvas.restore();

    }
}
