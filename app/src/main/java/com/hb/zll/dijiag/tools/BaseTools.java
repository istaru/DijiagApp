package com.hb.zll.dijiag.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.hb.zll.dijiag.ciphertext.AES;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by superMoon on 2017/3/15.
 */
public class BaseTools {
    private static boolean isInitApp;//判断用户是否第一次安装
    private static int firstInstall;//1表示第一次安装
    private static boolean isEncryt = true;
    private static ImageView noneImg;

    /**
     * 获取屏幕的宽度
     */
    public final static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高度
     */
    public final static int getWindowsHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * px转化为dp
     */
    public final static float pxChangeDp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * dp转化为px
     */
    public final static float dpChangePx(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public final static int dp2px(Context context, float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    /**
     * 打开输入法
     */
    public final static void openInput(final EditText view){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 500);
    }

    /**
     * 关闭输入法
     */
    public final static void closeInput(Activity context, EditText view){
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public static void setBackgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().setAttributes(lp);
    }

    /**
     * APP是否第一次安装
     * @param context
     * @return
     */
    public static int isFirstEnter(Context context){
        SharedPreferences preferences = context.getSharedPreferences("isInitApp",0);//读取SharedPreferences中需要的数据
        isInitApp = preferences.getBoolean("isInitApp", true);
        if(isInitApp) {//是第一次安装
            firstInstall = 1;
        } else {//不是第一次安装
            firstInstall = 0;
        }
        SharedPreferences.Editor editor = preferences.edit();//实例化Editor对象
        editor.putBoolean("isInitApp", false);//存入数据
        editor.commit();//提交修改
        return firstInstall;
    }

    /**
     * 验证是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if(!TextUtils.equals("",str)){
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);
            if(!isNum.matches() ){
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static void getNetWorkBitmap(final String urlString, final ImageView imageView) {
        Runnable getAndSaveImageRunnable = new Runnable() {
            @Override
            public void run() {
                URL imgUrl = null;
                Bitmap bitmap = null;
                try {
                    imgUrl = new URL(urlString);
                    HttpURLConnection urlConn = (HttpURLConnection) imgUrl.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.connect();
                    InputStream is = urlConn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    imageView.setImageBitmap(bitmap);
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(getAndSaveImageRunnable).start();
    }

    /**
     * 创建文件夹
     * @param fileName 文件夹的名称
     * @return
     */
    public static File makeFile(String fileName){
        File appDir = new File(Environment.getExternalStorageDirectory(), "dijiag" + "/" + fileName);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        return appDir;
    }

    /**
     * 判断是否有网络
     * @return 返回值
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            @SuppressLint("MissingPermission") NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 加密方法
     * @param data
     * @return
     */
    public static String encodeJson(String data){
        if(isEncryt == true){
            long secret = AES.get10Random();
            String content = AES.encrypt(data, AES.md5(AES.longMinusNum(secret+"")));
            JSONObject jsonEncrypt = new JSONObject();
            jsonEncrypt.put("secret",secret);
            jsonEncrypt.put("content",content);
            return jsonEncrypt.toString();
        } else {
            return data;
        }
    }

    /**
     * 获取参数并解密
     * @param json
     * @return
     */
    public static String decryptJson(String json){
        if(isEncryt == true){
            String result = "";
            try {
                JSONObject jsonObject = JSONObject.parseObject(json);
                String secret = jsonObject.getString("secret");
                String content = jsonObject.getString("content");
                result = AES.decrypt(content, AES.md5(AES.longMinusNum(secret)));
            } catch (Exception e){
                result = json;
                e.printStackTrace();
            }
            return result;
        }
        return json;
    }
}