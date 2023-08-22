package com.noellimx.main.controllers.rest.reference.bodytype.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.noellimx.main.entity.FoodEstablishment;
import java.util.List;

public class ResponseFoodEstablishment {


  @JsonProperty("sfa_license_no")
  private String sfaLicenseNo;


  public ResponseFoodEstablishment(String sfaLicenseNo) {
    this.sfaLicenseNo = sfaLicenseNo;
  }


  public ResponseFoodEstablishment() {
  }

  public String getSfaLicenseNo() {
    return sfaLicenseNo;
  }

  public void setSfaLicenseNo(String sfaLicenseNo) {
    this.sfaLicenseNo = sfaLicenseNo;
  }

  public static ResponseFoodEstablishment fromEntity(FoodEstablishment fe) {
    return new ResponseFoodEstablishment(fe.getSfaLicenseNo());
  }

  public static List<ResponseFoodEstablishment> fromEntities(FoodEstablishment... fes) {
    List<ResponseFoodEstablishment> rfes = List.of();
    for (FoodEstablishment fe : fes) {
      ResponseFoodEstablishment rfe = fromEntity(fe);
      rfes.add(rfe);

    }
    return rfes;
  }


}
