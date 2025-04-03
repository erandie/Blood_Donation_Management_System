package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.ImageServiceImpl;
import org.example.blood_donation_api.dto.ImageDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
public class ImageController {

    @Autowired
    private ImageServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;

    // Endpoint to register user with image
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam("name") String name,
                                               @RequestParam("address") String address,
                                               @RequestParam("contact") String contact,
                                               @RequestParam("image") MultipartFile image) {
        try {
            ImageDTO userDTO = new ImageDTO();
            userDTO.setName(name);
            userDTO.setAddress(address);
            userDTO.setContact(contact);
            userService.saveUserWithImage(userDTO, image);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload image");
        }
    }

    // Endpoint to get user image
    @GetMapping("/image/{userId}")
    public ResponseEntity<byte[]> getUserImage(@PathVariable int userId) {
        byte[] image = userService.getUserImage(userId);
        return ResponseEntity.ok().body(image);
    }
}

