package com.android.shudu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class MyView1 extends View {
    public MyView1(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRect(20,20,50,100,paint);
        super.onDraw(canvas);

    }
}
