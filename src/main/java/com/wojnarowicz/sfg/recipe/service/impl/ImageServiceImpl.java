package com.wojnarowicz.sfg.recipe.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wojnarowicz.sfg.recipe.service.ImageService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ImageServiceImpl implements ImageService {

    @Override
    public void saveImageFile(Long id, MultipartFile file) {
       log.debug("Received an image"); 
    }
}
