package com.booking.yoya.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.User;


@Repository
public interface UserDao extends CrudRepository<User, String> {
}
