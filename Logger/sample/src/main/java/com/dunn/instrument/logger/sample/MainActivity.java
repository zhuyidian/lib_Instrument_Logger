package com.dunn.instrument.logger.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dunn.instrument.logger.InitJointPoint;
import com.dunn.instrument.logger.LogJointPoint;
import com.dunn.instrument.logger.ReleaseJointPoint;
import com.dunn.instrument.logger.UploadJointPoint;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends Activity {
    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Log.i(TAG,"click:");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //初始化logger的地方
    @InitJointPoint(mFilePath = "/logger",mFileName = "TTT_logger_cache",isDebug = true)
    public static void loggerInit(){
    }

    //释放logger的地方
    @ReleaseJointPoint
    public static void loggerRelease(){}

    //收集日志的地方
    @LogJointPoint(type = "MSG", open = true)
    public static void logOut(String msg) {

    }

    //上传日志的地方
    @UploadJointPoint
    public static <T> void uploadLogger(T value, String url, String token, String userId) {

    }
}
