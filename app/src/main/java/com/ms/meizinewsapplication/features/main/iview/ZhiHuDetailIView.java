package com.ms.meizinewsapplication.features.main.iview;

import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.main.json.ZhihuDetail;
import com.ms.meizinewsapplication.utils.tool.ImagerLoad;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/3/10.
 */
public class ZhiHuDetailIView extends ViewImpl {

    private ImageView detail_img;
    private ProgressBar progress;
    private FrameLayout webContainer;
    private WebView webView;

    @Override
    public void created() {
        super.created();
        detail_img = findViewById(R.id.detail_img);
        progress = findViewById(R.id.progress);
        webContainer = findViewById(R.id.web_container);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    public void onPause() {
        webView.onPause();

    }

    public void onResume() {
        webView.onResume();
    }

    //TODO init =================================================

    public void init(Context context) {
        initWebView(context);
    }
    public void initWebView(Context context)
    {
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


    //TODO Mode ===================================================

    public void initImg(Context context, String url) {
        ImagerLoad.load(context, url, detail_img);
    }

    public void progressGone()
    {
        progress.setVisibility(View.GONE);
    }



    public void showDetail(ZhihuDetail detailNews) {
        //add css style to webView
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + detailNews.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }
}
