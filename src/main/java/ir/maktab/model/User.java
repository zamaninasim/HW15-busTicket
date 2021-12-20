package ir.maktab.model;

import ir.maktab.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String firstname;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Address> address = new ArrayList<>();
    private String email;
}
