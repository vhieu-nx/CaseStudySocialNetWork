package kl.socialnetwork.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kl.socialnetwork.domain.models.bindingModels.user.UserRegisterBindingModel;
import kl.socialnetwork.domain.models.serviceModels.UserServiceModel;
import kl.socialnetwork.domain.models.viewModels.user.UserCreateViewModel;
import kl.socialnetwork.services.UserService;
import kl.socialnetwork.utils.responseHandler.exceptions.BadRequestException;
import kl.socialnetwork.utils.responseHandler.successResponse.SuccessResponse;
import kl.socialnetwork.validations.serviceValidation.services.UserValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static kl.socialnetwork.utils.constants.ResponseMessageConstants.PASSWORDS_MISMATCH_ERROR_MESSAGE;
import static kl.socialnetwork.utils.constants.ResponseMessageConstants.SUCCESSFUL_REGISTER_MESSAGE;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private static  final String SERVER_ERROR_MESSAGE = "Server Error";
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final UserValidationService userValidationService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, ObjectMapper objectMapper, UserValidationService userValidationService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.userValidationService = userValidationService;
    }
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(@RequestBody @Valid UserRegisterBindingModel userRegisterBindingModel) throws Exception {

        if (!userValidationService.isValid(userRegisterBindingModel.getPassword(), userRegisterBindingModel.getConfirmPassword())) {
            throw new BadRequestException(PASSWORDS_MISMATCH_ERROR_MESSAGE);
        }

        if (!userValidationService.isValid(userRegisterBindingModel)) {
            throw new Exception(SERVER_ERROR_MESSAGE);
        }

        UserServiceModel user = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        UserCreateViewModel savedUser = this.userService.createUser(user);

        SuccessResponse successResponse = successResponseBuilder(LocalDateTime.now(), SUCCESSFUL_REGISTER_MESSAGE, savedUser, true);

        return new ResponseEntity<>(this.objectMapper.writeValueAsString(successResponse), HttpStatus.OK);
    }
    private SuccessResponse successResponseBuilder(LocalDateTime timestamp, String message, Object payload, boolean success) {
        return new SuccessResponse(timestamp, message, payload, success);
    }

}
