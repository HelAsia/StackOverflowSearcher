package com.helasia.stackoverflowsearcher.data.model;

public class License {
  private int licenseId;
  private String licenseName;
  private String licenseAuthor;
  private String licenseDescription;
  private String licenseUrl;

  public License(String licenseName, String licenseAuthor, String licenseDescription) {
    this.licenseName = licenseName;
    this.licenseAuthor = licenseAuthor;
    this.licenseDescription = licenseDescription;
  }

  public License(int licenseId, String licenseName, String licenseAuthor,
      String licenseDescription) {
    this.licenseId = licenseId;
    this.licenseName = licenseName;
    this.licenseAuthor = licenseAuthor;
    this.licenseDescription = licenseDescription;
  }

  public License(int licenseId, String licenseName, String licenseAuthor,
      String licenseDescription, String licenseUrl) {
    this.licenseId = licenseId;
    this.licenseName = licenseName;
    this.licenseAuthor = licenseAuthor;
    this.licenseDescription = licenseDescription;
    this.licenseUrl = licenseUrl;
  }

  public String getLicenseName() {
    return licenseName;
  }

  public void setLicenseName(String licenseName) {
    this.licenseName = licenseName;
  }

  public String getLicenseAuthor() {
    return licenseAuthor;
  }

  public void setLicenseAuthor(String licenseAuthor) {
    this.licenseAuthor = licenseAuthor;
  }

  public String getLicenseDescription() {
    return licenseDescription;
  }

  public void setLicenseDescription(String licenseDescription) {
    this.licenseDescription = licenseDescription;
  }

  public int getLicenseId() {
    return licenseId;
  }

  public void setLicenseId(int licenseId) {
    this.licenseId = licenseId;
  }

  public String getLicenseUrl() {
    return licenseUrl;
  }

  public void setLicenseUrl(String licenseUrl) {
    this.licenseUrl = licenseUrl;
  }
}
