package com.elvino.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elvino.rest.model.UserBean;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Long>{
	
	public Optional<UserBean> findById(Long id);
	
	@Query(value="select a from UserBean a where a.name= :name and a.email = :email", nativeQuery=false)
	public List<UserBean> findUserCriteria(@Param("name") String name, @Param("email") String email);
}
