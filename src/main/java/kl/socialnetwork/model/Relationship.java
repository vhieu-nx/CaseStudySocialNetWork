package kl.socialnetwork.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private RelationshipStatus status;

    private Long userSendId;
    private Long userReceiveId;

    public Relationship() {
    }

    public Relationship(Long id, Long userReceiveId) {

    }

    public RelationshipStatus getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatus status) {
        this.status = status;
    }
}
