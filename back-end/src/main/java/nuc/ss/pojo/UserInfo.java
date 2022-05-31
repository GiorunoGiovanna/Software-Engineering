package nuc.ss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String[] role;
    private int id;
    private String introduction;
    private String avatar;
    private String name;
}
