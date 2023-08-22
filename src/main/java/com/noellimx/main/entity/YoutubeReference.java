package com.noellimx.main.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.io.Serializable;


@Entity
@Table(name = "youtube_references", uniqueConstraints = {
    @UniqueConstraint(name = "uniqueVideoAndEstablishment", columnNames = {"video_id",
        "food_establishment_id"})})
public class YoutubeReference {

  @Embeddable
  public static class YoutubeReferenceKey implements Serializable {

    @Column(name = "food_establishment_id")
    private Integer foodEstablishmentId;

    @Column(name = "video_id")
    private String videoId;

    public String getVideoId() {
      return videoId;
    }

    public void setVideoId(String videoId) {
      this.videoId = videoId;
    }

    public YoutubeReferenceKey(Integer foodEstablishmentId) {
      this.foodEstablishmentId = foodEstablishmentId;
    }


    public YoutubeReferenceKey(Integer foodEstablishmentId, String videoId) {
      this.foodEstablishmentId = foodEstablishmentId;
      this.videoId = videoId;
    }

    public YoutubeReferenceKey() {
    }

    @Override
    public int hashCode() {

      final int prime = 31;
      int result = 1;
      result = prime * result + ((videoId == null) ? 0 : videoId.hashCode());
      result =
          prime * result + ((foodEstablishmentId == null) ? 0 : foodEstablishmentId.hashCode());
      return result;
    }


    @Override
    public boolean equals(Object obj) {

      YoutubeReferenceKey that = (YoutubeReferenceKey) obj;

      return super.equals(obj) && that.getFoodEstablishmentId().equals(getFoodEstablishmentId());
    }

    public Integer getFoodEstablishmentId() {
      return foodEstablishmentId;
    }

    public void setFoodEstablishmentId(Integer foodEstablishmentId) {
      this.foodEstablishmentId = foodEstablishmentId;
    }

    @Override
    public String toString() {
      return "YoutubeReferenceKey{" +
          "foodEstablishmentId=" + foodEstablishmentId +
          ", videoId='" + videoId + '\'' +
          '}';
    }
  }

  @EmbeddedId
  private YoutubeReferenceKey id;


  @Column(name = "timestamp", nullable = false)
  private String timestamp;

  @Column(name = "creator_name")
  private String creatorName;


  @MapsId("foodEstablishmentId")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "food_establishment_id")
  private FoodEstablishment foodEstablishment;

  public YoutubeReference() {
  }

  public YoutubeReference(String videoId, FoodEstablishment fe, String sfaLicenseNo,
      String timestamp, String username) {
    this.id = new YoutubeReferenceKey(fe.getId(), videoId);
    this.timestamp = timestamp;
    this.creatorName = username;
    this.foodEstablishment = fe;
  }


  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public void setCreatorName(String creator_name) {
    this.creatorName = creator_name;
  }

  public YoutubeReferenceKey getId() {
    return id;
  }

  public void setId(YoutubeReferenceKey id) {
    this.id = id;
  }


  public void setId(Integer fe) {
    this.id = new YoutubeReferenceKey(fe);
  }


  public FoodEstablishment getFoodEstablishment() {
    return foodEstablishment;
  }

  public void setFoodEstablishment(FoodEstablishment foodEstablishment) {
    this.foodEstablishment = foodEstablishment;
  }


  @Override
  public String toString() {
    return "YoutubeReference{" +
        "id=" + id +
        ", timestamp='" + timestamp + '\'' +
        ", creator_name='" + creatorName + '\'' +
        ", foodEstablishment=" + foodEstablishment +
        '}';
  }
}
