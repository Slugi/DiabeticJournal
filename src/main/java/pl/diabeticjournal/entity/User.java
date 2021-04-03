package pl.diabeticjournal.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Length(min = 5, message = "User name must have at least 5 characters")
    @NotEmpty(message = "Please provide a user name")
    private String userName;

    @Length(min = 5, message = "First name must have at least 5 characters")
    @NotEmpty(message = "Please provide a name")
    private String firstName;

    @Length(min = 5, message = "Last name must have at least 5 characters")
    @NotEmpty(message = "Please provide a name")
    private String lastName;

    @NotEmpty(message = "Please enter diabetic type")
    @Min(1)
    @Max(2)
    private int diabeticType;

    @Email(message = "Please provide a valid email")
    private String email;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    private Boolean active;

    @ManyToMany
    @JoinTable(name ="user_role", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;





}




