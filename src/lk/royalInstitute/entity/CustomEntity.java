package lk.royalInstitute.entity;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
public class CustomEntity implements SuperEntity {
    private String regNo;
    private String regDate;
    private double regFee;
    private String student_ID;
    private String studentName;
    private String contact;
    private Date dob;
    private String course_code;
    private String courseName;
    private double courseFee;
    private String duration;


}
