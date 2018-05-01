/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.Model;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.romain.mathieu.mynews.Model.API.ArticleSearch.NYTAPIArticleSearch;
import com.romain.mathieu.mynews.Model.API.MostPopular.NYTAPIMostPopular;
import com.romain.mathieu.mynews.Model.API.TopStories.NYTAPITopstories;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NYTService {

    MyConstant constant = new MyConstant();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(constant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @GET("svc/topstories/v2/{section}.json?api-key=e5ace90626ec4c7495500a0dbb327980")
    Observable<NYTAPITopstories> getPostTop(@Path("section") String section);

    @GET("svc/mostpopular/v2/mostviewed/{section}/30.json?api-key=e5ace90626ec4c7495500a0dbb327980")
    Observable<NYTAPIMostPopular> getPostMost(@Path("section") String section);

    @GET("svc/search/v2/articlesearch.json?&fq=news_desk:(\"science\")&page=0&sort=newest&?hl=true&api-key=e5ace90626ec4c7495500a0dbb327980")
    Observable<NYTAPIArticleSearch> getPostArticle();
}