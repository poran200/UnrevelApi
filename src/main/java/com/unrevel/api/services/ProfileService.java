package com.unrevel.api.services;

import com.unrevel.api.dto.Response;
import com.unrevel.api.model.Profile;

public interface ProfileService {
    Response create(Profile profile);
    Response findByUseName(String userName);
    Profile  save(Profile profile);
}
