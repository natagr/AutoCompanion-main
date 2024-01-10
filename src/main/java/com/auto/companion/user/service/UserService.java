package com.auto.companion.user.service;

import com.auto.companion.domain.model.User;
import com.auto.companion.domain.model.mapper.UserMapper;
import com.auto.companion.domain.repository.UserRepository;
import com.auto.companion.user.model.PasswordChangeRequest;
import com.auto.companion.user.model.UserDto;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void updateImage(Long userId, MultipartFile image) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setImageData(image.getBytes());
        userRepository.save(user);
    }

    public byte[] getUserImage(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getImageData();
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId).map(userMapper::toDto).orElseThrow(EntityExistsException::new);
    }

    @Transactional
    public void updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow();
        userMapper.updateUser(userDto, user);
    }

    @Transactional
    public void updatePasswordForUser(Long userId, PasswordChangeRequest passwordChangeRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->  new EntityExistsException("User not found"));
        user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
    }
}
