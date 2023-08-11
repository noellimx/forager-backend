package com.noellimx.main.controllers.rest.foodestablishment.bodytype.response;


import com.noellimx.main.controllers.rest.utils.JsonResponse;
import com.noellimx.main.entity.FoodEstablishment;
import java.util.List;


public class GetAllResponse extends JsonResponse<List<FoodEstablishment>> {


  public GetAllResponse(List<FoodEstablishment> data, String message) {
    super(data, message);
  }

  public GetAllResponse() {
  }
}
