package com.bank.services.user;

import com.bank.models.user.User;
import com.bank.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            throw new IllegalArgumentException("\n" +
                    "The id number does not exist in the database");
        }
        return user.get();
    }
    public User findByName(String name) {
        Optional<User> user = userRepository.findByName(name);
        if (!user.isPresent()){
            throw new IllegalArgumentException("\n" +
                    "The user not exist");
        }
        return user.get();
    }


}
