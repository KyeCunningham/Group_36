package com.portfolio.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.backend.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

	Optional<Profile> findByUsername(String name);
	
}
