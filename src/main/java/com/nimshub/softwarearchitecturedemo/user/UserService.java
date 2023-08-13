package com.nimshub.softwarearchitecturedemo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * get user details
     */
    public UserDetailsResponse getUserDetails(String userEmail){
        Optional<User> userOptional = userRepository.findByEmail(userEmail);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            return UserDetailsResponse.builder()
                    .firstname(user.getFirstName())
                    .lastname(user.getLastName())
                    .email(user.getEmail())
                    // Add more properties as needed
                    .build();
        } else {
            // Handle the case when the user is not found
            // todo throw an exception, or return a default response
            return null;
        }
    }
}
