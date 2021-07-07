package kl.socialnetwork.service.relationship;

import kl.socialnetwork.model.AppUser;
import kl.socialnetwork.model.Relationship;
import kl.socialnetwork.model.RelationshipStatus;
import java.util.List;

public interface IRelationshipService {
    Relationship saveRelationship(Relationship relationship);

    void deleteRelationship(Long id);

    Iterable<Relationship> findAllRelationship();

    Relationship findRelationshipById(Long id);

    Iterable<Relationship> findAllByUserReceiveIdAndStatus(Long userReceiveId, RelationshipStatus status);

    Relationship findRelationshipByUserSendIdAndUserReceiveId(Long userSendId, Long userReceiveId);

    Iterable<Relationship> findAllByUserSendIdOrUserReceiveIdAndStatus(Long userSendId, Long userReceiveId, RelationshipStatus status);

    Iterable<Relationship> findAllByUserSendIdAndStatusOrUserReceiveIdAndStatus(Long userSendId, RelationshipStatus firstStatus, Long userReceiveId, RelationshipStatus secondStatus);

    List<AppUser> findAllSimilarFriend(AppUser userSendId, AppUser userReceiveId);

    Iterable<Relationship> findAllByUserSendOrUserReceiver(AppUser userSendId, AppUser userReceiveId);

    List<AppUser> findListFriendByUser(AppUser appUser);
}
