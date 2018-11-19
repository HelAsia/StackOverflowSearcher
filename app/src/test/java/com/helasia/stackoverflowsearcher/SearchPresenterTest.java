package com.helasia.stackoverflowsearcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.helasia.stackoverflowsearcher.search_with_results.SearchPresenter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {
  @Mock
  private SearchPresenter mockSearchPresenter;

  @Test
  public void getLastQueryFromPreferences_checkReturnedValue(){
    when(mockSearchPresenter.getLastQueryFromPreferences()).thenReturn("test");

    assertEquals(mockSearchPresenter.getLastQueryFromPreferences(), "test");
    assertNotEquals(mockSearchPresenter.getLastQueryFromPreferences(), "test2");
    verify(mockSearchPresenter, times(2)).getLastQueryFromPreferences();
  }
}
