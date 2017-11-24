package com.sandyr.demo.github;

import com.sandyr.demo.github.search.model.Item;
import com.sandyr.demo.github.search.model.Owner;
import com.sandyr.demo.github.search.presenter.SearchPresenterImpl;
import com.sandyr.demo.github.search.view.SearchView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {

    @Mock
    public SearchView view;
    @Mock
    public SearchPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new SearchPresenterImpl();
        presenter.setView(view);
    }


    @Test
    public void checkIfViewIsReleasedOnDestroy() {
        presenter.onDestroy();
        assertNull(presenter.getSearchView());
    }

    @Test
    public void checkIfItemsArePassedToView() {
        ArrayList<Item> items = new ArrayList<Item>();
        Item item = new Item();
        item.setName("Repo Name");
        item.setDescription("Repo Description");
        item.setForksCount(25);

        Owner owner=new Owner();
        owner.setAvatarUrl("https://media.gettyimages.com/photos/models-show-samsung-electronics-cos-android-smartphones-during-the-picture-id96402674?b=1&k=6&m=96402674&s=170x170&h=N07yBNCzfyZdKO9ahoSVfass0L9BrUry8Z2-oOlrHDg=");

        item.setOwner(owner);

        items.add(item);
        presenter.getSearchView().onLoadImagesByPhraseSuccess(items);
        verify(view, times(1)).onLoadImagesByPhraseSuccess(items);
    }
}
