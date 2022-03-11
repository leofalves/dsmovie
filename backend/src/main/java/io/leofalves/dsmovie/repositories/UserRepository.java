package io.leofalves.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.leofalves.dsmovie.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);

}
