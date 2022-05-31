package nuc.ss.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HumanResource {
    private int id;
    @JsonProperty("AHRid")
    private int AHRid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("sex")
    private String sex;
    @JsonProperty("Email")
    private String Email;
    @JsonProperty("tel")
    private String tel;
    @JsonProperty("QQ")
    private String QQ;
    @JsonProperty("Phone")
    private String Phone;
    @JsonProperty("Location")
    private String Location;
    @JsonProperty("ZipCode")
    private String ZipCode;
    @JsonProperty("Nationality")
    private String Nationality;
    @JsonProperty("Birthplace")
    private String Birthplace;
    @JsonProperty("Birthday")
    private String Birthday;
    @JsonProperty("Nation")
    private String Nation;
    @JsonProperty("Belief")
    private String Belief;
    @JsonProperty("PoliticalOutlook")
    private String PoliticalOutlook;
    @JsonProperty("IDNumber")
    private String IDNumber;
    @JsonProperty("SocialSecurityNumber")
    private String SocialSecurityNumber;
    @JsonProperty("Age")
    private String Age;
    @JsonProperty("Education")
    private String Education;
    @JsonProperty("Major")
    private String Major;
    @JsonProperty("SalaryStandard")
    private String SalaryStandard;
    @JsonProperty("AccountOpening")
    private String AccountOpening;
    @JsonProperty("AccountNumber")
    private String AccountNumber;
    @JsonProperty("Specialty")
    private String Specialty;
    @JsonProperty("Hobbies")
    private String Hobbies;
    @JsonProperty("PersonalResume")
    private String PersonalResume;
    @JsonProperty("FamilyRelationshipInformation")
    private String FamilyRelationshipInformation;
    @JsonProperty("Note")
    private String Note;
    @JsonProperty("Registrant")
    private String Registrant;
    @JsonProperty("RegistrationTime")
    private String RegistrationTime;
    @JsonProperty("State")
    private int State;
    @JsonProperty("Image")
    private String Image;
}
