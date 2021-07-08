package kl.socialnetwork.servicesImpl;

import kl.socialnetwork.domain.entities.Message;
import kl.socialnetwork.domain.entities.Relationship;
import kl.socialnetwork.domain.entities.User;
import kl.socialnetwork.domain.models.bindingModels.message.MessageCreateBindingModel;
import kl.socialnetwork.domain.models.serviceModels.MessageServiceModel;
import kl.socialnetwork.domain.models.viewModels.message.MessageFriendsViewModel;
import kl.socialnetwork.repositories.MessageRepository;
import kl.socialnetwork.repositories.RelationshipRepository;
import kl.socialnetwork.repositories.UserRepository;
import kl.socialnetwork.services.MessageService;
import kl.socialnetwork.utils.responseHandler.exceptions.CustomException;
import kl.socialnetwork.validations.serviceValidation.services.MessageValidationService;
import kl.socialnetwork.validations.serviceValidation.services.RelationshipValidationService;
import kl.socialnetwork.validations.serviceValidation.services.UserValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static kl.socialnetwork.utils.constants.ResponseMessageConstants.SERVER_ERROR_MESSAGE;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final RelationshipRepository relationshipRepository;
    private final UserRepository userRepository;
    private final UserValidationService userValidation;
    private final MessageValidationService messageValidation;
    private final RelationshipValidationService relationshipValidation;
    private final ModelMapper modelMapper;
    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, RelationshipRepository relationshipRepository, UserRepository userRepository, UserValidationService userValidation, MessageValidationService messageValidation, RelationshipValidationService relationshipValidation, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.relationshipRepository = relationshipRepository;
        this.userRepository = userRepository;
        this.userValidation = userValidation;
        this.messageValidation = messageValidation;
        this.relationshipValidation = relationshipValidation;
        this.modelMapper = modelMapper;
    }


    @Override
    public MessageServiceModel createMessage(MessageCreateBindingModel messageCreateBindingModel, String loggedInUsername) throws Exception {
        if (!messageValidation.isValid(messageCreateBindingModel)) {
            throw new CustomException(SERVER_ERROR_MESSAGE);
        }
        User fromUser = userRepository
                .findByUsername(loggedInUsername)
                .filter(userValidation::isValid)
                .orElseThrow(() -> new CustomException(SERVER_ERROR_MESSAGE));

        User toUser = userRepository
                .findById(messageCreateBindingModel.getToUserId())
                .filter(userValidation::isValid)
                .orElseThrow(() -> new CustomException(SERVER_ERROR_MESSAGE));


        Relationship relationship = relationshipRepository
                .findRelationshipWithFriendWithStatus(fromUser.getId(), toUser.getId(), 1);

        if (!relationshipValidation.isValid(relationship)) {
            throw new CustomException(SERVER_ERROR_MESSAGE);
        }
        MessageServiceModel messageServiceModel = new MessageServiceModel();
        messageServiceModel.setContent(messageCreateBindingModel.getContent());
        messageServiceModel.setFromUser(fromUser);
        messageServiceModel.setToUser(toUser);
        messageServiceModel.setRelationship(relationship);
        messageServiceModel.setTime(LocalDateTime.now());
        Message message = this.modelMapper.map(messageServiceModel,Message.class);
        Message savedMessage = messageRepository.save(message);
        if (savedMessage != null){
            return this.modelMapper.map(savedMessage,MessageServiceModel.class);
        }
        throw  new CustomException(SERVER_ERROR_MESSAGE);
    }

    @Override
    public List<MessageServiceModel> getAllMessages(String loggedInUsername, String chatUserId) {
        return null;
    }

    @Override
    public List<MessageFriendsViewModel> getAllFriendMessages(String loggedInUsername) {
        return null;
    }
}
