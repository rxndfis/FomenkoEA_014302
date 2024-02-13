package com.hrdepartment.repo;

import com.hrdepartment.model.AppAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppAnswerRepo extends JpaRepository<AppAnswer, Long> {
}
