#AhSatyr

 - [性能监听：BlockCanary](https://github.com/moduth/blockcanary)
 - [MVP代码框架：MVPro](https://github.com/qibin0506/MVPro)
 - [增加Model层：框架模式MVP在Android中的使用](http://blog.csdn.net/feiduclear_up/article/details/46374653)
 - [日志：logger](https://github.com/orhanobut/logger)
 - [ RxJava 使用：给 Android 开发者的 RxJava 详解](http://gank.io/post/560e15be2dca930e00da1083#toc_26)
 - [网络请求：retrofit+okhttp3](https://github.com/square/retrofit)

---

#Retrofit 使用

##转换器

[retrofit](http://square.github.io/retrofit/#restadapter-configuration)
[RxAndroid+Retrofit环境搭建](http://www.07net01.com/program/2016/02/1280821.html)

转换器可以被添加到支持其他类型。六兄弟模块适应流行的序列化库为您提供方便。
Converters can be added to support other types. Six sibling modules adapt popular serialization libraries for your convenience.

	Gson: com.squareup.retrofit2:converter-gson
	Jackson: com.squareup.retrofit2:converter-jackson
	Moshi: com.squareup.retrofit2:converter-moshi
	Protobuf: com.squareup.retrofit2:converter-protobuf
	Wire: com.squareup.retrofit2:converter-wire
	Simple XML: com.squareup.retrofit2:converter-simplexml
	Scalars (primitives, boxed, and String): com.squareup.retrofit2:converter-scalars

直接返回String类型需引入：ScalarsConverterFactory.create()

	retrofit = new Retrofit.Builder()
			.client(MyOkHttpClient.getMyOkHttpClient().getOkHttpClient())//设置不同的底层网络库
			.baseUrl(strBaseUrl)
			.addConverterFactory(ScalarsConverterFactory.create())//添加 String类型[ Scalars (primitives, boxed, and String)] 转换器
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
			.build();
			
返回Gson类型需引入：GsonConverterFactory.create()

	retrofit = new Retrofit.Builder()
			.client(MyOkHttpClient.getMyOkHttpClient().getOkHttpClient())//设置不同的底层网络库
			.baseUrl(strBaseUrl)
			.addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
			.build();

##Retrofit 结合 RxJava使用

[Retrofit 结合 RxJava使用](http://blog.csdn.net/ttccaaa/article/details/50659234)

 1. 添加依赖
		
		compile 'com.squareup.retrofit2:retrofit:2.0.0-beta4'
		compile 'com.squareup.retrofit2:converter-gson:2.0.0-beta4'
		
		compile 'com.squareup.retrofit2:adapter-rxjava:2.0.0-beta4'
		
		compile 'io.reactivex:rxandroid:1.1.0'
		compile 'io.reactivex:rxjava:1.1.0'
		
	前两个是 Retrofit 和 Gson 的依赖，第三个是 Retrofit 中 RxJava 转换器的依赖，最后两个就是 RxJava 和 Rx Android 的依赖

 2. 编写 API

	使用 RxJava 的情况下，接口文件稍有修改，接口中的方法的返回类型不再是 Call 而是 Observable 类型：
		
		public interface GitHub {
		
		    @GET("/repos/{owner}/{repo}/contributors")
		    Call<List<Contributor>> contributors(@Path("owner") String owner,@Path("repo") String repo);
		
		    //使用 RxJava 的方法,返回一个 Observable
		    @GET("/repos/{owner}/{repo}/contributors")
		    Observable<List<Contributor>> RxContributors(@Path("owner") String owner,@Path("repo") String repo);
		}

	结合 RxJava 使用的 接口就定义好了，模型类不需要变动，接下来直接进行网络请求

 3. 网络请求

	使用 RxJava 的 Retrofit 可以直接在 主线程中编写。
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_main);
		
		    Retrofit retrofit=new Retrofit.Builder()
		            .baseUrl("https://api.github.com")
		            .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
		            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
		            .build();
		
		    GitHub gitHub=retrofit.create(GitHub.class);
		    gitHub.RxContributors("square","retrofit")
		            .subscribeOn(Schedulers.io())
		            .observeOn(AndroidSchedulers.mainThread())
		            .subscribe(new Subscriber<List<Contributor>>() {
		                @Override
		                public void onCompleted() {
		                    Log.i("TAG","onCompleted");
		                }
		
		                @Override
		                public void onError(Throwable e) {
		
		                }
		
		                @Override
		                public void onNext(List<Contributor> contributors) {
		                   for (Contributor c:contributors){
		                       Log.i("TAG","RxJava-->"+c.getLogin()+"  "+c.getId()+"  "+c.getContributions());
		                   }
		                }
		            });
		}

##使用Retrofit和Okhttp实现网络缓存。无网读缓存，有网根据过期时间重新请求

[okhttp 请求设置官方文档](https://github.com/square/okhttp/wiki/Recipes)
[使用Retrofit和Okhttp实现网络缓存。无网读缓存，有网根据过期时间重新请求](http://www.jianshu.com/p/9c3b4ea108a7#)

 okhttp3.X，retrofit:2.0.0-beta4适用

 1. 配置okhttp中的Cache
 
		OkHttpClient okHttpClient;
		File cacheFile = new File(DemoActivity.this.getCacheDir(), "[缓存目录]");
		Cache cache = new Cache(cacheFile, 1024 * 1024 * 10); //100Mb
		okHttpClient = new OkHttpClient.Builder()
		     .cache(cache)
		     .build();

 2. 配置请求头中的cache-control
	
	    //使用 RxJava 的方法,返回一个 Observable
	    @Headers("Cache-Control: public, max-age=3600")
	    @GET("/repos/{owner}/{repo}/contributors")
	    Observable<List<Contributor>> RxContributors(@Path("owner") String owner,@Path("repo") String repo);

 3. 配置请求 Retrofit 设置不同的底层网络库

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
                .build();

#API

## 知乎日报

    //Zhihu API
    public static final String BASE_URL = "http://news-at.zhihu.com/api/4/news/";//日报详情
    public static final String NEWS_LATEST = "http://news-at.zhihu.com/api/4/news/latest";//最新日报
    public static final String NEWS_BEFORE = "http://news-at.zhihu.com/api/4/news/before/";//指定日期的日报
    public static final String SPLASH = "http://news-at.zhihu.com/api/4/start-image/1080*1920";//封面

 - 日报详情
	 
	 BASE_URL+日报ID
	 [http://news-at.zhihu.com/api/4/news/7942211](http://news-at.zhihu.com/api/4/news/7942211)
	 
 - 最新日报
	 
	 直接访问：NEWS_LATEST
	 [http://news-at.zhihu.com/api/4/news/latest](http://news-at.zhihu.com/api/4/news/latest)
	 
 - 指定日期的日报
	 
	 NEWS_BEFORE+(指定日期+1天)，格式为全数字，YYYYMMDD,如：
	 2016年02月29号的数据：
	 [http://news-at.zhihu.com/api/4/news/before/20160301](http://news-at.zhihu.com/api/4/news/before/20160301)
 
## 豆瓣美女

    public static String DB_BREAST = "http://www.dbmeinv.com/dbgroup/show.htm?cid=2&pager_offset=";
    public static String DB_BUTT = "http://www.dbmeinv.com/dbgroup/show.htm?cid=6&pager_offset=";
    public static String DB_SILK = "http://www.dbmeinv.com/dbgroup/show.htm?cid=7&pager_offset=";
    public static String DB_LEG = "http://www.dbmeinv.com/dbgroup/show.htm?cid=3&pager_offset=";
    public static String DB_RANK="http://www.dbmeinv.com/dbgroup/rank.htm?pager_offset=";

---

#MD实践

##侧滑 NavigationView

最快的方法是创一下新的侧滑模块

 - [windowDrawsSystemBarBackgrounds:如何将DrawerLayout显示在ActionBar/Toolbar和status bar之间](http://solo.farbox.com/blog/how-do-i-use-drawerlayout-to-display-over-the-actionbar-or-toolbar-and-under-the-status-bar)
 - [Android M新控件之AppBarLayout，NavigationView，CoordinatorLayout，CollapsingToolbarLayout的使用](http://blog.csdn.net/feiduclear_up/article/details/46514791) 
 - [Android 自己实现 NavigationView [Design Support Library(1)]](http://blog.csdn.net/lmj623565791/article/details/46405409)

##上下拉：SwipeRefreshLayout
 
 - [Android SwipeRefreshLayout 官方下拉刷新控件介绍](http://blog.csdn.net/lmj623565791/article/details/24521483)
 - [RecyclerView完全解析之下拉刷新与上拉加载SwipeRefreshLayout](http://www.lcode.org/recyclerview%E5%AE%8C%E5%85%A8%E8%A7%A3%E6%9E%90%E4%B9%8B%E4%B8%8B%E6%8B%89%E5%88%B7%E6%96%B0%E4%B8%8E%E4%B8%8A%E6%8B%89%E5%8A%A0%E8%BD%BDswiperefreshlayout/)
 - [实例解析之SwipeRefreshLayout+RecyclerView+CardView](http://www.lcode.org/%E5%AE%9E%E4%BE%8B%E8%A7%A3%E6%9E%90%E4%B9%8Bswiperefreshlayoutrecyclerviewcardview/)
 - [Google官方下拉刷新组件--SwipeRefreshLayout](https://github.com/stormzhang/SwipeRefreshLayoutDemo)