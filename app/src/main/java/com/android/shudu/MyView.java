package com.android.shudu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class MyView extends View{
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawLine(0,0,200,200,paint);
        super.onDraw(canvas);
    }
}
