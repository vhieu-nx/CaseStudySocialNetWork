package kl.socialnetwork.web.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import kl.socialnetwork.domain.models.serviceModels.RelationshipServiceModel;
import kl.socialnetwork.domain.models.viewModels.relationship.FriendsAllViewModel;
import kl.socialnetwork.services.RelationshipService;
import kl.socialnetwork.utils.responseHandler.exceptions.CustomException;
import kl.socialnetwork.utils.responseHandler.successResponse.SuccessResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kl.socialnetwork.utils.constants.ResponseMessageConstants.*;

@RestController
@RequestMapping(value = "/relationship")
public class RelationshipController {
    private final RelationshipService relationshipService;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    public RelationshipController(RelationshipService relationshipService, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.relationshipService = relationshipService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }
    @GetMapping(value = "/friends/{id}")
    public List<FriendsAllViewModel> findAllFriends(@PathVariable String id) throws Exception {
        List<RelationshipServiceModel> allFriends = this.relationshipService.findAllUserRelationshipsWithStatus(id);

        List<FriendsAllViewModel> friendsAllViewModels = allFriends.stream().map(relationshipServiceModel -> {
            if (!relationshipServiceModel.getUserOne().getId().equals(id)) {
                return this.modelMapper.map(relationshipServiceModel.getUserOne(), FriendsAllViewModel.class);
            }

            return this.modelMapper.map(relationshipServiceModel.getUserTwo(), FriendsAllViewModel.class);
        }).collect(Collectors.toList());

        return friendsAllViewModels;
    }
    @PostMapping(value = "/addFriend")
    public ResponseEntity addFriend(@RequestBody Map<String, Object> body) throws Exception {
        String loggedInUserId = (String) body.get("loggedInUserId");
        String friendCandidateId = (String) body.get("friendCandidateId");

        boolean result = this.relationshipService.createRequestForAddingFriend(loggedInUserId, friendCandidateId);

        if (result) {
            SuccessResponse successResponse = new SuccessResponse(LocalDateTime.now(), SUCCESSFUL_FRIEND_REQUEST_SUBMISSION_MESSAGE, "", true);

            return new ResponseEntity<>(this.objectMapper.writeValueAsString(successResponse), HttpStatus.OK);
        }

        throw new CustomException(SERVER_ERROR_MESSAGE);
    }
    @PostMapping(value = "/removeFriend")
    public ResponseEntity removeFriend(@RequestBody Map<String, Object> body) throws Exception {
        String loggedInUserId = (String) body.get("loggedInUserId");
        String friendToRemoveId = (String) body.get("friendToRemoveId");

        boolean result = this.relationshipService.removeFriend(loggedInUserId, friendToRemoveId);

        if (result) {
            SuccessResponse successResponse = new SuccessResponse(LocalDateTime.now(), SUCCESSFUL_FRIEND_REMOVE_MESSAGE, "", true);
            return new ResponseEntity<>(this.objectMapper.writeValueAsString(successResponse), HttpStatus.OK);
        }

        throw new CustomException(SERVER_ERROR_MESSAGE);
    }
    @PostMapping(value = "/acceptFriend")
    public ResponseEntity acceptFriend(@RequestBody Map<String, Object> body) throws Exception {
        String loggedInUserId = (String) body.get("loggedInUserId");
        String friendToAcceptId = (String) body.get("friendToAcceptId");

        boolean result = this.relationshipService.acceptFriend(loggedInUserId, friendToAcceptId);

        if (result) {
            SuccessResponse successResponse = new SuccessResponse(LocalDateTime.now(), SUCCESSFUL_ADDED_FRIEND_MESSAGE, "", true);
            return new ResponseEntity<>(this.objectMapper.writeValueAsString(successResponse), HttpStatus.OK);
        }
        throw new CustomException(SERVER_ERROR_MESSAGE);
    }


}
