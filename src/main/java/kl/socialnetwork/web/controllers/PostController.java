package kl.socialnetwork.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kl.socialnetwork.domain.models.bindingModels.post.PostCreateBindingModel;
import kl.socialnetwork.services.PostService;
import kl.socialnetwork.utils.responseHandler.exceptions.CustomException;
import kl.socialnetwork.utils.responseHandler.successResponse.SuccessResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static kl.socialnetwork.utils.constants.ResponseMessageConstants.SERVER_ERROR_MESSAGE;
import static kl.socialnetwork.utils.constants.ResponseMessageConstants.SUCCESSFUL_CREATE_POST_MESSAGE;

@RestController()
@RequestMapping(value = "/post")
public class PostController {
    private final PostService postService;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public PostController(PostService postService, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createPost(@RequestBody @Valid PostCreateBindingModel postCreateBindingModel, Authentication principal) throws Exception {
        boolean post = this.postService.createPost(postCreateBindingModel);
        if (post) {
            SuccessResponse successResponse = new SuccessResponse(LocalDateTime.now(), SUCCESSFUL_CREATE_POST_MESSAGE, " ", true);
            return new ResponseEntity<>(this.objectMapper.writeValueAsString(successResponse), HttpStatus.OK);
        }

        throw new CustomException(SERVER_ERROR_MESSAGE);
    }

}
