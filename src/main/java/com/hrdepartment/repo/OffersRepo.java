package com.hrdepartment.repo;

import com.hrdepartment.model.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffersRepo extends JpaRepository<Offers, Long> {
}
