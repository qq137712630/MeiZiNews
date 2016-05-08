package com.ms.meizinewsapplication.features.video.iview;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ms.meizinewsapplication.R;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/5/5.
 */
public class M3u8VideoIView extends ViewImpl {


    private WebView web_view;

    @Override
    public void created() {
        super.created();
        web_view = findViewById(R.id.web_view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_m3u8_video;
    }


    public void onPause() {
        web_view.onPause();

    }

    public void onResume() {
        web_view.onResume();
    }


    //TODO init =================================================

    public void init(AppCompatActivity activity) {
        initWebView();
    }

    public void initWebView() {
        web_view.setVisibility(View.INVISIBLE);
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        web_view.setWebChromeClient(new WebChromeClient() {
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

    //TODO Mode ===================================================

    public void showDetail(String body) {

        //add css style to webView
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/dyindex.css\" type=\"text/css\">";
        String html = "<html><head>" +
                css +
                "</head><body>" +
                "<video width=\"100%\" height=\"100%\" preload=\"auto\"  type=a  src=\""
                + body +
                "\"></video>\n</body></html>";
        web_view.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }


}
