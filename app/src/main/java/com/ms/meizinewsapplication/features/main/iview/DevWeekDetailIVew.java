package com.ms.meizinewsapplication.features.main.iview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.ms.meizinewsapplication.R;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/3/22.
 */
public class DevWeekDetailIVew extends ViewImpl {


    private FrameLayout webContainer;
    private WebView webView;

    @Override
    public void created() {
        super.created();
        webContainer = findViewById(R.id.web_container);
    }

    @Override
    public int getLayoutId() {
        return R.layout.app_bar_dev_week_detail;
    }


    public void onPause() {
        webView.onPause();

    }

    public void onResume() {
        webView.onResume();
    }

    public void onBackPressed() {
        webView.setVisibility(View.GONE);
        webContainer.removeAllViews();
    }

    //TODO init =================================================

    public void init(AppCompatActivity activity) {
        initWebView(activity);
    }

    public void initWebView(Context context) {
        webView = new WebView(context);
        webContainer.addView(webView);
        webView.setVisibility(View.INVISIBLE);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(final WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.setVisibility(View.VISIBLE);
                        }
                    }, 300);
                }
            }
        });
    }

    public void showDetail(String html) {
        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }
}
