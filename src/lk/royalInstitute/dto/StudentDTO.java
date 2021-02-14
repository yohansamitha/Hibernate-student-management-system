package lk.royalInstitute.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Getter
@Setter
public class StudentDTO implements SuperDTO {
    @Id
    private String student_ID;
    private String studentName;
    private String address;
    private String contact;
    private Date dob;
    private String gender;
}
