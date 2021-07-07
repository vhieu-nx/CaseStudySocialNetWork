package kl.socialnetwork.model;


import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Validated
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank
    private String username;

    private String password;
    private Date birthday;

    private String firstName;
    private String lastName;

    private String gender;

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,5}$")
    private String phone;

    @Email
    private String email;

    private String address;

    private String avatar;


    private Timestamp createdDate;

    private boolean blocked;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppRole> roles;
}
