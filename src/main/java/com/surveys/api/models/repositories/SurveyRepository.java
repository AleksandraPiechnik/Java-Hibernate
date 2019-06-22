package com.surveys.api.models.repositories;

import com.surveys.api.models.entities.data_base_entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey,Integer> {
}
