package be.intecbrussel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    @Column ( name = "id", nullable = false )
    private Long id;

    @Column ( name = "title", nullable = false, unique = true )
    private String title;

    @Lob
    @Column ( name = "description" )
    private String description;

    @Column ( name = "active", nullable = false )
    private Boolean active = true;

}
