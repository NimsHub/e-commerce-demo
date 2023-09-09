package com.nimshub.softwarearchitecturedemo.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleDao extends JpaRepository<Role, String> {

}
