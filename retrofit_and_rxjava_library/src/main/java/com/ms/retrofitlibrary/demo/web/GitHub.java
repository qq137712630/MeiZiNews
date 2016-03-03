package com.ms.retrofitlibrary.demo.web;

import com.ms.retrofitlibrary.demo.pojo.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 啟成 on 2016/2/28.
 */
public interface GitHub {


    //https://api.github.com/repos/square/retrofit/contributors

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);

    //使用 RxJava 的方法,返回一个 Observable
    @Headers("Cache-Control: public, max-age=3600")
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> RxContributors(@Path("owner") String owner, @Path("repo") String repo);


    //使用 RxJava 的方法,返回一个 Observable
    @Headers("Cache-Control: public, max-age=3600")
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<String> RxContributorsString(@Path("owner") String owner, @Path("repo") String repo);
}