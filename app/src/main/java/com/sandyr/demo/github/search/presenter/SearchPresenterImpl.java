package com.sandyr.demo.github.search.presenter;


import com.sandyr.demo.github.search.Interactor.Services.GithubService;
import com.sandyr.demo.github.search.model.Feed;
import com.sandyr.demo.github.search.model.Item;
import com.sandyr.demo.github.search.view.SearchView;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sandyr on 11/23/2017.
 */

public class SearchPresenterImpl implements SearchPresenter {
    public Subscription subscription;
    private SearchView searchView;
    private String phrase;

    private final int pageSize;
    private int page;
    private ArrayList<Item> items;

    @Inject
    GithubService gService;

    @Inject
    public SearchPresenterImpl() {
        pageSize = 10;
        phrase = null;
    }

    public void setView(SearchView view) {
        searchView = view;
    }

    @Override
    public void onDestroy() {
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        searchView = null;
    }

    /**
     * TODO: adjust API call to load pages instead of getting the same page every time, as result is too large to be requested once
     */
    @Override
    public void loadNextPage() {
        Observer<Feed> myObserver = new Observer<Feed>() {

            @Override
            public void onCompleted() {
                if (items != null) {
                    page++;
                    searchView.onLoadImagesByPhraseSuccess(items);
                }
                searchView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                searchView.onLoadImagesByPhraseError(e.getMessage());
                searchView.hideProgress();
            }

            @Override
            public void onNext(Feed feed) {
                if (feed.getItems().size() == 0) {
                    searchView.onEmptyResult();
                } else {
                    items = feed.getItems();};
            }

        };

        subscription = gService.searchRepositories(phrase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myObserver);
        searchView.showProgress();
    }

    /**
     * initiate server request
     *
     * @param searchPhrase
     */
    @Override
    public void searchGithubRepos(String searchPhrase) {
        if (items != null) {
            items.clear();
            searchView.onRemoveImagesFromUI();
        }
        page = 1;
        phrase = searchPhrase;
        loadNextPage();
    }

    /**
     * get view instance
     *
     * @return
     */
    public SearchView getSearchView() {
        return searchView;
    }
}
