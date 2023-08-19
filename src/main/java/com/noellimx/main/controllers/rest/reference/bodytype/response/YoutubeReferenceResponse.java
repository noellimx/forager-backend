package com.noellimx.main.controllers.rest.reference.bodytype.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.noellimx.main.entity.YoutubeReference;


public class YoutubeReferenceResponse {


  @JsonProperty("video_id")
  private String videoId;


  @JsonProperty("timestamp")
  private String timestamp;

  @JsonProperty("food_establishment")
  private ResponseFoodEstablishment responseFoodEstablishment;

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


  public ResponseFoodEstablishment getResponseFoodEstablishment() {
    return responseFoodEstablishment;
  }

  public void setResponseFoodEstablishment(
      ResponseFoodEstablishment responseFoodEstablishment) {
    this.responseFoodEstablishment = responseFoodEstablishment;
  }


  public static YoutubeReferenceResponse fromEntity(YoutubeReference ref) {

    YoutubeReferenceResponse ryr = new YoutubeReferenceResponse();

    ryr.setTimestamp(ref.getTimestamp());
    ryr.setVideoId(ref.getId().getVideoId());

    ryr.setResponseFoodEstablishment(
        ResponseFoodEstablishment.fromEntity(ref.getFoodEstablishment()));

    return ryr;
  }
}
