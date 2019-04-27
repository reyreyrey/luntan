package com.android.tuan.ui;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment2;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.android.tuan.R;
import com.android.tuan.databinding.DialogPhotoviewBinding;
import com.bumptech.glide.Glide;


/**
 * author: Rea.X
 * date: 2018/1/3.
 */

public class PhotoViewDialog extends DialogFragment2 {

    private DialogPhotoviewBinding binding;
    private String src;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_photoview, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(src != null){
            Glide.with(getActivity()).load(src).into(binding.photoView);
        }
        binding.photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissAllowingStateLoss();
            }
        });
    }

    public void setImage(String src){
        this.src = src;
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_photoview, null);
//
//        final Dialog shudu_dialog = new Dialog(getActivity(), R.style.style_dialog1);
//        shudu_dialog.setContentView(view);
//        shudu_dialog.setCanceledOnTouchOutside(true);//设置点击外边缘d对话框消失
//        shudu_dialog.show();
//
//        Window window = shudu_dialog.getWindow();
//        window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
//        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
//
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(lp);
//        return shudu_dialog;
//
//    }
}
