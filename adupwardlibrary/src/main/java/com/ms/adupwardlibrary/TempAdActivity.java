package com.ms.adupwardlibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;

import com.adupward.lib.AdBannerViewManager;
import com.adupward.lib.AdInterstitialViewManager;
import com.adupward.lib.AdShellViewActivity;
 
public class TempAdActivity extends AppCompatActivity
{
	AdBannerViewManager myadbview=null;
	AdInterstitialViewManager myadiview=null;
 	
	Button b=null;
	///method 1 ,no need of layout file ,recommended
	///第一种使用方式 ， 不需要布局文件（layout）,直接调用，推荐使用
	private void usage1()
	{
		setContentView(R.layout.layout1);
		 
		myadbview = new AdBannerViewManager(this);
		myadbview.setPosition(AdBannerViewManager.POSITION_BOTTOM); //you can set banner to top (你可以把这个设置到顶上)
		myadbview.show();
		
		
		myadiview = new AdInterstitialViewManager(this); 
		myadiview.show();
	}
	
	/// method 2, need of layout file ,you can control the position of the advertisment
	/// for proper display bannercontainer 		 least size:  320x50 dip
	/// for proper display interstitialcontainer least size:  300x250 dip
	/// 第二种使用方式，需要配合layout使用,可以控制广告的具体位置
	/// 为了正常显示广告 一定注意     bannercontainer 	   :至少 320x50 dip
	/// 为了正常显示广告  一定注意    interstitialcontainer :至少 300x250  dip
	private void usage2()
	{
		setContentView(R.layout.layout2);
		myadbview = new AdBannerViewManager(this,(ViewGroup)this.findViewById(R.id.bannercontainer));
		myadbview.show();
		
		myadiview=new AdInterstitialViewManager(this,(ViewGroup)this.findViewById(R.id.interstitialcontainer));  
		myadiview.show();
	}
	
	
	///第三种方式，只需要Context
	private void usage3(Context context)
	{
		setContentView(R.layout.layout3);
		
		AdShellViewActivity.showMe(TempAdActivity.this);
		
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setTitle("谢谢你的支持！");
		this.usage1();
		
		//you can comment off the line below and comment usage1() above to use custom adview
		//你可以把下面的注释去掉把上面的 usage1()注释上来使用自定义位置的广告
		//this.usage2();  
		
		//this.usage3(this.getApplicationContext());
		
//		this.usage3(this.getApplicationContext());
	 
	}
	
	

	@Override
	protected void onDestroy() 
	{
		if(myadbview!=null)
		{
			myadbview.destroy();
		}
		
		if(myadiview!=null)
		{
			myadiview.destroy();
		}
		 
		super.onDestroy();
	}

	@Override
	protected void onResume() 
	{
		super.onResume();
	}
}
