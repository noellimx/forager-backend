package com.noellimx.main.service.app;


import com.noellimx.main.entity.FoodEstablishment;
import com.noellimx.main.entity.YoutubeReference;
import com.noellimx.main.respository.YoutubeReferenceRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YoutubeReferenceService {


  final YoutubeReferenceRepository repo;
  final FoodEstablishmentService foodEstablishmentService;


  @Autowired
  public YoutubeReferenceService(YoutubeReferenceRepository repo,
      FoodEstablishmentService foodEstablishmentService) {
    this.repo = repo;

    this.foodEstablishmentService = foodEstablishmentService;
  }

  @Transactional
  public List<YoutubeReference> getAll() {
    return repo.findAll();
  }

  @Transactional
  public YoutubeReference findByLicenseNo(String no) {
    Optional<YoutubeReference> s = repo.findBySfaLicenseNo(no);

    return s.orElse(null);
  }


  @Transactional
  public List<YoutubeReference> findAllByLicenseNo(String no) {

    System.out.println("[findAllByLicenseNo]");
    return repo.findAllBySfaLicenseNo(no);
  }


  @Transactional
  public YoutubeReference create(String videoId, String sfaLicenseNo,
      String timestamp, String username) {

    FoodEstablishment est = this.foodEstablishmentService.getOrCreateByLicenseNo(sfaLicenseNo);

    YoutubeReference ref = new YoutubeReference(videoId, est, sfaLicenseNo, timestamp, username);

    return repo.save(ref);
  }
}