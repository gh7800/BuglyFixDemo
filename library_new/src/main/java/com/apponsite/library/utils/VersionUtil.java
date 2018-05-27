package com.apponsite.library.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.apponsite.library.base.BaseApplication;

import java.io.File;

/**
 * Created by zhang on 2016/5/13.
 */
public class VersionUtil {
    public static int getVersionCode(Context context) {

        PackageManager packageManager = context.getPackageManager();

        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        int result = packageInfo.versionCode;

        return result;
    }

    public static int getVersionCode() {

        PackageManager packageManager = BaseApplication.context().getPackageManager();

        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(
                    BaseApplication.context().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        int result = packageInfo.versionCode;

        return result;
    }


    public static String getVersionName() {

        PackageManager packageManager = BaseApplication.context().getPackageManager();

        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(
                    BaseApplication.context().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String result = packageInfo.versionName;

        return result;
    }


    public static void setIntent(Context context,File result) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(result),
                "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * SIM卡唯一识别码
     * @param c
     * @return 460002831311135
     */
    public static String getIMSI(Context c) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) c
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = mTelephonyMgr.getSubscriberId();
        if (imsi == null) {
            return "";
        }
        return imsi;
    }

    public static String getPhone(Context c) {
        // 创建电话管理
        TelephonyManager tm = (TelephonyManager)
                // 与手机建立连接
                c.getSystemService(Context.TELEPHONY_SERVICE);
        // 获取手机号码
        String phoneId = tm.getLine1Number();
        if (phoneId == null) {
            phoneId = "";
        }
        return phoneId;
    }

    /**
     * 获取IMEI 手机设备唯一码
     *
     * @param c
     * @return 864264025502250
     */
    public static String getIMEI(Context c) {
        String imei = null;
        try {
            TelephonyManager mTelephonyMgr = (TelephonyManager) c
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imei = mTelephonyMgr.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            imei = Settings.Secure.getString(c.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        if (imei == null) {
            return "";
        }
        return imei;
    }




}
