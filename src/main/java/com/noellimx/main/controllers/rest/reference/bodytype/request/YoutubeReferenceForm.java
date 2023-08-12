package com.noellimx.main.controllers.rest.reference.bodytype.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YoutubeReferenceForm {


  private YoutubeReferenceForm() {
  }

  @JsonProperty("sfa_license_no")
  public String sfaLicenseNo;

  @JsonProperty("video_id")
  public String videoId;

  @JsonProperty("timestamp")
  public String timestamp;

  public String getSfaLicenseNo() {
    return sfaLicenseNo;
  }

  public void setSfaLicenseNo(String sfa_license_no) {
    this.sfaLicenseNo = sfa_license_no;
  }

  public String getVideoId() {
    return videoId;
  }

  public void setVideoId(String videoId) {
    this.videoId = videoId;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public String toString() {
    return "YoutubeReferenceForm{" +
        "sfaLicenseNo='" + sfaLicenseNo + '\'' +
        ", videoId='" + videoId + '\'' +
        ", timestamp='" + timestamp + '\'' +
        '}';
  }
}
