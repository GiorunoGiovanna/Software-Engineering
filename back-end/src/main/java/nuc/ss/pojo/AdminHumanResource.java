package nuc.ss.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminHumanResource {
    private int id;
    @JsonProperty("ILevel")
    private String ILevel;
    @JsonProperty("IILevel")
    private String IILevel;
    @JsonProperty("IIILevel")
    private String IIILevel;
    @JsonProperty("PositionType")
    private String PositionType;
    @JsonProperty("JobTitle")
    private String JobTitle;
}
