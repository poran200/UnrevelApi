package com.unrevel.api.services.impl;

import com.unrevel.api.dto.Response;
import com.unrevel.api.model.ImageFile;
import com.unrevel.api.repository.ImageRepository;
import com.unrevel.api.services.ImageStorageService;
import com.unrevel.api.utill.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Objects;
@Service
public class ImageStorageServiceImpl implements ImageStorageService {
    private final ImageRepository imageRepository;

    public ImageStorageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Response saveImage(MultipartFile file) {
        String fileNme = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try{
            if (fileNme.contains("..")){
                return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST,"Sorry the file name contains invalid path sequence");
            }

//            Encoder encoder = Base64.getEncoder();

            ImageFile imageFile = new ImageFile(null,fileNme,file.getContentType(),file.getBytes());
            var saveFile = imageRepository.save(imageFile);
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED,"File uploaded successfully ",saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST,"Could not store the file "+fileNme+" please try again !");
        }
    }

    @Override
    public Response getImageById(String imageId) {
        var optionalImageFile = imageRepository.findById(imageId);
        if (optionalImageFile.isPresent()){
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK,"File found !",optionalImageFile.get());
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND,"file not found imageId = "+imageId);
    }
}
