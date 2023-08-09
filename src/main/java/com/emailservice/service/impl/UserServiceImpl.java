package com.emailservice.service.impl;

import com.emailservice.entity.User;
import com.emailservice.payload.UserDto;
import com.emailservice.repository.UserRepository;
import com.emailservice.service.EmailService;
import com.emailservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Transactional
    @Override
    public UserDto saveUser(UserDto userDto) {

        User user = mapToEntity(userDto);
        User save = userRepository.save(user);

        String email = user.getEmail();
        String subject = "Welcome To 3KT";
        String text = "Thank You For Registering:\n" +
                "First Name: " + user.getFirstName() + "\n" +
                "Last Name : " + user.getLastName() + "\n" +
                "Email I'd : " + user.getEmail();

        try {
            emailService.sendRegistrationEmail(email, subject, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapToDto(save);

    }

    public User mapToEntity(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    public UserDto mapToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }


}
