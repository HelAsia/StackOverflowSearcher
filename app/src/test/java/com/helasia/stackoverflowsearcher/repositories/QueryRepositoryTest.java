package com.helasia.stackoverflowsearcher.repositories;

import static org.junit.Assert.assertEquals;

import com.helasia.stackoverflowsearcher.data.repositories.QueryRepository;
import com.helasia.stackoverflowsearcher.data.repositories.QueryRepositoryInterface.OnQueryResultDisplayListener;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QueryRepositoryTest {
  @Rule
  public MockWebServer mockServer;
  @Mock
  private OnQueryResultDisplayListener mockListener;

  private QueryRepository mockQueryRepository;

  @Before
  public void setUp() throws Exception{
    mockServer = new MockWebServer();
    mockServer.start();
    String url = mockServer.url("/").toString();

    mockQueryRepository = new QueryRepository(url);
  }

  @Test
  public void getQueryResult_useCorrectRequestPath() {
    MockResponse mockResponse = new MockResponse()
        .setResponseCode(200)
        .setBody("{\"items\": [],\"has_more\": false,\"quota_max\": 10000,\"quota_remaining\": 9995}");

    mockServer.enqueue(mockResponse);

    mockQueryRepository.getQueryResult("rfghdfghdgfhdfghxfhnfhfhdfghfhfhdfhdfhg", mockListener);


    try {
      RecordedRequest request = mockServer.takeRequest();
      assertEquals("/2.2/search?order=desc&sort=activity&site=stackoverflow&intitle=rfghdfghdgfhdfghxfhnfhfhdfghfhfhdfhdfhg", request.getPath());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @After
  public void tearDown() throws Exception {
    mockServer.shutdown();
  }
}