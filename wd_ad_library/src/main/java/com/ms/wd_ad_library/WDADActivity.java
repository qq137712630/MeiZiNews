package com.ms.wd_ad_library;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wandoujia.ads.sdk.Ads;


public class WdAdActivity extends Activity {
  private static final String APP_ID = "100039950";
  private static final String SECRET_KEY = "0e6a87e2248e40ac4d1508abae1d79a0";
  private static final String BANNER = "9a2d72b9985262f8d0713bb40fcf813b";
  private static final String INTERSTITIAL = "7d84ef1e9c92b21f60a3c179d0bf09ef";
  private static final String APP_WALL = "3eeee0a420dd86d87ddc4432a4dc3600";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

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
