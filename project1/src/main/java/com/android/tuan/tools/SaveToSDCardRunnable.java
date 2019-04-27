package com.android.tuan.tools;

import android.os.Environment;

import com.android.mj.tools.LogUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SaveToSDCardRunnable implements Runnable {
    private String folder;
    private String fileName;
    private String message;

    public SaveToSDCardRunnable(String message) {
        this.folder = Environment.getExternalStorageDirectory()+File.separator+"json";
        this.fileName = "json.txt";
        this.message = message;
    }

    @Override
    public void run() {
        try {
            File file = new File(folder, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(message.getBytes());
            outputStream.flush();
            outputStream.close();
            LogUtil.e("====>结束1");
        } catch (Exception e) {
            LogUtil.e("====>结束2"+e.toString());
            e.printStackTrace();
        }
    }
}
