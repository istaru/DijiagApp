package com.hb.zll.dijiag.tools;

import android.content.Context;
import android.os.Looper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.hb.zll.dijiag.application.Application;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by Moon on 2018/4/27.
 */

public class GlideCatchUtil implements GlideModule {
    private static GlideCatchUtil instance;

    public static GlideCatchUtil getInstance() {
        if (null == instance) {
            instance = new GlideCatchUtil();
        }
        return instance;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);//设置图片的显示格式ARGB_8888(指图片大小为32bit)
        int cacheSize = 100 * 1000 * 1000;//设置大小
        //设置磁盘缓存目录（和创建的缓存目录相同）
//        File storageDirectory = Environment.getExternalStorageDirectory();
//        String downloadDirectoryPath = storageDirectory + "/GlideCache";
        File storageDirectory = BaseTools.makeFile("imageCache");
        builder.setDiskCache(new DiskLruCacheFactory(storageDirectory.getPath(), cacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }

    /**
     * 获取Glide磁盘缓存大小
     */
    public String getCacheSize() {
        try {
            return getFormatSize(getFolderSize(new File(BaseTools.makeFile("imageCache").getPath())));
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败";
        }
    }

    /**
     * 清除Glide磁盘缓存，自己获取缓存文件夹并删除方法
     */
    public boolean cleanCatchDisk() {
        return deleteFolderFile(BaseTools.makeFile("imageCache").getPath(), true);
    }

    /**
     * 清除图片磁盘缓存，调用Glide自带方法
     */
    public boolean clearCacheDiskSelf() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(Application.getInstance()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(Application.getInstance()).clearDiskCache();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 清除Glide内存缓存
     */
    public boolean clearCacheMemory() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(Application.getInstance()).clearMemory();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取指定文件夹内所有文件大小的和
     */
    private long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     */
    private static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 按目录删除文件夹文件方法
     */
    private boolean deleteFolderFile(String filePath, boolean deleteThisPath) {
        try {
            File file = new File(filePath);
            if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (File file1 : files) {
                    deleteFolderFile(file1.getAbsolutePath(), true);
                }
            }
            if (deleteThisPath) {
                if (!file.isDirectory()) {
                    file.delete();
                } else {
                    if (file.listFiles().length == 0) {
                        file.delete();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
