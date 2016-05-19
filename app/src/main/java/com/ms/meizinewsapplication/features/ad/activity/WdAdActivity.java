package com.ms.meizinewsapplication.features.ad.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ms.meizinewsapplication.R;
import com.wandoujia.ads.sdk.Ads;


public class WdAdActivity extends Activity {
  private static final String APP_ID = "100039974";
  private static final String SECRET_KEY = "9e87bcc2dab21b3e6833f95869ba1edb";
  private static final String BANNER = "cb9216af984add6fc62db617823222f5";
  private static final String INTERSTITIAL = "0889f8851be4e5fe71c983758a42380a";
  private static final String APP_WALL = "16711e13ea9e02f93acd9ab8dbf06f05";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wd_ad);

    new AsyncTask<Void, Void, Boolean>() {
      @Override
      protected Boolean doInBackground(Void... params) {
        try {
          Ads.init(WdAdActivity.this, APP_ID, SECRET_KEY);
          return true;
        } catch (Exception e) {
          Log.e("ads-sample", "error", e);
          return false;
        }
      }

      @Override
      protected void onPostExecute(Boolean success) {
        final ViewGroup container = (ViewGroup) findViewById(R.id.banner_container);

        if (success) {
          /**
           * pre load
           */
          Ads.preLoad(BANNER, Ads.AdFormat.banner);
          Ads.preLoad(INTERSTITIAL, Ads.AdFormat.interstitial);
          Ads.preLoad(APP_WALL, Ads.AdFormat.appwall);

          /**
           * add ad views
           */
          View bannerView = Ads.createBannerView(WdAdActivity.this, BANNER);
          container.addView(bannerView, new ViewGroup.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.WRAP_CONTENT
          ));

          Button btI = new Button(WdAdActivity.this);
          btI.setText("interstitial");
          btI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Ads.showInterstitial(WdAdActivity.this, INTERSTITIAL);
            }
          });
          container.addView(btI);

          Button btW = new Button(WdAdActivity.this);
          btW.setText("app wall");
          btW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Ads.showAppWall(WdAdActivity.this, APP_WALL);
            }
          });
          container.addView(btW);
        } else {
          TextView errorMsg = new TextView(WdAdActivity.this);
          errorMsg.setText("init failed");
          container.addView(errorMsg);
        }
      }
    }.execute();
  }
}
