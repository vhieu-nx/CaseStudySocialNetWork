package kl.socialnetwork.service.relationship;

import kl.socialnetwork.model.AppUser;
import kl.socialnetwork.model.Relationship;
import kl.socialnetwork.model.RelationshipStatus;
import kl.socialnetwork.repository.IRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RelationshipService implements IRelationshipService{
    @Autowired
    private IRelationshipRepository relationshipRepository;

    @Override
    public Relationship saveRelationship(Relationship relationship) {
        return relationshipRepository.save(relationship);
    }

    @Override
    public void deleteRelationship(Long id) {
        relationshipRepository.deleteById(id);
    }

    @Override
    public Iterable<Relationship> findAllRelationship() {
        return relationshipRepository.findAll();
    }

    @Override
    public Relationship findRelationshipById(Long id) {
        return relationshipRepository.findById(id).get();
    }

    @Override
    public Iterable<Relationship> findAllByUserReceiveIdAndStatus(Long userReceiveId, RelationshipStatus status) {
        return relationshipRepository.findAllByUserReceiveIdAndStatus(userReceiveId, status);
    }

    @Override
    public Relationship findRelationshipByUserSendIdAndUserReceiveId(Long userSendId, Long userReceiveId) {
        return relationshipRepository.findRelationshipByUserSendIdAndUserReceiveId(userSendId, userReceiveId);
    }

    @Override
    public Iterable<Relationship> findAllByUserSendIdOrUserReceiveIdAndStatus(Long userSendId, Long userReceiveId, RelationshipStatus status) {
        return relationshipRepository.findAllByUserSendIdOrUserReceiveIdAndStatus(userSendId, userReceiveId, status);
    }

    @Override
    public Iterable<Relationship> findAllByUserSendIdAndStatusOrUserReceiveIdAndStatus(Long userSendId, RelationshipStatus firstStatus, Long userReceiveId, RelationshipStatus secondStatus) {
        return relationshipRepository.findAllByUserSendIdAndStatusOrUserReceiveIdAndStatus(userSendId, firstStatus, userReceiveId, secondStatus);
    }

    @Override
    public List<AppUser> findAllSimilarFriend(AppUser userSendId, AppUser userReceiveId) {
        List<AppUser> userList = new ArrayList<>();
        List<AppUser> userListFriend1 = findListFriendByUser(userSendId);
        List<AppUser> userListFriend2 = findListFriendByUser(userReceiveId);
        for (AppUser friend1 : userListFriend1) {
            for (AppUser friend2 : userListFriend2) {
                if (friend1 == friend2) {
                    userList.add(friend1);
                }
            }
        }
        return userList;
    }


    @Override
    public Iterable<Relationship> findAllByUserSendOrUserReceiver(AppUser userSendId, AppUser userReceiveId) {
        return relationshipRepository.findAllByUserSendIdOrUserReceiveId(userSendId, userReceiveId);
    }

    @Override
    public List<AppUser> findListFriendByUser(AppUser user) {
//        List<AppUser> userList = new ArrayList<>();
//        if (user != null) {
//            List<Relationship> list = findAllByUserSendOrUserReceiver(user, user);
//            for (Relationship friend : list) {
//                if (friend.getUserSendId() == user) {
//                    userList.add(friend.getUserReceiveId());
//                } else {
//                    userList.add(friend.getUserSendId());
//                }
//            }
//            return userList;
//        }
        return null;
    }
}
