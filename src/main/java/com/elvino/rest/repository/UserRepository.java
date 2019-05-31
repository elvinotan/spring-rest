package com.elvino.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elvino.rest.model.UserBean;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Long>{
	
	public Optional<UserBean> findById(Long id);
}
