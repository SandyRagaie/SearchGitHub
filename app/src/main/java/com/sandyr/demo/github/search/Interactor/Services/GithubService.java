package com.sandyr.demo.github.search.Interactor.Services;

import com.sandyr.demo.github.search.model.Feed;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sandyr on 11/23/2017.
 */

public interface GithubService {

    @GET("/search/repositories")
    Observable<Feed> searchRepositories(@Query("q") String query );

}
