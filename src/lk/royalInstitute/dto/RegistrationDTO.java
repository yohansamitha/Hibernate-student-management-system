package lk.royalInstitute.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
public class RegistrationDTO implements SuperDTO {
    @Id
    private String regNo;
    private String regDate;
    private double regFee;
    private String student_ID;
    private String course_code;
}
