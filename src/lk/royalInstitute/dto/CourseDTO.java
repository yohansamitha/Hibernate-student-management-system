package lk.royalInstitute.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Getter
@Setter
public class CourseDTO implements SuperDTO {
    @Id
    private String course_code;
    private String courseName;
    private String duration;
    private double courseFee;
}
