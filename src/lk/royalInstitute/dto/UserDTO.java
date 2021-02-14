package lk.royalInstitute.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
public class UserDTO {
    private String userID;
    private String userName;
    private String password;
}
