package com.noellimx.main.respository;


import com.noellimx.main.entity.YoutubeReference;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeReferenceRepository extends JpaRepository<YoutubeReference, Long> {

  Optional<YoutubeReference> findBySfaLicenseNo(String sfaLicenseNo);

  List<YoutubeReference> findAllBySfaLicenseNo(String sfaLicenseNo);

  List<YoutubeReference> findAllByVideoId(String sfaLicenseNo);

}
