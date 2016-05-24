 ---
2016/4/29

因为连不上 ``` github ``` ，所以目前只在这里，等连上了就同步上去

[碗豆夹下载地址：http://www.wandoujia.com/apps/com.ms.meizinewsapplication](http://www.wandoujia.com/apps/com.ms.meizinewsapplication)

 ---

#MeiZiNews

 - [性能监听：BlockCanary](https://github.com/moduth/blockcanary)
 - [MVP代码框架：MVPro](https://github.com/qibin0506/MVPro)
 - [增加Model层：框架模式MVP在Android中的使用](http://blog.csdn.net/feiduclear_up/article/details/46374653)
 - [日志：logger](https://github.com/orhanobut/logger)
 - [ RxJava 使用：给 Android 开发者的 RxJava 详解](http://gank.io/post/560e15be2dca930e00da1083#toc_26)
 - [网络请求：retrofit+okhttp3](https://github.com/square/retrofit)
 - [图片加载：glide](https://github.com/bumptech/glide)
 - [搜索控件：SearchView](https://github.com/lapism/SearchView)
 - [数据库框架：GreenDAO](https://github.com/greenrobot/greenDAO)
 - [retrofit+okhttp3日志显示 okhttp3:logging-interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
 - [视频播放：PLDroidPlayer](https://github.com/pili-engineering/PLDroidPlayer)
 - [一个垂直方向的DrawerLayout,抽屉从上向下展开](https://github.com/corerzhang/VerticalDrawerLayout)

---

#RxJava

 - [收集了RxJava常见的使用场景，例子简洁、经典、易懂...](https://github.com/THEONE10211024/RxJavaSamples)

## 取消订阅

 - [深入浅出RxJava四-在Android中使用响应式编程：CompositeSubscription取消订阅](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0426/2787.html)

## 多线程切换

[ RxJava 使用：给 Android 开发者的 RxJava 详解](http://gank.io/post/560e15be2dca930e00da1083#toc_26)

![](http://ww1.sinaimg.cn/mw1024/52eb2279jw1f2rxd1vl7xj20hd0hzq6e.jpg)

图中共有 5 处含有对事件的操作。

由图中可以看出，①和②两处受第一个 subscribeOn() 影响，运行在红色线程；

③和④处受第一个 observeOn() 的影响，运行在绿色线程；

⑤处受第二个 observeOn() 影响，运行在紫色线程；

而第二个 subscribeOn() ，由于在通知过程中线程就被第一个 subscribeOn() 截断，因此对整个流程并没有任何影响。

这里也就回答了前面的问题：当使用了多个 subscribeOn() 的时候，只有第一个 subscribeOn() 起作用。

```` subscribeOn() ```` 设置影响在它以上的操作，只有第一个 ````subscribeOn`````起效；

```` observeOn() ```` 设置影响在它以下的操作，直到下一个 ```` observeOn() ```` 设置。

---

#GreenDAO

 - [GreenDAO数据库版本升级](http://blog.csdn.net/fancylovejava/article/details/46713445)
 - [使用greenDAO遇到的问题：Cannot update entity without key - was it inserted before?](http://blog.csdn.net/plmmmmlq/article/details/50404495)
 - [greenDao数据库更新和多表关联](http://souly.cn/%E6%8A%80%E6%9C%AF%E5%8D%9A%E6%96%87/2015/05/21/greenDAO%E5%BC%80%E6%BA%90%E6%A1%86%E6%9E%B6%E6%9B%B4%E6%96%B0%E5%92%8C%E5%A4%9A%E8%A1%A8%E5%85%B3%E8%81%94/)
 - [多个有关联的表查询：joins](http://greenrobot.org/greendao/documentation/joins/)
 - [查找查询中的问题，将sql语句打印出来:GreenDao简明教程（查询，Querying）](http://blog.csdn.net/yuyuanhuang/article/details/42751469)


## 联表查询

    DbUtil
    /**
     * Built SQL for query:
     * SELECT T."_id",T."HTML_ID",T."COLLECT" FROM "COLLECT_ENTITY" T
     * JOIN HTML_ENTITY J1 ON T."HTML_ID"=J1."_id"
     * WHERE J1."URL"=?
     *
     * @param url
     * @return
     */
    public List<CollectEntity> queryCollectByhtmlUrl(String url) {
        QueryBuilder<CollectEntity> queryBuilder = collectEntityDao.queryBuilder();
        queryBuilder.join(CollectEntityDao.Properties.Html_id, HtmlEntity.class, HtmlEntityDao.Properties.Id)
                .where(HtmlEntityDao.Properties.Url.eq(url));
        return queryBuilder.list();
    }

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

## Retrofit 如何使用Observable.from发出多条连接的？

    参考 ````` MzituImgListModel ````

    /**
     * Retrofit 使用Observable.from发出多条连接
     * @param context
     * @param listener
     * @return
     */
    @Override
    public Subscription loadWeb(final Context context, final OnModelListener<ImgItem> listener) {

        MyStringRetrofit.getMyStringRetrofit().init(context, MeiZiApi.MZITU_API);
        final MzituApi mzituApi = MyStringRetrofit.getMyStringRetrofit().getCreate(MzituApi.class);


        Observable observable = mzituApi.RxImgList(
                MyOkHttpClient.getCacheControl(context),
                imgId,
                page
        ).flatMap(
                new Func1<String, Observable<Integer>>() {

                    @Override
                    public Observable<Integer> call(String s) {
                        // 第一次请求先请求页数，从而获得要发的链接数

                        Elements mElements = JsoupUtil.getMzituImgPage(s);
                        Elements tempElements = mElements.select("span");
                        String size = tempElements.get(tempElements.size() - 2).text();
                        if (TextUtils.isEmpty(size)) {
                            return null;
                        }

                        ArrayList<Integer> numList = new ArrayList<Integer>();

                        for (int i = 1; i <= Integer.parseInt(size); i++) {
                            numList.add(i);
                        }
                        return Observable.from(numList);
                    }
                }
        ).flatMap(
                new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(Integer integer) {
                    //   发出单次网络请求连接
                        return mzituApi.RxImgList(
                                MyOkHttpClient.getCacheControl(context),
                                imgId,
                                integer + ""
                        );
                    }
                }
        ).map(
                new Func1<String, ImgItem>() {
                    @Override
                    public ImgItem call(String s) {
                    //   处理每次请问的结果

                        ImgItem img = new ImgItem();
                        Elements mElements = JsoupUtil.getMzituImgItem(s);
                        Element tempElement = mElements.first();

                        img.setImgUrl(tempElement.select("img").attr("src"));
                        img.setUrl(tempElement.select("img").attr("src"));
                        img.setStory_title(tempElement.select("img").attr("alt"));
                        return img;
                    }
                }
        );

        return RxJavaUtil.rxIoAndMain(
                observable,
                new Subscriber<ImgItem>() {

                    @Override
                    public void onCompleted() {
                        listener.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.toString());
                        DebugUtil.debugLogErr(e, e.toString());
                    }

                    @Override
                    public void onNext(ImgItem imgItem) {
                        listener.onSuccess(imgItem);
                    }
                }
        );
    }

## 问题集

 - [Retrofit error URL query string must not have replace block](http://stackoverflow.com/questions/24610243/retrofit-error-url-query-string-must-not-have-replace-block)

## 其他笔记

@Path:路径

@Query:查询条件，如：     

	xx=yy

如果有多个查询条件可以使用: ` @QueryMap ` ;

---

#API

##视频直播

[通过webview打开HTML5页面用自己的浏览器点直播MP4和M3U8](http://my.oschina.net/u/269082/blog/62701)
[【M3U8】测试地址及android播放器](http://blog.csdn.net/heng615975867/article/details/22812671)

###斗鱼

分类

    http://www.douyu.com/directory

进入分类

    http://www.douyu.com/directory/game/DOTA2?page=2&isAjax=1

HTML5版-房间信息

    http://m.douyu.com/html5/live?roomId=73334

    {
        "error": 0,
        "msg": "ok",
        "data": {
            "room_id": "73334",
            "tag_name": "DOTA2",
            "room_src": "http://rpic.douyucdn.cn/z1605/01/15/73334_160501151547.jpg",
            "room_name": "重返8000之路！7600",
            "show_status": "1",
            "online": 13126,
            "nickname": "两仪落214",
            "hls_url": "http://hls3a.douyutv.com/live/73334rRfZajWPCGs_550/playlist.m3u8?wsSecret=c52bca967d3b341d878291b7901c800b&wsTime=1462084010",
            "is_pass_player": false,
            "is_ticket": 0
        }
    }

### 熊猫TV

分类：

    http://www.panda.tv/cate

分页

    http://www.panda.tv/ajax_sort?pageno=1&pagenum=20&classification=lol
    http://www.panda.tv/ajax_sort?token=&pageno=2&pagenum=120&classification=lol

房间：

    http://www.panda.tv/354095

房间信息

    http://room.api.m.panda.tv/index.php?method=room.shareapi&roomid=354095
    http://room.api.m.panda.tv/index.php?method=room.shareapi&roomid=354095&callback=jQuery1910274208655487177_1462807801662&_=1462807801663
    http://room.api.m.panda.tv/index.php?method=room.recommend&callback=jQuery1910274208655487177_1462807801664&_=1462807801665


## 妹子图集

    // 专题
    http://www.mzitu.com/zhuanti

    // 首页
    http://www.mzitu.com/
    http://www.mzitu.com/page/1

    // 热门
    http://www.mzitu.com/hot
    http://www.mzitu.com/hot/page/2

    // 推荐
    http://www.mzitu.com/best
    http://www.mzitu.com/best/page/2

    // 性感妹子
    http://www.mzitu.com/xinggan
    http://www.mzitu.com/xinggan/page/2

    // 日本妹子
    http://www.mzitu.com/japan
    http://www.mzitu.com/japan/page/2

    // 台湾妹子
    http://www.mzitu.com/taiwan
    http://www.mzitu.com/taiwan/page/2

    // 清纯
    http://www.mzitu.com/mm
    http://www.mzitu.com/mm/page/2

    // 图集查看
    http://www.mzitu.com/{图集ID}/{页数}
    http://www.mzitu.com/65054/48

## 永久免费的基于深度学习的中文在线抽词-PullWord

[永久免费的基于深度学习的中文在线抽词-PullWord](http://www.pullword.com/)
[API 使用](http://api.pullword.com/)

API:

	http://103.37.149.178:16888/pullword/get.php?source=清华大学是好学校&param1=0&param2=1

## 知乎日报

    //Zhihu API
    public static final String BASE_URL = "http://news-at.zhihu.com/api/4/news/";//日报详情
    public static final String NEWS_LATEST = "http://news-at.zhihu.com/api/4/news/latest";//最新日报
    public static final String NEWS_BEFORE = "http://news-at.zhihu.com/api/4/news/before/";//指定日期的日报
    public static final String SPLASH = "http://news-at.zhihu.com/api/4/start-image/1080*1920";//封面

    http://news-at.zhihu.com/api/4/themes // 主题日报列表查看
    http://news-at.zhihu.com/api/4/theme/{id} //主题日报内容查看

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

 - 主题日报

    http://news-at.zhihu.com/api/4/themes // 主题日报列表查看
    http://news-at.zhihu.com/api/4/theme/{id} //主题日报内容查看

    [http://news-at.zhihu.com/api/4/theme/11](http://news-at.zhihu.com/api/4/theme/11)

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
 - [ Android 学习笔记（二七）：Menu](http://blog.csdn.net/flowingflying/article/details/6317632)
 - [spinBars-Android – Toolbar 上的 Navigation Drawer](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1123/2050.html)
 - [Android设计模式之单例模式](http://mp.weixin.qq.com/s?__biz=MzA4NTQwNDcyMA==&mid=403126596&idx=1&sn=101c6d4e363213bcdbe1879edeb08736&scene=23&srcid=0405dfOnHTLZxrgDaQMKMcNR#rd)
 - [软键盘处理：解析android中隐藏与显示软键盘及不自动弹出键盘的实现方法](http://www.jb51.net/article/36653.htm)
 - [软键盘处理：android:windowSoftInputMode属性详解](http://blog.csdn.net/twoicewoo/article/details/7384398)
 - [win10 Genymotion无法启动：virtualbox connot start the virtual device:GenyMotion Unable to start the Genymotion virtual device](http://stackoverflow.com/questions/19922077/genymotion-unable-to-start-the-genymotion-virtual-device)
 - [通透Gson@Expose注解、@SerializedName、解析json数据](http://blog.csdn.net/andywuchuanlong/article/details/44077913)
 - [android5.0自带兼容控件__RecyclerView](http://blog.csdn.net/rozol/article/details/50225593)
 - [RecyclerView 实现下拉刷新和自动加载](http://www.jianshu.com/p/4feb0c16d1b5)

    recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                    int totalItemCount = mLayoutManager.getItemCount();
                    //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                    // dy>0 表示向下滑动
                    if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                        if(isLoadingMore){
                             Log.d(TAG,"ignore manually update!");
                        } else{
                             loadPage();//这里多线程也要手动控制isLoadingMore
                            isLoadingMore = false;
                        }
                    }
                }
            });

 - [通过重写OnScrollListener来监听RecyclerView是否滑动到底部](http://www.cnblogs.com/tianzhijiexian/p/4397552.html)

---
 
# 疑问

 - XML布局是否可以像Jsp一样动态加载？

---

# 奇怪的错误：

## 由编译时出现的错误：

	通过清理项目的缓存可解决。

第1个：

	04-19 20:57:09.844 31614-31614/com.ms.meizinewsapplication E/AndroidRuntime: FATAL EXCEPTION: main
	 Process: com.ms.meizinewsapplication, PID: 31614
	 java.lang.NoSuchMethodError: No virtual method setMenuItemIconByCollect(Z)V in class Lcom/ms/meizinewsapplication/features/main/iview/DevWeekDetailIVew; or its super classes (declaration of 'com.ms.meizinewsapplication.features.main.iview.DevWeekDetailIVew' appears in /data/data/com.ms.meizinewsapplication/files/instant-run/dex/slice-slice_4-classes.dex)
		 at com.ms.meizinewsapplication.features.main.activity.DevWeekDetailActivity$2.onMenuItemClick(DevWeekDetailActivity.java:160)
		 at android.support.v7.widget.Toolbar$1.onMenuItemClick(Toolbar.java:169)
		 at android.support.v7.widget.ActionMenuView$MenuBuilderCallback.onMenuItemSelected(ActionMenuView.java:760)
		 at android.support.v7.view.menu.MenuBuilder.dispatchMenuItemSelected(MenuBuilder.java:811)
		 at android.support.v7.view.menu.MenuItemImpl.invoke(MenuItemImpl.java:152)
		 at android.support.v7.view.menu.MenuBuilder.performItemAction(MenuBuilder.java:958)
		 at android.support.v7.view.menu.MenuBuilder.performItemAction(MenuBuilder.java:948)
		 at android.support.v7.widget.ActionMenuView.invokeItem(ActionMenuView.java:618)
		 at android.support.v7.view.menu.ActionMenuItemView.onClick(ActionMenuItemView.java:139)
		 at android.view.View.performClick(View.java:4908)
		 at android.view.View$PerformClick.run(View.java:20378)
		 at android.os.Handler.handleCallback(Handler.java:815)
		 at android.os.Handler.dispatchMessage(Handler.java:104)
		 at android.os.Looper.loop(Looper.java:194)
		 at android.app.ActivityThread.main(ActivityThread.java:5691)
		 at java.lang.reflect.Method.invoke(Native Method)
		 at java.lang.reflect.Method.invoke(Method.java:372)
		 at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:959)
		 at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:754)


第2个：

	04-19 20:13:37.194 5338-5416/com.ms.meizinewsapplication E/AndroidRuntime: FATAL EXCEPTION: RxCachedThreadScheduler-2
	   Process: com.ms.meizinewsapplication, PID: 5338
	   java.lang.IllegalStateException: Fatal Exception thrown on Scheduler.Worker thread.
		   at rx.internal.schedulers.ScheduledAction.run(ScheduledAction.java:62)
		   at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:422)
		   at java.util.concurrent.FutureTask.run(FutureTask.java:237)
		   at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$201(ScheduledThreadPoolExecutor.java:152)
		   at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:265)
		   at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1112)
		   at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:587)
		   at java.lang.Thread.run(Thread.java:818)
		Caused by: java.lang.NoSuchMethodError: No virtual method addCollectByUrl(Ljava/lang/String;Ljava/lang/String;)Z in class Lcom/ms/meizinewsapplication/features/base/utils/db/DbUtil; or its super classes (declaration of 'com.ms.meizinewsapplication.features.base.utils.db.DbUtil' appears in /data/data/com.ms.meizinewsapplication/files/instant-run/dex/slice-slice_9-classes.dex)
		   at com.ms.meizinewsapplication.features.base.model.CollectModel$1.call(CollectModel.java:44)
		   at com.ms.meizinewsapplication.features.base.model.CollectModel$1.call(CollectModel.java:39)
		   at rx.internal.operators.OperatorMap$1.onNext(OperatorMap.java:54)
		   at rx.internal.util.ScalarSynchronousObservable$1.call(ScalarSynchronousObservable.java:46)
		   at rx.internal.util.ScalarSynchronousObservable$1.call(ScalarSynchronousObservable.java:35)
		   at rx.Observable$2.call(Observable.java:162)
		   at rx.Observable$2.call(Observable.java:154)
		   at rx.Observable.unsafeSubscribe(Observable.java:8098)
		   at rx.internal.operators.OperatorSubscribeOn$1$1.call(OperatorSubscribeOn.java:62)
		   at rx.internal.schedulers.ScheduledAction.run(ScheduledAction.java:55)
		   at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:422) 
		   at java.util.concurrent.FutureTask.run(FutureTask.java:237) 
		   at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$201(ScheduledThreadPoolExecutor.java:152) 
		   at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:265) 
		   at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1112) 
		   at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:587) 
		   at java.lang.Thread.run(Thread.java:818) 

## app首次打开要很长时间 ``` First launch take long time in Android Studio 2.0 & Gradle 2.0 ```

[app首次打开要很长时间 ``` First launch take long time in Android Studio 2.0 & Gradle 2.0 ```](http://stackoverflow.com/questions/36623917/first-launch-take-long-time-in-android-studio-2-0-gradle-2-0)

所报日志：

    04-27 10:24:16.241 12442-12450/com.ms.meizinewsapplication W/art: Suspending all threads took: 8.750ms
    04-27 10:24:16.394 12442-12442/com.ms.meizinewsapplication W/art: Before Android 4.1, method android.graphics.PorterDuffColorFilter android.support.graphics.drawable.VectorDrawableCompat.updateTintFilter(android.graphics.PorterDuffColorFilter, android.content.res.ColorStateList, android.graphics.PorterDuff$Mode) would have incorrectly overridden the package-private method in android.graphics.drawable.Drawable
    04-27 10:24:16.552 12442-12802/com.ms.meizinewsapplication D/OpenGLRenderer: Use EGL_SWAP_BEHAVIOR_PRESERVED: true

                                                                                 [ 04-27 10:24:16.554 12442:12442 D/         ]
                                                                                 HostConnection::get() New Host Connection established 0xb4305d40, tid 12442
    04-27 10:24:16.556 12442-12442/com.ms.meizinewsapplication D/Atlas: Validating map...


原因：

    在2.0版本的新功能已添加即时运行。
    要启用此功能的工具添加了大量的元信息，所以第一个构建和上传需要更多的时间。
    请注意有关设置的minSdkVersion 15或更高，以获得任何盈利。

    In version 2.0 a new feature was added instant-run.

    To enable this feature tool adds a lots of meta information, so the first build and upload takes more time.

    Be aware about setting minSdkVersion 15 or higher to get any profit.

解决步骤：

    1、点击：Settings → Build, Execution, Deployment → Instant Run
    2、取消选择：Enable Instant Run to hot swap code/resoure changes on deploy

这样会取消功能``` 即时运行 ```，但可以解决这个问题。

---

# 增加分组的Adapter：BaseTypeItemQuickAdapter

主要还是BaseQuickAdapter为样本，重写``` getItemViewType ```，主要修改如下：


    /**
     * 返回的布局判断
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if (position == 0 || oldCount == position) {
            return TYPE_TITLE;
        } else {
            return TYPE_ITEM;
        }

    }

    /**
     * 对不同类型的操作
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_TITLE:

                view = LayoutInflater.from(viewGroup.getContext()).inflate(titleLayoutResId, viewGroup, false);

                break;
            case TYPE_ITEM:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayoutResId, viewGroup, false);

                break;
        }

        if (view == null) {
            return null;
        }

        view.setOnClickListener(this);
        BaseAdapterHelper vh = new BaseAdapterHelper(view);
        return vh;
    }
