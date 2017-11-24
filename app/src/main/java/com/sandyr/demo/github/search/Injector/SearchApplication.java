package com.sandyr.demo.github.search.Injector;

import android.app.Application;

import com.sandyr.demo.github.search.Injector.modules.ContextModule;
import com.sandyr.demo.github.search.Injector.modules.SearchPresenterModule;
import com.sandyr.demo.github.search.Injector.modules.InteractorModule;
import com.sandyr.demo.github.search.ui.activity.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sandyr on 7/16/2017.
 */

public class SearchApplication extends Application {

    /**
     * Newly added modules must be added to the @Component annotation here. You must also provide
     * further inject() methods for new classes that want to perform injection.
     */
    @Singleton
    @Component(modules = {SearchPresenterModule.class, InteractorModule.class, ContextModule.class})
    public interface ApplicationComponent {
        void inject(SearchActivity searchActivity);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // This setups up the component which is used by other views (activities/fragments/etc., not Android views) for injection.
        // This pulls all modules which have statically declared @Provides methods automatically.
        DaggerSearchApplication_ApplicationComponent.builder().build();
    }
}
