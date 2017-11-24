package com.sandyr.demo.github.search.presenter;


/**
 * Created by sandyr on 11/23/2017.
 */

public interface SearchPresenter {
    void onDestroy();

    void loadNextPage();

    void searchGithubRepos(String searchPhrase);
}
