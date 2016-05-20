package com.example.coupondisplayview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 作者：陈剑涛 on 2016/5/20 10:13
 * 邮箱：978328357@qq.com
 * 用途：自定义购物券
 */
public class CouponDisplayView
        extends LinearLayout
{

    private Paint mPaint;
    //圆的间距
    private float gap = 16;
    //半径
    private float radius=20;
    //圆的数量
    private int circleNum;
    private float remain;

    public CouponDisplayView(Context context)
    {
        super(context);
    }

    public CouponDisplayView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        //设置画笔的属性
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public CouponDisplayView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 获取要画的圆的数量
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0)
        {
            remain = (int) (w - gap) % (2 * radius + gap);
        }
        circleNum = (int) ((w - gap) / (2 * radius + gap));
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //        这里remain/2是因为计算出来的可以画的数量不是刚好整除的。这样就会出现右边最后一个间距会比其它的间距都要宽。
        //        所以我们在绘制第一个的时候加上了余下的间距的一半，即使是不整除的情况。至少也能保证第一个和最后一个间距宽度一致。
        super.onDraw(canvas);
        for (int i = 0; i < circleNum; i++)
        {
            float x = gap + radius + remain / 2 + ((gap + radius * 2) * i);
            canvas.drawCircle(x, 0, radius, mPaint);
            canvas.drawCircle(x, getHeight(), radius, mPaint);
        }
    }
}
