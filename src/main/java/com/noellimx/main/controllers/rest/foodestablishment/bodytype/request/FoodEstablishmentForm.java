package com.noellimx.main.controllers.rest.foodestablishment.bodytype.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodEstablishmentForm {


  private FoodEstablishmentForm() {
  }

  @JsonProperty("sfa_license_no")
  public String sfa_license_no;

  @JsonProperty("postal_code_official")
  public String postalCodeOfficial;

  @JsonProperty("business_name")
  public String businessName;

  public String getsfa_license_no() {
    return sfa_license_no;
  }

  public void setsfa_license_no(String sfa_license_no) {
    this.sfa_license_no = sfa_license_no;
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
    return "RegisterForm{" +
        "sfa_license_no='" + sfa_license_no + '\'' +
        ", postalCodeOfficial='" + postalCodeOfficial + '\'' +
        ", businessName='" + businessName + '\'' +
        '}';
  }
}
