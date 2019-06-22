package com.surveys.api.models.repositories;

import com.surveys.api.models.entities.data_base_entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
