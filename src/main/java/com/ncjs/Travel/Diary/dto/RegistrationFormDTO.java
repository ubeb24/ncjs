//Cheri's Code
package com.ncjs.Travel.Diary.dto;

import com.ncjs.Travel.Diary.models.PasswordSecurityQuestions;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrationFormDTO extends LoginFormDTO {

    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 20,
            message = "Invalid Password. Must be between 6 and 20 characters.")
    private String verifyPassword;

    @NotNull(message = "Email is required.")
    @NotBlank(message = "Email is required.")
    @Size(min = 6, max = 30,
            message = "Invalid Email. Must be between 6 and 30 characters.")
    @Email(message = "Invalid email. Try again.")
    private String email;

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public PasswordSecurityQuestions getPasswordSecurityQuestions() {
        return passwordSecurityQuestions;
    }

    public void setPasswordSecurityQuestions(PasswordSecurityQuestions passwordSecurityQuestions) {
        this.passwordSecurityQuestions = passwordSecurityQuestions;
    }

    @NotNull (message ="Answers are required for registration.")
    private PasswordSecurityQuestions passwordSecurityQuestions;

    public String getVerifyPassword( ) { return verifyPassword; }
    // no need to set verifyPassword because we are not saving it
//    public void setVerifyPassword(String verifyPassword) {
//        this.verifyPassword = verifyPassword;
//    }

    public String getEmail( ) { return email; }
    public void setEmail(String email) { this.email = email; }

}