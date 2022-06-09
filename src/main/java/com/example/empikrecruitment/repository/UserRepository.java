package com.example.empikrecruitment.repository;

import com.example.empikrecruitment.model.LoginCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LoginCounter, String> {
}
