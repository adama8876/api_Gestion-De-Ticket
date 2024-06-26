package com.kalanso.creationApi.repository;

import com.kalanso.creationApi.modele.Admin;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
