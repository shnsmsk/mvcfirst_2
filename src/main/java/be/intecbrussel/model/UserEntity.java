package be.intecbrussel.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    @Column ( name = "id", nullable = false )
    private Long id;

    @Column ( name = "fname" )
    private String fname;

    @Column ( name = "mname" )
    private String mname;

    @Column ( name = "lname" )
    private String lname;

    @Column ( name = "email", nullable = false, unique = true, length = 1000 )
    private String email;

    @Column ( name = "hashed_password", nullable = false, length = 1000 )
    private String hashedPassword;

    @Column ( name = "validation", nullable = false, length = 8 )
    private String validation;

    @Column ( name = "active", nullable = false )
    private Boolean active = true;


}
