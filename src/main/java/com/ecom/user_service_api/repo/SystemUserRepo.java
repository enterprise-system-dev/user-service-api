package com.ecom.user_service_api.repo;

import com.ecom.user_service_api.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepo extends JpaRepository<SystemUser, String> {
}
