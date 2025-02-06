package com.hr.scms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.scms.model.RegisterUser;

@Repository
public interface UserInfoRepository extends JpaRepository<RegisterUser, String> {

	Optional<RegisterUser> findByUsername(String username);

}
