// This is used to save user information to the MySQL database.
package com.ncjs.Travel.Diary.service;

import com.ncjs.Travel.Diary.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ncjs.Travel.Diary.web.dto.UserRegistrationDto;
import com.ncjs.Travel.Diary.models.User;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
       User user = new User(registrationDto.getUserName(),
               registrationDto.getPassword(), registrationDto.getConfirmPassword(),
               registrationDto.getEmail(), registrationDto.getVerified());
//       at this time, the password will be stored as text.
//       It can be encrypted later.
        return userRepository.save(user);

    }

}