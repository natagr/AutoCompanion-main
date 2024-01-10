package com.auto.companion.user.controller;


import com.auto.companion.domain.model.User;
import com.auto.companion.user.model.PasswordChangeRequest;
import com.auto.companion.user.model.UserDto;
import com.auto.companion.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.getUserById(user.getId());
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UserDto userDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        userService.updateUser(user.getId(), userDto);
    }

    @PatchMapping("/new-password")
    @ResponseStatus(HttpStatus.OK)
    public void updatePasswordForUser(@RequestBody PasswordChangeRequest passwordChangeRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        userService.updatePasswordForUser(user.getId(), passwordChangeRequest);
    }

    @PostMapping(value = "/uploadImage",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void uploadImage(@RequestParam("image") MultipartFile image, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        userService.updateImage(user.getId(), image);
    }

    @GetMapping("/image/{userId}")
    public ResponseEntity<?> getUserImage(@PathVariable Long userId) {
        try {
            byte[] imageData = userService.getUserImage(userId);
            if (imageData == null || imageData.length == 0) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new ByteArrayResource(imageData));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
