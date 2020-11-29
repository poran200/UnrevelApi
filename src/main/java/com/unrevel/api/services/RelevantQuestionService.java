package com.unrevel.api.services;

import com.unrevel.api.dto.RelevenetQSDto;
import com.unrevel.api.dto.Response;

public interface RelevantQuestionService {
    public Response  create(RelevenetQSDto dto);
    public Response getAllQuestions();
    public Response findById(long id);
}
