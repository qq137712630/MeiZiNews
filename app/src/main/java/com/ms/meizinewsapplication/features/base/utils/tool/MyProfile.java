package com.ms.meizinewsapplication.features.base.utils.tool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 啟成 on 2016/6/9.
 */
public class MyProfile {

    private static MyProfile myProfile = null;
    private SharedPreferences mySp;

    public MyProfile(Context context) {

        SharedPreferences mySp = context.getSharedPreferences("MyProfile", Context.MODE_PRIVATE);
    }

    public static synchronized MyProfile getInstance(Context context) {
        if (myProfile == null) {
            myProfile = new MyProfile(context);
        }
        return myProfile;
    }

    public void setString(String strKey, String strValue) {
        //存入数据
        SharedPreferences.Editor editor = mySp.edit();
        editor.putString(strKey, strValue);
        editor.apply();

    }

    public String getString(String strKey, String strValue)
    {
       return mySp.getString(strKey,strValue);
    }

    public void setTheme(boolean isDay) {

        if (isDay){

            setString(ConstantData.MY_PROFILE_THEME,ConstantData.MY_PROFILE_THEME_NIGHT);
        }else
        {
            setString(ConstantData.MY_PROFILE_THEME,ConstantData.MY_PROFILE_THEME_DAY);
        }

    }

    public String getTheme()
    {
        return getString(ConstantData.MY_PROFILE_THEME,ConstantData.MY_PROFILE_THEME_DAY);
    }

}
