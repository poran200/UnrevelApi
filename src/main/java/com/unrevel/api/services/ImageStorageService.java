package com.unrevel.api.services;

import com.unrevel.api.dto.Response;
import org.springframework.web.multipart.MultipartFile;


public interface ImageStorageService {
  public Response saveImage(MultipartFile file);
  public Response getImageById(String imageId);
}
