package com.noellimx.main.respository;

import com.noellimx.main.entity.FoodEstablishment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodEstablishmentRepository extends JpaRepository<FoodEstablishment, Integer> {

  Optional<FoodEstablishment> findBySfaLicenseNo(String sfaLicenseNo);
}