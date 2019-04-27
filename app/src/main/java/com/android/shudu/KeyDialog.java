package com.android.shudu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.android.mj.R;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class KeyDialog extends Dialog {
    private final View keys[] = new View[9];
    private final int used[];
    private ShuduView shuduview;
    public KeyDialog(@NonNull Context context,int[] used,ShuduView shuduView) {
        super(context);
        this.used = used;
        this.shuduview = shuduView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Dialog");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shudu_keypad);
        findViews();
        for (int i = 0; i < used.length; i++) {
            if (used[i] != 0){
                keys[used[i] - 1].setVisibility(View.INVISIBLE);
            }
        }
        setListeners();
    }

    private void setListeners() {
        for (int i = 0; i < keys.length; i++) {
            final int t = i + 1;
            keys[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnResult(t);
                }
            });
        }
    }
    private void returnResult(int tile){
        shuduview.setSelectTile(tile);
        dismiss();
    }

    private void findViews() {
        keys[0] = findViewById(R.id.keypad1);
        keys[1] = findViewById(R.id.keypad2);
        keys[2] = findViewById(R.id.keypad3);
        keys[3] = findViewById(R.id.keypad4);
        keys[4] = findViewById(R.id.keypad5);
        keys[5] = findViewById(R.id.keypad6);
        keys[6] = findViewById(R.id.keypad7);
        keys[7] = findViewById(R.id.keypad8);
        keys[8] = findViewById(R.id.keypad9);
    }
}
