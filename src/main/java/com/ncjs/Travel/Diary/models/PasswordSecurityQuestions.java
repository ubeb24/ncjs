package com.ncjs.Travel.Diary.models;

//import com.ncjs.Travel.Diary.models.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//does this need to extend abstract entity? --> NO
@Entity
public class PasswordSecurityQuestions {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public int id;
    @Size(max = 100, message = "Please enter up to 100 characters")
    @NotBlank(message = "Required field, if unknown, please put 'unknown'")
    private String momMaidenName;
    @Size(max = 100, message = "Please enter up to 100 characters")
    @NotBlank(message = "Required field, if unknown, please put 'unknown'")
    private String birthLocation;

    @Size(max = 100, message = "Please enter up to 100 characters")
    @NotBlank(message = "Required field. If unknown, please put 'unknown'")
    private String firstKiss;
    @Size(max = 100, message = "Please enter up to 100 characters")
    @NotBlank(message = "Required field, if unknown, please put 'unknown'")
    private String firstLocation;

    @Size(max = 100, message = "Please enter up to 100 characters")
    @NotBlank(message = "Required field, if unknown, please put 'unknown'")
    private String firstWord;

    public PasswordSecurityQuestions(String momMaidenName, String birthLocation, String firstKiss, String firstLocation, String firstWord) {
        this.momMaidenName = momMaidenName;
        this.birthLocation = birthLocation;
        this.firstKiss = firstKiss;
        this.firstLocation = firstLocation;
        this.firstWord = firstWord;
    }

    public  PasswordSecurityQuestions() {};

    public String getMomMaidenName() {
        return momMaidenName;
    }

    public void setMomMaidenName(String momMaidenName) {
        this.momMaidenName = momMaidenName;
    }

    public String getBirthLocation() {
        return birthLocation;
    }

    public void setBirthLocation(String birthLocation) {
        this.birthLocation = birthLocation;
    }

    public String getFirstKiss() {
        return firstKiss;
    }

    public void setFirstKiss(String firstKiss) {
        this.firstKiss = firstKiss;
    }

    public String getFirstLocation() {
        return firstLocation;
    }

    public void setFirstLocation(String firstLocation) {
        this.firstLocation = firstLocation;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }
}