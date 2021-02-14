package lk.royalInstitute.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Getter
@Setter
public class Course implements SuperEntity {
    @Id
    private String course_code;
    private String courseName;
    private String duration;
    private double courseFee;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Registration> registration;
}
