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


  YoutubeReferenceRepository repo;


  private static void prevalidate(YoutubeReference input) {

  }

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

    if (s.isEmpty()) {
      return null;
    }
    return s.get();
  }


  @Transactional
  public List<YoutubeReference> findAllByLicenseNo(String no) {
    List<YoutubeReference> s = repo.findAllBySfaLicenseNo(no);

    return s;
  }

  @Transactional
  public List<YoutubeReference> findAllByVideoId(String video_id) {
    List<YoutubeReference> s = repo.findAllByVideoId(video_id);
    return s;
  }

  @Transactional
  public YoutubeReference create(String videoId, String sfaLicenseNo,
      String timestamp, String username) {
    YoutubeReference est = new YoutubeReference(videoId, sfaLicenseNo, timestamp, username);

    System.out.println("UService: " + est);
    prevalidate(est);

    YoutubeReference s = repo.save(est);
    System.out.println("UService after save: " + s);

    return s;
  }
}