package com.elvino.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elvino.rest.model.RoleBean;

@Repository
public interface RoleRepository extends JpaRepository<RoleBean, Long>{

}
