package com.ms.meizinewsapplication.features.base.view.iview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 啟成 on 2016/6/11.
 */
public abstract class ColorfuWeblViewImpl extends ColorfulViewImpl {


    protected boolean isFinish = true;


    public class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {


            Uri uri = Uri.parse(url); //url为你要链接的地址

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            activity.startActivity(intent);

            return true;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // 加载开始
            Log.d("WebView", "onPageStarted");
            super.onPageStarted(view, url, favicon);
        }

        public void onPageFinished(WebView view, String url) {
            // 加载结束
            Log.d("WebView", "onPageFinished ");

            if (isDay) {

                view.loadUrl(
                        "javascript:{" +
                                "load_day()" +
                                "};"
                );
            } else {

                view.loadUrl(
                        "javascript:{" +
                                "load_night()" +
                                "};"
                );
            }

        }
    }

    protected String dayTheme() {
        return "<script language=javascript>\n" +
                "function load_night() \n" +
                "{\n" +
                "\n" +
                "\tdocument.bgColor=\"#000000\"\n" +
                "\tdocument.body.text=\"white\"\n" +
                "\tdocument.body.link =\"blue\"\n" +
                "\tdocument.body.vlink = \"red\"" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "<script language=\"javascript\">\n" +
                "    function load_day(){\n" +
                "\n" +
                "\tdocument.bgColor=\"#ffffff\"\n" +
                "\tdocument.body.text=\"black\"\n" +
                "\tdocument.body.link =\"blue\"\n" +
                "\tdocument.body.vlink = \"red\"" +
                "    }\n" +
                "</script>";
    }
}
