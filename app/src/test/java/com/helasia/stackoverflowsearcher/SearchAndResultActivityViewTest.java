package com.helasia.stackoverflowsearcher;

import android.content.Intent;
import com.helasia.stackoverflowsearcher.data.repositories.QueryRepository;
import com.helasia.stackoverflowsearcher.details.WebViewActivity;
import com.helasia.stackoverflowsearcher.search_with_results.SearchAndResultActivityView;
import com.helasia.stackoverflowsearcher.search_with_results.SearchAndResultPresenter;
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
public class SearchAndResultActivityViewTest {
  private SearchAndResultActivityView searchAndResultActivityView;

  private SearchAndResultPresenter mockPresenter;

  @Mock
  private QueryRepository mockQueryRepository;

  @Before
  public void setUp(){
    searchAndResultActivityView = Robolectric.buildActivity(SearchAndResultActivityView.class).create().get();
    mockPresenter = new SearchAndResultPresenter(searchAndResultActivityView, mockQueryRepository);
  }
  @Test
  public void startWebViewActivityTest() {
    mockPresenter.shareCardClicked("QueryRepositoryTest");

    ShadowActivity shadowActivity = shadowOf(searchAndResultActivityView);
    Intent startedIntent = shadowActivity.getNextStartedActivity();
    assertThat(startedIntent.getComponent().getClassName(), equalTo(WebViewActivity.class.getName()));
  }
}