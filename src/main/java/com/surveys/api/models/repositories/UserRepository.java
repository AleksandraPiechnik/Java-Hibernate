package com.surveys.api.models.repositories;

import com.surveys.api.models.entities.data_base_entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer > {
}
