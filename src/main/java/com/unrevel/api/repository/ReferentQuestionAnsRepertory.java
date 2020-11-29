package com.unrevel.api.repository;

import com.unrevel.api.model.RelevantQsAns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferentQuestionAnsRepertory extends JpaRepository<RelevantQsAns, Long> {

}
