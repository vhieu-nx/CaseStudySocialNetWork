package kl.socialnetwork.repository;

import kl.socialnetwork.model.AppUser;
import kl.socialnetwork.model.Relationship;
import kl.socialnetwork.model.RelationshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRelationshipRepository extends JpaRepository<Relationship, Long> {
    Iterable<Relationship> findAllByUserReceiveIdAndStatus(Long userReceiveId, RelationshipStatus status);

    Relationship findRelationshipByUserSendIdAndUserReceiveId(Long userSendId, Long userReceiveId);

    Iterable<Relationship> findAllByUserSendIdOrUserReceiveIdAndStatus(Long userSendId, Long userReceiveId, RelationshipStatus status);

    Iterable<Relationship> findAllByUserSendIdAndStatusOrUserReceiveIdAndStatus(Long userSendId, RelationshipStatus firstStatus, Long userReceiveId, RelationshipStatus secondStatus);

    List<Relationship> findAllByUserSendIdOrUserReceiveId(AppUser userSendId, AppUser userReceiveId);
}
