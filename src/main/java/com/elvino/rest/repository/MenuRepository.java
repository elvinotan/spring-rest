package com.elvino.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elvino.rest.model.MenuBean;

@Repository
public interface MenuRepository extends JpaRepository<MenuBean, Long>{

}
