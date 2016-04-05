#AhSatyr

 - [性能监听：BlockCanary](https://github.com/moduth/blockcanary)
 - [MVP代码框架：MVPro](https://github.com/qibin0506/MVPro)
 - [增加Model层：框架模式MVP在Android中的使用](http://blog.csdn.net/feiduclear_up/article/details/46374653)
 - [日志：logger](https://github.com/orhanobut/logger)
 - [ RxJava 使用：给 Android 开发者的 RxJava 详解](http://gank.io/post/560e15be2dca930e00da1083#toc_26)
 - [网络请求：retrofit+okhttp3](https://github.com/square/retrofit)
 - [图片加载：glide](https://github.com/bumptech/glide)
 - [搜索控件：SearchView](https://github.com/lapism/SearchView)
 - [数据库框架：GreenDAO](https://github.com/greenrobot/greenDAO)

---

#GreenDAO

 - [GreenDAO数据库版本升级](http://blog.csdn.net/fancylovejava/article/details/46713445)

---

#Retrofit 使用

[Retrofit 2.0 + OkHttp 3.0 配置](https://drakeet.me/retrofit-2-0-okhttp-3-0-config)

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
[OuNews 新闻：RetrofitManager类下的 initOkHttpClient 方法](https://github.com/oubowu/OuNews)
 okhttp3.X，retrofit:2.0.0-beta4适用

 1. 配置okhttp中的Cache
 
		OkHttpClient okHttpClient;
		File cacheFile = new File(DemoActivity.this.getCacheDir(), "[缓存目录]");
		Cache cache = new Cache(cacheFile, 1024 * 1024 * 10); //100Mb
		okHttpClient = new OkHttpClient.Builder()
		     .cache(cache)
		     .build();
		
	栗子：
	
		// 完成缓存
		public class MyOkHttpClient {
		
		    //设缓存有效期为两天
		    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
		    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
		    protected static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
		    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
		    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";
		
		    private String TAG = "MyOkHttpClient";
		
		    private OkHttpClient okHttpClient;
		    private static MyOkHttpClient myOkHttpClient;
		
		    public static MyOkHttpClient getMyOkHttpClient() {
		        if (myOkHttpClient == null) {
		            myOkHttpClient = new MyOkHttpClient();
		
		        }
		
		        return myOkHttpClient;
		    }
		
		    /**
		     * 初始化
		     *
		     * @param mContext
		     */
		    public void init(final Context mContext) {
		
		        if (okHttpClient != null) {
		            return;
		        }
		
		
		        Log.e(TAG, "初始化okHttpClient");
		        // 因为BaseUrl不同所以这里Retrofit不为静态，但是OkHttpClient配置是一样的,静态创建一次即可
		        File cacheFile = new File(
		                mContext.getCacheDir(),
		                "HttpCache"
		        ); // 指定缓存路径
		        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 指定缓存大小100Mb
		        // 云端响应头拦截器，用来配置缓存策略
		        Interceptor rewriteCacheControlInterceptor = new Interceptor() {
		            @Override
		            public Response intercept(Chain chain) throws IOException {
		                Request request = chain.request();
		                if (!NetUtil.isConnected(mContext)) {
		                    request = request.newBuilder()
		                            .cacheControl(CacheControl.FORCE_CACHE).build();
		                    Log.e(TAG, "no network");
		                }
		                Response originalResponse = chain.proceed(request);
		                if (NetUtil.isConnected(mContext)) {
		                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
		                    String cacheControl = request.cacheControl().toString();
		                    return originalResponse.newBuilder()
		                            .header("Cache-Control", cacheControl)
		                            .removeHeader("Pragma").build();
		                } else {
		                    return originalResponse.newBuilder().header("Cache-Control",
		                            "public, only-if-cached," + CACHE_STALE_SEC)
		                            .removeHeader("Pragma").build();
		                }
		            }
		        };
		        okHttpClient = new OkHttpClient.Builder().cache(cache)
		                .addNetworkInterceptor(rewriteCacheControlInterceptor)
		                .addInterceptor(rewriteCacheControlInterceptor)
		                .connectTimeout(30, TimeUnit.SECONDS).build();
		
		    }
		
		    public OkHttpClient getOkHttpClient() {
		        return okHttpClient;
		    }
		
		    /**
		     * 根据网络状况获取缓存的策略
		     *
		     * @return
		     */
		    @NonNull
		    public static String getCacheControl(Context mContext) {
		        return NetUtil.isConnected(mContext) ? CACHE_CONTROL_NETWORK : CACHE_CONTROL_CACHE;
		    }
		
		}


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

## 问题集

 - [Retrofit error URL query string must not have replace block](http://stackoverflow.com/questions/24610243/retrofit-error-url-query-string-must-not-have-replace-block)

## 其他笔记

@Path:路径

@Query:查询条件，如：     

	xx=yy

如果有多个查询条件可以使用: ` @QueryMap ` ;

---

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

## Android开发技术周报

网址

[http://androidweekly.cn](http://androidweekly.cn)

例子：

[http://androidweekly.cn/page/2/](http://androidweekly.cn/page/2/)

---

#MD实践

 - [Android Design Support Library使用详解](http://blog.csdn.net/eclipsexys/article/details/46349721)
 - [NestedScrollView： 兼容MD的 ScrollView ](http://blog.csdn.net/hangeqq685042/article/details/48129911)
 - [Android L动画入门](http://blog.jobbole.com/77015/)
 - [ANDROID L——Material Design详解（动画篇）](http://blog.csdn.net/a396901990/article/details/40187203)
 - [ Android M新控件之FloatingActionButton，TextInputLayout，Snackbar，TabLayout的使用](http://blog.csdn.net/feiduclear_up/article/details/46500865)
 - [Android M新控件之AppBarLayout，NavigationView，CoordinatorLayout，CollapsingToolbarLayout的使用](http://blog.csdn.net/feiduclear_up/article/details/46514791) 

##侧滑 NavigationView

最快的方法是创一下新的侧滑模块

 - [windowDrawsSystemBarBackgrounds:如何将DrawerLayout显示在ActionBar/Toolbar和status bar之间](http://solo.farbox.com/blog/how-do-i-use-drawerlayout-to-display-over-the-actionbar-or-toolbar-and-under-the-status-bar)
 - [Android 自己实现 NavigationView [Design Support Library(1)]](http://blog.csdn.net/lmj623565791/article/details/46405409)

##上下拉：SwipeRefreshLayout
 
 - [Android SwipeRefreshLayout 官方下拉刷新控件介绍](http://blog.csdn.net/lmj623565791/article/details/24521483)
 - [RecyclerView完全解析之下拉刷新与上拉加载SwipeRefreshLayout](http://www.lcode.org/recyclerview%E5%AE%8C%E5%85%A8%E8%A7%A3%E6%9E%90%E4%B9%8B%E4%B8%8B%E6%8B%89%E5%88%B7%E6%96%B0%E4%B8%8E%E4%B8%8A%E6%8B%89%E5%8A%A0%E8%BD%BDswiperefreshlayout/)
 - [实例解析之SwipeRefreshLayout+RecyclerView+CardView](http://www.lcode.org/%E5%AE%9E%E4%BE%8B%E8%A7%A3%E6%9E%90%E4%B9%8Bswiperefreshlayoutrecyclerviewcardview/)
 - [Google官方下拉刷新组件--SwipeRefreshLayout](https://github.com/stormzhang/SwipeRefreshLayoutDemo)
 
## 头部： CoordinatorLayout CollapsingToolbarLayout
	
	

	
 - [CoordinatorLayout与滚动的处理](http://www.open-open.com/lib/view/open1437312265428.html)
 - [该使用 fitsSystemWindows 了！](http://ju.outofmemory.cn/entry/247350)或[该使用 fitsSystemWindows 了！]( http://blog.chengyunfeng.com/?p=905)

	    以哪个为固定:
	    android:fitsSystemWindows="true"
 
### CoordinatorLayout

 - [Android 组件属性](http://blog.csdn.net/mg505/article/details/7714658)
 
 总结： 为了使得Toolbar有滑动效果，必须做到如下三点：
 
 注：CoordinatorLayout如果是最外的布局要去掉：
 
	
 
 1、CoordinatorLayout必须作为整个布局的父布局容器。
 2、 给需要滑动的组件设置 app:layout_scrollFlags=”scroll|enterAlways” 属性。
 3、给你的可滑动的组件，也就是RecyclerView 或者 NestedScrollView 设置如下属性：
 
	app:layout_behavior="@string/appbar_scrolling_view_behavior"

---

# RecyclerView 

[RecyclerView使用介绍](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1118/2004.html)
[base-adapter-helper的RecyclerView版](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0809/3277.html)

## 上下拉监听

`SwipeRefreshLayout.OnRefreshListener` 下拉监听

`RecyclerView.OnScrollListener` 到底监听    

[Android一点 RecyclerView上拉刷新](http://blog.csdn.net/qqqgl/article/details/50353642)
	
	recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
	            @Override
	            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
	                super.onScrollStateChanged(recyclerView, newState);
	                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
	                    onListScrolled();
	                }
	            }
	        });
	        
## RecyclerView多Item的情况

[RecyclerView多Item的情况](http://ju.outofmemory.cn/entry/185097)

	其中 getItemViewType 用来返回当前项是哪种类型布局， getViewTypeCount 返回当前ListView总共多少种类型的布局
	
	如果在 RecyclerView 实现多种Item，只需要实现一个 getItemType 方法，用来返回item的种类 
	在 onCreateViewHolder 和 onBindViewHolder 方法中，第二个参数就是item的类型
	
---

# Glide

[Glide 系列预览](http://mrfu.me/2016/02/27/Glide_Getting_Started/)

---

# Github Android 开源库

[GitHub Top 100的Android开源库](https://github.com/Freelander/Android_Data/blob/master/Android-Librarys-Top-100.md#rd)

---

# Other

 - [互联网协议入门（一）](http://blog.jobbole.com/77851/#comment-64486)
 - [给安卓初学者的12篇教程](http://android.jobbole.com/76447/)
 - [android:configChanges属性](http://blog.csdn.net/goyoung/article/details/8921139)
 - [android:screenOrientation属性](http://blog.csdn.net/nmgchfzhzhg/article/details/8077133)
 - [android：ToolBar详解（手把手教程）](http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1118/2006.html)
 - [为实现Fragment 不会重新创，仿OuNews的 BaseFragment类中的onCreateView和onDestroyView写法](https://github.com/oubowu/OuNews/blob/a42f773e26a27eeadda385afaa40f8fc8e5745dc/app/src/main/java/com/oushangfeng/ounews/base/BaseFragment.java)
 - [menu-Android ActionBar详解](http://blog.csdn.net/huiguixian/article/details/9836189)
 - [spinBars-Android – Toolbar 上的 Navigation Drawer](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1123/2050.html)
 
# 疑问

 - XML布局是否可以像Jsp一样动态加载？