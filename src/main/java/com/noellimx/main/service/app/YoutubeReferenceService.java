package com.noellimx.main.service.app;


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


  @Autowired
  public YoutubeReferenceService(YoutubeReferenceRepository repo) {
    this.repo = repo;
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

    return repo.findAllBySfaLicenseNo(no);
  }

  @Transactional
  public List<YoutubeReference> findAllByVideoId(String video_id) {
    return repo.findAllByVideoId(video_id);
  }

  @Transactional
  public YoutubeReference create(String videoId, String sfaLicenseNo,
      String timestamp, String username) {
    YoutubeReference est = new YoutubeReference(videoId, sfaLicenseNo, timestamp, username);

    return repo.save(est);
  }
}