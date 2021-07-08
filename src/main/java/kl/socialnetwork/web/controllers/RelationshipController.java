package kl.socialnetwork.web.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import kl.socialnetwork.domain.models.serviceModels.RelationshipServiceModel;
import kl.socialnetwork.domain.models.viewModels.relationship.FriendsAllViewModel;
import kl.socialnetwork.services.RelationshipService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

}
