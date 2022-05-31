package nuc.ss.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPayment {
    private int id;
    @JsonProperty("AHRid")
    private int AHRid;
    @JsonProperty("AHRName")
    private String AHRName;
    @JsonProperty("Designee")
    private String Designee;
    @JsonProperty("DesigneeTime")
    private String DesigneeTime;
    @JsonProperty("Registrant")
    private String Registrant;
    @JsonProperty("Base")
    private Float Base;
    @JsonProperty("Transportation")
    private Float Transportation;
    @JsonProperty("Lunch")
    private Float Lunch;
    @JsonProperty("Communication")
    private Float Communication;
    @JsonProperty("PensionInsurance")
    private Float PensionInsurance;
    @JsonProperty("MedicalInsurance")
    private Float MedicalInsurance;
    @JsonProperty("UnemploymentInsurance")
    private Float UnemploymentInsurance;
    @JsonProperty("HousingProvidentFund")
    private Float HousingProvidentFund;
    @JsonProperty("Tatol")
    private Float Tatol;
    @JsonProperty("State")
    private int State;
    @JsonProperty("Suggestion")
    private String Suggestion;
}
