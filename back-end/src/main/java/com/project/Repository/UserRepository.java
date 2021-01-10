package com.project.Repository;

import com.project.Entities.Project;
import com.project.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    Boolean existsByUsername(String username);
    void deleteById(int id);
}
