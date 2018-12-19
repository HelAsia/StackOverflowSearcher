package com.helasia.stackoverflowsearcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.helasia.stackoverflowsearcher.searchWithResults.SearchAndResultPresenter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SearchAndResultPresenterTest {
  @Mock
  private SearchAndResultPresenter mockSearchAndResultPresenter;

  @Test
  public void getLastQueryFromPreferences_checkReturnedValue(){
    when(mockSearchAndResultPresenter.getLastQueryFromPreferences()).thenReturn("QueryRepositoryTest");

    assertEquals(mockSearchAndResultPresenter.getLastQueryFromPreferences(), "QueryRepositoryTest");
    assertNotEquals(mockSearchAndResultPresenter.getLastQueryFromPreferences(), "test2");
    verify(mockSearchAndResultPresenter, times(2)).getLastQueryFromPreferences();
  }
}
