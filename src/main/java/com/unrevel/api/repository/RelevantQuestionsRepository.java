package com.unrevel.api.repository;

import com.unrevel.api.model.RelevantQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelevantQuestionsRepository extends JpaRepository<RelevantQuestion,Long> {
}
