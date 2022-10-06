package be.intecbrussel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    @Column ( name = "id", nullable = false )
    private Long id;

    @ManyToOne ( optional = false )
    @JoinColumn ( name = "sender_id", nullable = false )
    private UserEntity sender;

    @ManyToMany
    @JoinTable ( name = "chats",
            joinColumns = @JoinColumn ( name = "message_id" ),
            inverseJoinColumns = @JoinColumn ( name = "receiver_id" ) )
    private Set < UserEntity > receivers = new LinkedHashSet <> ( );

    @Column ( name = "subject", nullable = false )
    private String subject;

    @Lob
    @Column ( name = "content" )
    private String content;

    @Column ( name = "views_count" )
    private Integer viewsCount;

    @Column ( name = "likes_count" )
    private Integer likesCount;

    @Column ( name = "active", nullable = false )
    private Boolean active = true;

    public void setReceivers(UserEntity receiver) {
        Set < UserEntity > receivers = new LinkedHashSet <> ( );
        receivers.add(receiver);
        this.receivers = receivers;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
