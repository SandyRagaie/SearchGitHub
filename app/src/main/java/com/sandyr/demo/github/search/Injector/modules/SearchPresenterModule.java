package com.sandyr.demo.github.search.Injector.modules;

import com.sandyr.demo.github.search.presenter.SearchPresenter;
import com.sandyr.demo.github.search.presenter.SearchPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/**
 * Created by sandyr on 7/16/2017.
 */

/**
 * Modules aren't used by you directly, they're used by Dagger. @Provides annotated methods are
 * used to lookup classes during injection. @Singleton indicates that only one instance of the object
 * is used, and used everywhere when injected.
 */
@Module
public class SearchPresenterModule {
    @Singleton
    @Provides
    static SearchPresenter providesGalleryPresenter() {
        return new SearchPresenterImpl();
    }
}