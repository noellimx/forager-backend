package com.noellimx.main.respository;


import com.noellimx.main.entity.YoutubeReference;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeReferenceRepository extends JpaRepository<YoutubeReference, Long> {

  //
//  Optional<YoutubeReference> findBySfaLicenseNo(String sfaLicenseNo);
//
  List<YoutubeReference> findAllByFoodEstablishmentSfaLicenseNo(String sfaLicenseNo);

  List<YoutubeReference> findByIdVideoId(String videoId);

  List<YoutubeReference> findAllByIdVideoIdAndFoodEstablishmentSfaLicenseNo(String videoId,
      String foodEstablishmentId);

  Optional<YoutubeReference> findByIdVideoIdAndFoodEstablishmentSfaLicenseNo(String videoId,
      String foodEstablishmentId);


}
