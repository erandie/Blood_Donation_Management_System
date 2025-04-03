package org.example.blood_donation_api.Service.Implementations;

import org.example.blood_donation_api.Entity.Image;
import org.example.blood_donation_api.Repo.ImageRepo;
import org.example.blood_donation_api.dto.ImageDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl {
    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Save user and image to the database
    public void saveUserWithImage(ImageDTO imageDTO, MultipartFile imageFile) throws Exception {
        Image image = modelMapper.map(imageDTO, Image.class);
        image.setImage(imageFile.getBytes());  // Convert image to byte array
        imageRepo.save(image);
    }

    // Get user image by userId
    public byte[] getUserImage(int userId) {
        Image image = imageRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return image.getImage();
    }
}

