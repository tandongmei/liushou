package com.ls.dao;

import com.ls.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tan.dongmei on 2017/12/1
 */
public interface IUserDao extends JpaRepository<User,Integer>{
}
