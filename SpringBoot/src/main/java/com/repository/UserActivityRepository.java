package com.repository;

import com.entity.Group;
import com.entity.UserActivity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserActivityRepository extends CrudRepository<UserActivity, Long> {
}