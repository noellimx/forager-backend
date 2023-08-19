package com.noellimx.main.service.app;


import com.noellimx.main.entity.FoodEstablishment;
import com.noellimx.main.respository.FoodEstablishmentRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FoodEstablishmentService {


  final FoodEstablishmentRepository repo;


  private static void prevalidate(FoodEstablishment input) {
    if (input.getSfaLicenseNo().equals("")) {
      throw new RuntimeException("CreateValidator fail: postal code size inexact");
    }
  }


  @Transactional
  public List<FoodEstablishment> getAll() {
    return repo.findAll();
  }

  @Transactional
  public FoodEstablishment getByLicenseNo(String no) {
    Optional<FoodEstablishment> s = repo.findBySfaLicenseNo(no);

    return s.orElse(null);
  }


  @Transactional
  public FoodEstablishment getOrCreateByLicenseNo(String no) {
    FoodEstablishment s = getByLicenseNo(no);
    if (s == null) {
      return create(no, "", "");
    }
    return s;
  }


  @Autowired
  public FoodEstablishmentService(FoodEstablishmentRepository repo) {
    this.repo = repo;
  }

  @Transactional
  public FoodEstablishment create(String li, String po, String businessName) {

    FoodEstablishment est = new FoodEstablishment(li, po, businessName);

    prevalidate(est);
    return repo.save(est);
  }
}