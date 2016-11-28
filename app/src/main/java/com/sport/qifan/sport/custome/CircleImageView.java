package com.sport.qifan.sport.custome;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.utils.ImageUtil;

/**
 * Created by qifan on 2016/11/21.
 */

public class CircleImageView extends View {
    //该view的长宽
    private int imageSize;
    private int resourceId = -1;
    //要显示的图片
    private Bitmap bitmap;
    private Bitmap defaut;

    private Context mContext;
    private Paint mPaint;
    public CircleImageView(Context context) {
      this(context,null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        mPaint=new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height=measure(heightMeasureSpec);
        int width=measure(widthMeasureSpec);
        imageSize=Math.max(width,height);
        setMeasuredDimension(imageSize,imageSize);
    }

    /**
     * 测量宽高
     * @param measureSpec
     * @return
     */
    private int measure(int measureSpec) {
        int mode=MeasureSpec.getMode(measureSpec);
        int size=MeasureSpec.getSize(measureSpec);
        int result=0;
        if (mode==MeasureSpec.EXACTLY){
            result=size;
        }else {
            result=50;
            if (mode==MeasureSpec.AT_MOST){
                result=Math.min(size,result);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        //显示圆形图片有问题
        if (resourceId!=-1){
            bitmap = ImageUtil.decodeBitmapFromResource(getResources(), resourceId, imageSize, imageSize);
            bitmap = ImageUtil.resize(bitmap, imageSize, imageSize);
            canvas.drawBitmap(ImageUtil.createCircleImage(bitmap, imageSize), 0, 0, mPaint);
        }else{
            defaut = ImageUtil.decodeBitmapFromResource(getResources(), R.drawable.user_head_defaut, imageSize, imageSize);
            defaut = ImageUtil.resize(defaut, imageSize, imageSize);
            canvas.drawBitmap(ImageUtil.createCircleImage(defaut, imageSize), 0, 0, mPaint);
        }
    }
}
