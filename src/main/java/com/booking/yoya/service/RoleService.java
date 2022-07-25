package com.booking.yoya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.dao.RoleDao;
import com.booking.yoya.model.Role;

@Service
public class RoleService {

	@Autowired
	RoleDao roleDao;

	public Role createNewRole(Role role) {
		return roleDao.save(role);
	}
}
