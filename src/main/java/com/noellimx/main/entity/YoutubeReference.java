package com.noellimx.main.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "youtube_references", uniqueConstraints = {
    @UniqueConstraint(name = "uniqueVideoAndEstablishment", columnNames = {"video_id",
        "sfa_license_no"})})
public class YoutubeReference {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;


  @Column(name = "video_id", nullable = false)
  private String videoId;

  @Column(name = "sfa_license_no", nullable = false)
  private String sfaLicenseNo;

  @Column(name = "timestamp", nullable = false)
  private String timestamp;

  @Column(name = "username")
  private String username;

  public YoutubeReference() {
  }

  public YoutubeReference(String videoId, String sfaLicenseNo,
      String timestamp, String username) {
    this.videoId = videoId;
    this.sfaLicenseNo = sfaLicenseNo;
    this.timestamp = timestamp;
    this.username = username;
  }

  @Override
  public String toString() {
    return "YoutubeReference{" +
        "id=" + id +
        ", videoId='" + videoId + '\'' +
        ", sfaLicenseNo='" + sfaLicenseNo + '\'' +
        ", timestamp='" + timestamp + '\'' +
        ", username='" + username + '\'' +
        '}';
  }
}
