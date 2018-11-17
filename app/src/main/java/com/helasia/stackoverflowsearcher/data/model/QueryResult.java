package com.helasia.stackoverflowsearcher.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class QueryResult {
  @SerializedName("items")
  @Expose
  private List<Item> items = null;
  @SerializedName("has_more")
  @Expose
  private Boolean hasMore;
  @SerializedName("quota_max")
  @Expose
  private Integer quotaMax;
  @SerializedName("quota_remaining")
  @Expose
  private Integer quotaRemaining;
  @SerializedName("error_id")
  @Expose
  private Integer errorId;
  @SerializedName("error_message")
  @Expose
  private Integer errorMessage;
  @SerializedName("error_name")
  @Expose
  private Integer errorName;

  public QueryResult(){

  }

  public QueryResult(List<Item> items, Boolean hasMore, Integer quotaMax,
      Integer quotaRemaining) {
    this.items = items;
    this.hasMore = hasMore;
    this.quotaMax = quotaMax;
    this.quotaRemaining = quotaRemaining;
  }

  public QueryResult(Integer errorId, Integer errorMessage, Integer errorName) {
    this.errorId = errorId;
    this.errorMessage = errorMessage;
    this.errorName = errorName;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public Boolean getHasMore() {
    return hasMore;
  }

  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }

  public Integer getQuotaMax() {
    return quotaMax;
  }

  public void setQuotaMax(Integer quotaMax) {
    this.quotaMax = quotaMax;
  }

  public Integer getQuotaRemaining() {
    return quotaRemaining;
  }

  public void setQuotaRemaining(Integer quotaRemaining) {
    this.quotaRemaining = quotaRemaining;
  }

  public Integer getErrorId() {
    return errorId;
  }

  public void setErrorId(Integer errorId) {
    this.errorId = errorId;
  }

  public Integer getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(Integer errorMessage) {
    this.errorMessage = errorMessage;
  }

  public Integer getErrorName() {
    return errorName;
  }

  public void setErrorName(Integer errorName) {
    this.errorName = errorName;
  }

  @Override
  public String toString() {
    return "QueryResult{" +
        "items=" + items +
        ", hasMore=" + hasMore +
        ", quotaMax=" + quotaMax +
        ", quotaRemaining=" + quotaRemaining +
        ", errorId=" + errorId +
        ", errorMessage=" + errorMessage +
        ", errorName=" + errorName +
        '}';
  }
}
