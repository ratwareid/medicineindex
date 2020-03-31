package com.ratwareid.spring.repository;

import com.ratwareid.spring.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Integer> {

    UserModel findUserModelByUsernameEqualsAndPasswordEquals(String username,String password);

    UserModel findUserModelByUseridEquals(int userid);
}
