package com.example.demo.service;

import com.example.demo.models.Picture;
import com.example.demo.models.Users;
import com.example.demo.repository.PictureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    private final static Logger logger = LoggerFactory.getLogger(PictureService.class);

    public PictureService(PictureRepository pictureRepository){
        this.pictureRepository = pictureRepository;
    }

    public Picture upload(MultipartFile file, Users author){
        logger.info("Upload started in PictureService.");
        if(file.isEmpty())
            return null;
        try {
            Picture picture = new Picture();
            picture.setImageData(file.getBytes());
            picture.setAuthor(author);
            picture.setContentType(file.getContentType());
            picture.setFilename(file.getOriginalFilename());
            return pictureRepository.save(picture);
        } catch (IOException e) {
            logger.warn("No image found.", e);
            return null;
        }
    }

    public List<Picture> getAllPictures(){
        logger.info("GetAllPicture started.");
        return pictureRepository.findAll();
    }

    public Picture getPictureById(Integer id){
        logger.info("GetPictureById started.");
        return pictureRepository.findById(id).orElse(null);
    }
}
