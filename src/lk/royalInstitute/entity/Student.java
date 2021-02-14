package lk.royalInstitute.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Getter
@Setter
public class Student implements SuperEntity {
    @Id
    private String student_ID;
    private String studentName;
    private String address;
    private String contact;
    private Date dob;
    private String gender;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registration;
}
