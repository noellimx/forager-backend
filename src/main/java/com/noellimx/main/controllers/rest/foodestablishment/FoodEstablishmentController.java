package com.noellimx.main.controllers.rest.foodestablishment;


import com.noellimx.main.controllers.rest.foodestablishment.bodytype.request.FoodEstablishmentForm;
import com.noellimx.main.controllers.rest.utils.JsonResponse;
import com.noellimx.main.service.app.FoodEstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food-establishment")
public class FoodEstablishmentController {


  FoodEstablishmentService foodEstablishmentService;

  @Autowired
  public FoodEstablishmentController(FoodEstablishmentService foodEstablishmentService) {
    this.foodEstablishmentService = foodEstablishmentService;
  }

  @PostMapping("/")
  public ResponseEntity<FoodEstablishmentForm> create(@RequestBody FoodEstablishmentForm form) {
    foodEstablishmentService.create(form.sfa_license_no, form.postalCodeOfficial,
        form.businessName);
    return ResponseEntity.status(501).body(form);
  }

  @GetMapping("/all")
  public JsonResponse getAll() {
    JsonResponse response = new JsonResponse<>(
        foodEstablishmentService.getAll(), "");
    return response;
  }


}
