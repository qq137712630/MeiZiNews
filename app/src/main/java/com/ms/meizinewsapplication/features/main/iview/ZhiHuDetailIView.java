package com.ms.meizinewsapplication.features.main.iview;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.utils.tool.ImagerLoad;
import com.ms.meizinewsapplication.features.base.view.colorful.CollapsingToolbarLayoutSetter;
import com.ms.meizinewsapplication.features.base.view.iview.ColorfuWeblViewImpl;
import com.ms.meizinewsapplication.features.main.json.ZhihuDetail;
import com.ms.mythemelibrary.lib.Colorful;

/**
 * Created by 啟成 on 2016/3/10.
 */
public class ZhiHuDetailIView extends ColorfuWeblViewImpl {

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

        webView = findViewById(R.id.web_view);

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


    public boolean onCreateOptionsMenu(AppCompatActivity activity, Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        activity.getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }


    //TODO init =================================================


    @Override
    public void init(AppCompatActivity activity) {

        initToolbar(activity);
        initWebView(activity);
        super.init(activity);
    }

    @Override
    public void initColorful(Activity activity) {

        CollapsingToolbarLayoutSetter collapsingToolbarLayoutSetter = new CollapsingToolbarLayoutSetter(toolbar_layout, R.attr.colorPrimary);

        mColorful = new Colorful.Builder(activity)
                .setter(collapsingToolbarLayoutSetter)
                .backgroundColor(R.id.coordinator_layout, R.attr.root_view_bg)
                .create();
        setTheme(isDay);
//        setStatusBarTheme(isDay, activity);

    }

    /**
     * 设置左上角的返回键与它的点击效果
     *
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

    public void setMenuItemIconByCollect(boolean isCollect) {

        if (isCollect) {
            setMenuItemIcon(2, R.drawable.iconfont_weishoucang);
        } else {
            setMenuItemIcon(2, R.drawable.iconfont_yishoucang);
        }

    }


    public void setMenuItemIcon(int index, @DrawableRes int iconRes) {
        toolbar.getMenu().getItem(index).setIcon(iconRes);
    }


    public void initWebView(Context context) {
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

        webView.setWebViewClient(new MyWebViewClient());
    }


    //TODO Mode ===================================================

    public void initImg(Context context, String url) {
        ImagerLoad.load(context, url, detail_img);
    }

    public void progressGone() {
        progress.setVisibility(View.GONE);
    }


    public void showDetail(ZhihuDetail detailNews) {

        toolbar_layout.setTitle(detailNews.getTitle());
        String css;

        //add css style to webView
        if(isDay){
            css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        }else {
            css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news_night.css\" type=\"text/css\">";
        }

        String html = "<html><head>" + css + "</head><body>" + detailNews.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "") + dayTheme();
        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }

    public void showDetail(String title, String html) {
        ZhihuDetail detailNews = new ZhihuDetail();
        detailNews.setTitle(title);
        detailNews.setBody(html);
        showDetail(detailNews);
    }

    //TODO Listener====================

    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener onMenuItemClickListener) {

        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    public void setFabOnClickListener(View.OnClickListener fabOnClickListener) {
        fab.setOnClickListener(fabOnClickListener);
    }
}
