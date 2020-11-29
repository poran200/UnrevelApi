package com.unrevel.api.services.impl;

import com.unrevel.api.dto.RelevenetQSDto;
import com.unrevel.api.dto.Response;
import com.unrevel.api.model.RelevantQuestion;
import com.unrevel.api.repository.RelevantQuestionsRepository;
import com.unrevel.api.services.RelevantQuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.unrevel.api.utill.ResponseBuilder.*;

@Service
public class RelevantQuestionServiceImpl implements RelevantQuestionService {
    private final RelevantQuestionsRepository relevantQuestionsRepository;
    private final ModelMapper modelMapper;
    public RelevantQuestionServiceImpl(RelevantQuestionsRepository relevantQuestionsRepository, ModelMapper modelMapper) {
        this.relevantQuestionsRepository = relevantQuestionsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response create(RelevenetQSDto dto) {
        var question = modelMapper.map(dto, RelevantQuestion.class);
        var saveQs = relevantQuestionsRepository.save(question);
        if (saveQs == null) return getFailureResponse(HttpStatus.BAD_REQUEST,"qustion not created");
        return getSuccessResponse(HttpStatus.OK,"Qusetion created",modelMapper.map(saveQs,RelevenetQSDto.class));
    }

    @Override
    public Response getAllQuestions() {
      List<RelevantQuestion> questions = relevantQuestionsRepository.findAll();
      questions.forEach(question-> modelMapper.map(question,RelevenetQSDto.class));
      return getSuccessResponseList(HttpStatus.OK,"all rlevent  question",questions,questions.size());
    }

    @Override
    public Response findById(long id) {
        var optional = relevantQuestionsRepository.findById(id);
        if (optional.isPresent()) return getSuccessResponse(HttpStatus.OK,"found question",modelMapper.map(optional.get(),RelevenetQSDto.class));
        return getFailureResponse(HttpStatus.NOT_FOUND,"Question not found");

    }
}
