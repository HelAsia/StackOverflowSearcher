package com.helasia.stackoverflowsearcher.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Item {
  @SerializedName("tags")
  @Expose
  private List<String> tags = null;
  @SerializedName("owner")
  @Expose
  private Owner owner;
  @SerializedName("is_answered")
  @Expose
  private Boolean isAnswered;
  @SerializedName("view_count")
  @Expose
  private Integer viewCount;
  @SerializedName("answer_count")
  @Expose
  private Integer answerCount;
  @SerializedName("score")
  @Expose
  private Integer score;
  @SerializedName("last_activity_date")
  @Expose
  private Integer lastActivityDate;
  @SerializedName("creation_date")
  @Expose
  private Integer creationDate;
  @SerializedName("question_id")
  @Expose
  private Integer questionId;
  @SerializedName("link")
  @Expose
  private String link;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("accepted_answer_id")
  @Expose
  private Integer acceptedAnswerId;
  @SerializedName("last_edit_date")
  @Expose
  private Integer lastEditDate;
  @SerializedName("closed_date")
  @Expose
  private Integer closedDate;
  @SerializedName("closed_reason")
  @Expose
  private String closedReason;
  @SerializedName("bounty_amount")
  @Expose
  private Integer bountyAmount;
  @SerializedName("bounty_closes_date")
  @Expose
  private Integer bountyClosesDate;


  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public Boolean getAnswered() {
    return isAnswered;
  }

  public void setAnswered(Boolean answered) {
    isAnswered = answered;
  }

  public Integer getViewCount() {
    return viewCount;
  }

  public void setViewCount(Integer viewCount) {
    this.viewCount = viewCount;
  }

  public Integer getAnswerCount() {
    return answerCount;
  }

  public void setAnswerCount(Integer answerCount) {
    this.answerCount = answerCount;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getLastActivityDate() {
    return lastActivityDate;
  }

  public void setLastActivityDate(Integer lastActivityDate) {
    this.lastActivityDate = lastActivityDate;
  }

  public Integer getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Integer creationDate) {
    this.creationDate = creationDate;
  }

  public Integer getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getAcceptedAnswerId() {
    return acceptedAnswerId;
  }

  public void setAcceptedAnswerId(Integer acceptedAnswerId) {
    this.acceptedAnswerId = acceptedAnswerId;
  }

  public Integer getLastEditDate() {
    return lastEditDate;
  }

  public void setLastEditDate(Integer lastEditDate) {
    this.lastEditDate = lastEditDate;
  }

  public Integer getClosedDate() {
    return closedDate;
  }

  public void setClosedDate(Integer closedDate) {
    this.closedDate = closedDate;
  }

  public String getClosedReason() {
    return closedReason;
  }

  public void setClosedReason(String closedReason) {
    this.closedReason = closedReason;
  }

  public Integer getBountyAmount() {
    return bountyAmount;
  }

  public void setBountyAmount(Integer bountyAmount) {
    this.bountyAmount = bountyAmount;
  }

  public Integer getBountyClosesDate() {
    return bountyClosesDate;
  }

  public void setBountyClosesDate(Integer bountyClosesDate) {
    this.bountyClosesDate = bountyClosesDate;
  }

  @Override
  public String toString() {
    return "Item{" +
        "tags=" + tags +
        ", owner=" + owner +
        ", isAnswered=" + isAnswered +
        ", viewCount=" + viewCount +
        ", answerCount=" + answerCount +
        ", score=" + score +
        ", lastActivityDate=" + lastActivityDate +
        ", creationDate=" + creationDate +
        ", questionId=" + questionId +
        ", link='" + link + '\'' +
        ", title='" + title + '\'' +
        ", acceptedAnswerId=" + acceptedAnswerId +
        ", lastEditDate=" + lastEditDate +
        ", closedDate=" + closedDate +
        ", closedReason='" + closedReason + '\'' +
        ", bountyAmount=" + bountyAmount +
        ", bountyClosesDate=" + bountyClosesDate +
        '}';
  }
}
