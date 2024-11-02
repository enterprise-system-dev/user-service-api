package com.ecom.user_service_api.repo;

import com.ecom.user_service_api.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemUserRepo extends JpaRepository<SystemUser, String> {
    Optional<SystemUser> findByEmail(String email);
}
