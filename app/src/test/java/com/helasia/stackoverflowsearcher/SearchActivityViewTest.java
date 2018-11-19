package com.helasia.stackoverflowsearcher;

import android.content.Intent;
import com.helasia.stackoverflowsearcher.data.repositories.QueryRepository;
import com.helasia.stackoverflowsearcher.details.WebViewActivity;
import com.helasia.stackoverflowsearcher.search_with_results.SearchActivityView;
import com.helasia.stackoverflowsearcher.search_with_results.SearchPresenter;
import org.junit.Before;;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class SearchActivityViewTest {
  private SearchActivityView searchActivityView;

  private SearchPresenter mockPresenter;

  @Mock
  private QueryRepository mockQueryRepository;

  @Before
  public void setUp(){
    searchActivityView = Robolectric.buildActivity(SearchActivityView.class).create().get();
    mockPresenter = new SearchPresenter(searchActivityView, mockQueryRepository);
  }
  @Test
  public void startWebViewActivityTest() {
    mockPresenter.shareCardClicked("QueryRepositoryTest");

    ShadowActivity shadowActivity = shadowOf(searchActivityView);
    Intent startedIntent = shadowActivity.getNextStartedActivity();
    assertThat(startedIntent.getComponent().getClassName(), equalTo(WebViewActivity.class.getName()));
  }
}