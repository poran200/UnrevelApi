package com.unrevel.api.services.impl;

import com.unrevel.api.dto.Response;
import com.unrevel.api.model.Profile;
import com.unrevel.api.repository.ProfileRepository;
import com.unrevel.api.repository.RelevantQuestionsRepository;
import com.unrevel.api.services.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final RelevantQuestionsRepository relevantQuestionsRepository;
    public ProfileServiceImpl(ProfileRepository profileRepository, RelevantQuestionsRepository relevantQuestionsRepository) {
        this.profileRepository = profileRepository;

        this.relevantQuestionsRepository = relevantQuestionsRepository;
    }

    @Override
    public Response create(Profile profile) {
        return null;
    }

    @Override
    public Response findByUseName(String userName) {
        return null;
    }

    @Override
    public Profile save(Profile profile) {
        profile.getRelevantQsAnsList().forEach(profile::addQuestionAns);

       return profileRepository.save(profile);
    }
}
