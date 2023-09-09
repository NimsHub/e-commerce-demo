package com.nimshub.softwarearchitecturedemo.service;

import com.nimshub.softwarearchitecturedemo.dao.RoleDao;
import com.nimshub.softwarearchitecturedemo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
