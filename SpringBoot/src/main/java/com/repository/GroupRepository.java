package com.repository;

import com.entity.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {
    /*List<Group> findByowener_name(String o);
    List<Group> findBymember_name(String o);*/
}