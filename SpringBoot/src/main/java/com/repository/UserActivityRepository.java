package com.repository;

import com.entity.UserActivity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface UserActivityRepository extends CrudRepository<UserActivity, Long> {

}