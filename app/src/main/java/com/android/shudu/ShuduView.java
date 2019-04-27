package com.android.shudu;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.mj.R;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class ShuduView extends View {
    private float height;
    private float width;
    private int selectX;
    private int selectY;
    private Game game = new Game();
    public ShuduView(Context context) {
        super(context);
    }

    public ShuduView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShuduView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ShuduView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.height = h / 9.0f;
        this.width = w / 9.0f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    @TargetApi(23)
    protected void onDraw(Canvas canvas) {
        Paint background_paint = new Paint();
        background_paint.setColor(getResources().getColor(R.color.shudu_background,null));
        canvas.drawRect(0,0,getWidth(),getHeight(),background_paint);

        Paint dark_paint = new Paint();
        dark_paint.setColor(getResources().getColor(R.color.shudu_dark));

        Paint hilite_paint = new Paint();
        hilite_paint.setColor(getResources().getColor(R.color.shudu_hilite));

        Paint light_paint = new Paint();
        light_paint.setColor(getResources().getColor(R.color.shudu_light));

        for (int i = 0; i < 9; i++) {
            canvas.drawLine(0,i * height,getWidth(),i * height,light_paint);
            canvas.drawLine(0,i * height + 1,getWidth(), i * height + 1,hilite_paint);
            canvas.drawLine(i * width,0,i * width,getHeight(),light_paint);
            canvas.drawLine(i * width + 1,0,i * width + 1,getHeight(),hilite_paint);
        }
        for (int i = 0; i < 9; i++) {
            if (i % 3 != 0){
                continue;
            }
            canvas.drawLine(0,i * height,getWidth(),i * height,dark_paint);
            canvas.drawLine(0,i * height + 1,getWidth(), i * height + 1,hilite_paint);
            canvas.drawLine(i * width,0,i * width,getHeight(),dark_paint);
            canvas.drawLine(i * width + 1,0,i * width + 1,getHeight(),hilite_paint);
        }
        //绘制数字
        Paint number_paint = new Paint();
        number_paint.setColor(Color.BLACK);
        number_paint.setStyle(Paint.Style.STROKE);
        number_paint.setTextSize(height * 0.75f);
        number_paint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fm = number_paint.getFontMetrics();
        float x = width / 2;
        float y = height / 2 - (fm.ascent + fm.descent) / 2;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                canvas.drawText(game.getTitleString(i,j),i * width + x,j * height + y,number_paint);
            }
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN){
            return super.onTouchEvent(event);
        }
        selectX = (int)(event.getX() / width);
        selectY = (int)(event.getY() / height);

        int used[] = game.getUsedTilesByCoor(selectX,selectY);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < used.length; i++) {
            stringBuffer.append(used[i]);
        }
        KeyDialog keyDialog = new KeyDialog(getContext(),used,this);
        keyDialog.show();
       /* LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        View layoutView = layoutInflater.inflate(R.layout.shudu_dialog,null);
        TextView textView = layoutView.findViewById(R.id.usedTextId);
        textView.setText(stringBuffer.toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setView(layoutView);
        AlertDialog shudu_dialog = builder.create();
        shudu_dialog.show();*/
        return true;
    }
    public void setSelectTile(int tile){
        if (game.setTileIfValid(selectX,selectY,tile)){
            invalidate();
        }
    }

}
