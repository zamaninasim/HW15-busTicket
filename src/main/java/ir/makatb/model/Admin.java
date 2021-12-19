package ir.makatb.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@ToString(callSuper = true)
public class Admin extends User{
    private String password;
}
