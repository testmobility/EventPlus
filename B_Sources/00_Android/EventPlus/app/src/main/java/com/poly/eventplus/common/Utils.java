package com.poly.eventplus.common;

import android.content.Context;
import android.os.Environment;

import com.nmd.libs.SharedPreference;
import com.nmd.libs.UtilLibs;
import com.nmd.libs.UtilityMain;

import java.io.File;

/**
 * Created by Rua on 2/22/2016.
 */
public class Utils {
    public static final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "EventPlus";
    public static final String pathDirectoryImage = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Pictures" + File.separator + "EventPlus";

    public enum Keys {
        SCREEN_HEIGHT,
        SCREEN_WIDTH,
        AUTO_LOGIN,
        AUTO_LOGIN_FACEBOOK,
        GCM_ID,
        FACEBOOK_ID,
        NOTIFICATION_COUNT,
        USER_PROFILE_DATA,
        USER_ID,
        ACCESS_TOKEN,
        CATEGORY_LIST_DATA
    }

    public static void clearAllData(Context context){
        SharedPreference.set(context, Keys.AUTO_LOGIN, "");
        SharedPreference.set(context, Keys.AUTO_LOGIN_FACEBOOK, "");
        SharedPreference.set(context, Keys.FACEBOOK_ID, "");
        SharedPreference.set(context, Keys.NOTIFICATION_COUNT, "");
        SharedPreference.set(context, Keys.USER_PROFILE_DATA, "");
        SharedPreference.set(context, Keys.USER_ID, "");
        SharedPreference.set(context, Keys.ACCESS_TOKEN, "");
    }

    public static void upLog(Context context) {
        UtilityMain.isRecordLog = false;
        UtilityMain.DEBUG_D = false;
        UtilityMain.CRASH_RECORD = true;
        UtilityMain.DEBUG_N = false;
        UtilityMain.isUpLog = true;
        UtilityMain.start(context);

    }

    public static void createPathFileWishlist(){
        try {
            new File(path).mkdir();
            new File(pathDirectoryImage).mkdir();
            UtilLibs.writeToFileInSDCard(path, ".nomedia", "0");
            UtilLibs.writeToFileInSDCard(pathDirectoryImage, ".nomedia", "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
