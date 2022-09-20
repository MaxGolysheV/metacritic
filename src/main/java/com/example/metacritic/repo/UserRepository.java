package com.example.metacritic.repo;

import com.example.metacritic.Models.User;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);



}
