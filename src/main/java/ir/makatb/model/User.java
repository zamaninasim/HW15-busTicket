package ir.makatb.model;

import ir.makatb.enums.Gender;
import lombok.Data;

import javax.persistence.*;
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
    @Enumerated
    private Gender gender;
    private Date birthdate;
    @OneToMany
    private List<Address> address;
    private String email;
}
