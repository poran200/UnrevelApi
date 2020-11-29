package com.unrevel.api.controller;

import com.unrevel.api.anotation.APiController;
import com.unrevel.api.model.ImageFile;
import com.unrevel.api.services.ImageStorageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import static com.unrevel.api.utill.UrlConstrains.FileManagement.*;

@APiController
@RequestMapping(ROOT)
public class ImageUploadController {
    private final ImageStorageService imageStorageService;

    public ImageUploadController(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }


    @PostMapping(UPLOAD)
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        var response = imageStorageService.saveImage(file);
        ImageFile imageFile = (ImageFile) response.getContent();
        String fileUri = ServletUriComponentsBuilder.fromContextPath(request)
                          .path(ROOT+"/")
                          .path(imageFile.getId())
                          .toUriString();
        if (response.getStatusCode() == 201){
            response.setContent(fileUri);

            return ResponseEntity.status((int) response.getStatusCode()).body(response);
        }else {
            return ResponseEntity.status((int) response.getStatusCode()).body(response);
        }
    }
    @GetMapping(FIND_BY_ID)
    public ResponseEntity<Object> getFile(@PathVariable String id){
        var response = imageStorageService.getImageById(id);
        if (response.getStatusCode() == 200){
            var file = (ImageFile)response.getContent();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getFileType()))
                        //user for download
//                     .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= \""+file.getFileName()+"\"")
                     .body(new ByteArrayResource((file.getData())));
        }else {
            return ResponseEntity.status((int) response.getStatusCode()).body(response);
        }
    }
}
