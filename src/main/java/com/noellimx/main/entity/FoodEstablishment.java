package com.noellimx.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_establishments")
public class FoodEstablishment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }


  @Column(unique = true, name = "sfa_license_no", nullable = false)
  private String sfaLicenseNo;

  @Column(name = "postal_code_official", length = 6)
  private String postalCodeOfficial;

  @Column(name = "business_name")
  private String businessName;

  public FoodEstablishment(String licenseNo, String postal, String businessName) {
    this.sfaLicenseNo = licenseNo;
    this.postalCodeOfficial = postal;
    this.businessName = businessName;
  }

  public FoodEstablishment() {

  }

  public String getSfaLicenseNo() {
    return sfaLicenseNo;
  }

  public void setSfaLicenseNo(String sfaLicenseNo) {
    this.sfaLicenseNo = sfaLicenseNo;
  }

  public String getPostalCodeOfficial() {
    return postalCodeOfficial;
  }

  public void setPostalCodeOfficial(String postalCodeOfficial) {
    this.postalCodeOfficial = postalCodeOfficial;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  @Override
  public String toString() {
    return "FoodEstablishment{" +
        "id=" + id +
        ", sfaLicenseNo='" + sfaLicenseNo + '\'' +
        ", postalCodeOfficial='" + postalCodeOfficial + '\'' +
        ", businessName='" + businessName + '\'' +
        '}';
  }
}