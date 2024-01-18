package com.ncjs.Travel.Diary.controllers;

import com.ncjs.Travel.Diary.models.User;
import com.ncjs.Travel.Diary.repository.UserRepository;
import com.ncjs.Travel.Diary.service.UserService;
import com.ncjs.Travel.Diary.web.dto.LoginFormDTO;
import com.ncjs.Travel.Diary.web.dto.RegisterFormDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("")
public class AuthenticationController {
// TODO Should this controller extend TravelDiaryApplication?
//public class AuthenticationController extends TravelDiaryApplication {
// TODO - or should this include a constructor like the lines below?
// constructor
//public UserController(UserService userService) {
//    super();
//    this.userService = userService;
//}


    @Autowired
    UserRepository userRepository;

    // userSessionKey stores user IDs
    private static final String userSessionKey = "user";

    // handles session creation and lookup
    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    // form processing
    // registration authentication
    @GetMapping("/register")
    public String displayRegistrationForm(Model model, HttpSession session) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        model.addAttribute("loggedIn",
            session.getAttribute(userSessionKey) != null);
        return "users/register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(
            @ModelAttribute @Valid RegisterFormDTO registerFormDto,
            Errors errors,
            HttpServletRequest request,
            Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "users/register";
        }

        User existingUser =
            userRepository.findByUsername(registerFormDto.getUsername());

        if (existingUser != null) {
            errors.rejectValue(
                    "username",
                    "username.already-exists",
                    "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "users/register";
        }

        String password = registerFormDto.getPassword();
        String verifyPassword = registerFormDto.getConfirmPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password",
                    "passwords.mismatch",
                    "Passwords do not match");
            model.addAttribute("title", "Register");
            return "users/register";
        }

        // At this point, the given username does NOT already exist
        // and the rest of the form data is valid.
        // Now create the user object, store it in the DB and create session.
        User newUser = new User(
                registerFormDto.getUsername(),
                registerFormDto.getPassword(),
                registerFormDto.getEmail(),
                registerFormDto.getVerified());
        userRepository.save(newUser);
//  TODO figure out whether or not to use userService when using userRepository
//        userService.save(registerFormDto);

        setUserInSession(request.getSession(), newUser);

        // return to the users/registration page with a Success message
        return "redirect:users/register?success";
        // send user to the next location
//        return "redirect:";
    }

    // login authentication
    @GetMapping("/login")
    public String displayLoginForm(Model model, HttpSession session) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        model.addAttribute("loggedIn",
                session.getAttribute(userSessionKey) != null);
        return "users/login";
    }

    @PostMapping("/login")
    public String processLoginForm(
            @ModelAttribute @Valid LoginFormDTO loginFormDTO,
            Errors errors, HttpServletRequest request,
            Model model) {

// TODO ask re: attributeName "title"
        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "users/login";
        }

        User theUser =
            userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue(
                    "username",
                    "user.invalid",
                    "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "users/login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue(
                    "password",
                    "password.invalid",
                    "Credentials invalid. Please try again with correct username/password combination.");
            model.addAttribute("title", "Log In");
            return "users/login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:";
    }

    // Logging out
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

}