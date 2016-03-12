package com.ms.meizinewsapplication.features.main.iview;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.ms.meizinewsapplication.utils.tool.Share;

import org.loader.view.ViewImpl;

/**
 * Created by 啟成 on 2016/3/10.
 */
public class ZhiHuDetailIView extends ViewImpl {

    private CollapsingToolbarLayout toolbar_layout;
    private ImageView detail_img;
    private ProgressBar progress;
    private FloatingActionButton fab;
    private FrameLayout webContainer;
    private WebView webView;
    private Toolbar toolbar;

    @Override
    public void created() {
        super.created();
        detail_img = findViewById(R.id.detail_img);
        progress = findViewById(R.id.progress);
        webContainer = findViewById(R.id.web_container);
        fab = findViewById(R.id.fab);
        toolbar_layout = findViewById(R.id.toolbar_layout);
        toolbar = findViewById(R.id.toolbar);
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

    public void onBackPressed() {
        webView.setVisibility(View.GONE);
        webContainer.removeAllViews();
    }


    //TODO init =================================================

    public void init(AppCompatActivity activity) {
        initToolbar(activity);
        initWebView(activity);
        initFAB(activity);
    }

    /**
     * 设置左上角的返回键与它的点击效果
     * @param appCompatActivity
     */
    private void initToolbar(final AppCompatActivity appCompatActivity) {

        appCompatActivity.setSupportActionBar(toolbar);

        if (appCompatActivity.getSupportActionBar() == null) {
            return;
        }

        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        [android：ToolBar详解（手把手教程）](http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1118/2006.html)
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    appCompatActivity.finishAfterTransition();
                } else {
                    appCompatActivity.finish();
                }
            }
        });

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
                            progressGone();
                            view.setVisibility(View.VISIBLE);
                        }
                    }, 300);
                }
            }
        });
    }

    private void initFAB(final Context context) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detailNews == null) {
                    return;
                }
                Share.shareText(context, detailNews.getShare_url());
            }
        });
    }

    //TODO Mode ===================================================

    public void initImg(Context context, String url) {
        ImagerLoad.load(context, url, detail_img);
    }

    public void progressGone() {
        progress.setVisibility(View.GONE);
    }

    private ZhihuDetail detailNews;

    public void showDetail(ZhihuDetail detailNews) {
        this.detailNews = detailNews;

        toolbar_layout.setTitle(detailNews.getTitle());

        //add css style to webView
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + detailNews.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }
}
