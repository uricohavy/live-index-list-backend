package com.list.index.live.live_index_list.controllers;

import com.list.index.live.live_index_list.exceptions.CustomHttpException;
import com.list.index.live.live_index_list.exceptions.InternalServerErrorException;
import com.list.index.live.live_index_list.exceptions.UnauthorizedException;
import com.list.index.live.live_index_list.models.User;
import com.list.index.live.live_index_list.models.dtos.UserToken;
import com.list.index.live.live_index_list.services.JwtService;
import com.list.index.live.live_index_list.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    JwtService jwtService;

    @Autowired
    public UserController(UserService userService,
                          JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * Registers a new User with optional Educator details.
     *
     * @param userEducator an object containing User fields and optional Educator fields to be registered
     * @return a ResponseEntity containing the userId of the newly created User, or the educatorId if the User is an Educator, with a 201 status code on success. Returns an error message and appropriate status code on failure.
     */
//    @PostMapping
//    public ResponseEntity<Object> addUser(@RequestBody UserEducator userEducator) {
//
//        try {
//            User newUser = userService.addUser(userEducator.getUser());
//
//            if (newUser.getRole().equals(Role.educator)) {
//                Educator extractedEducator = userEducator.getEducator();
//                extractedEducator.setEducatorId(newUser.getUserId());
//                Educator newEducator = educatorService.addEducator(extractedEducator);
//                return new ResponseEntity<>(newEducator.getEducatorId(), HttpStatus.CREATED);
//            }
//            return new ResponseEntity<>(newUser.getUserId(), HttpStatus.CREATED);
//        } catch (CustomHttpException e) {
//            return new ResponseEntity<>(e.getMessage(), e.getStatus());
//        }
//    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully!");
    }

    /**
     * Endpoint for verifying a User login.
     *
     * @param user A User object containing an email/password combination to be verified.
     * @return A ResponseEntity containing a UserToken object on success, which includes a JWT Token as a string and User details, along with a 200 status code. If the login fails, it returns an error message and an appropriate status code.
     */
//    @PostMapping("/login")
//    public ResponseEntity<Object> loginUser(@RequestBody User user) {
//
//        try {
//            Integer validUserId = userService.verifyUser(user);
//            String jwt = jwtService.generateJwt(validUserId);
//            UserToken userToken = new UserToken(jwt, userService.getUser(validUserId));
//            return new ResponseEntity<>(userToken, HttpStatus.OK);
//        } catch (CustomHttpException e) {
//            return new ResponseEntity<>(e.getMessage(), e.getStatus());
//        }
//    }
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User user) {
        try {
            // Verify the user's credentials
            Integer validUserId = userService.verifyUser(user);

            // If user credentials are invalid, verifyUser throws an exception
            if (validUserId == null) {
                return new ResponseEntity<>("Invalid credentials!", HttpStatus.UNAUTHORIZED);
            }

            // If credentials are valid, generate the JWT
            String jwt = jwtService.generateJwt(validUserId);
            System.out.println("JWT generated: " + jwt);

            // Create a UserToken response with the JWT and user details
            User storedUser = userService.getUser(validUserId); // Retrieve user details by userId
            UserToken userToken = new UserToken(jwt, storedUser);

            return new ResponseEntity<>(userToken, HttpStatus.OK);

        } catch (UnauthorizedException e) {
            // Handle invalid credentials
            return new ResponseEntity<>("Invalid credentials!", HttpStatus.UNAUTHORIZED);
        } catch (InternalServerErrorException e) {
            // Handle any errors related to password encryption or internal errors
            return new ResponseEntity<>("An error occurred while processing your login request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Endpoint for retrieving the currently logged-in User and Educator information given a valid JWT authorization.
     *
     * @param authorization The JWT authorization token of the currently logged-in User.
     * @return A ResponseEntity containing the User and Educator data (if applicable) along with a 200 status code on success, or an error message and appropriate status code on failure.
     */
    @GetMapping
    public ResponseEntity<Object> getCurrentUser(@RequestHeader String authorization) {

        try {
            User currentUser = jwtService.getUserFromToken(authorization);

//            if (currentUser.getRole().equals(Role.educator)) {
//                Educator currentEducator = educatorService.getEducator(currentUser.getUserId());
//                UserEducator dto = userService.combineUserAndEducator(currentUser, currentEducator);
//                return new ResponseEntity<>(dto, HttpStatus.OK);
//            }
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        } catch (CustomHttpException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    /**
     * Endpoint for retrieving User and Educator information given a valid User ID.
     *
     * @param userId The ID of the User whose data is being requested.
     * @return A ResponseEntity containing the User and Educator data (if applicable) along with a 200 status code on success, or an error message and appropriate status code on failure.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Integer userId) {

        try {
            User existingUser = userService.getUser(userId);

            existingUser.setEmail(null);
            existingUser.setPassword(null);

//            if (existingUser.getRole().equals(Role.educator)) {
//                Educator existingEducator = educatorService.getEducator(existingUser.getUserId());
//                UserEducator dto = userService.combineUserAndEducator(existingUser, existingEducator);
//                return new ResponseEntity<>(dto, HttpStatus.OK);
//            }
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } catch (CustomHttpException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }


    @PostMapping("/delete/{idToDelete}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer idToDelete, @RequestHeader("Authorization") String token) {
        try {
            // Extract user ID from JWT token
            Integer requestUserId = jwtService.getUserFromToken(token).getUserId();

            // Call delete method, passing the logged-in user's ID
            userService.deleteUser(requestUserId, idToDelete);

            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>("You are not authorized to delete this user", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}