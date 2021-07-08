package kl.socialnetwork.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kl.socialnetwork.domain.models.bindingModels.comment.CommentCreateBindingModel;
import kl.socialnetwork.services.CommentService;
import kl.socialnetwork.utils.responseHandler.exceptions.CustomException;
import kl.socialnetwork.utils.responseHandler.successResponse.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static kl.socialnetwork.utils.constants.ResponseMessageConstants.SERVER_ERROR_MESSAGE;
import static kl.socialnetwork.utils.constants.ResponseMessageConstants.SUCCESSFUL_CREATE_COMMENT_MESSAGE;

@RestController()
@RequestMapping(value = "/comment")
public class CommentController {
    private final CommentService commentService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CommentController(CommentService commentService, ObjectMapper objectMapper) {
        this.commentService = commentService;
        this.objectMapper = objectMapper;
    }
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createComment(@RequestBody @Valid CommentCreateBindingModel commentCreateBindingModel) throws Exception {
        boolean comment = this.commentService.createComment(commentCreateBindingModel);
        if (comment) {
            SuccessResponse successResponse = new SuccessResponse(LocalDateTime.now(), SUCCESSFUL_CREATE_COMMENT_MESSAGE, "", true);
            return new ResponseEntity<>(this.objectMapper.writeValueAsString(successResponse), HttpStatus.OK);
        }
        throw new CustomException(SERVER_ERROR_MESSAGE);
    }

}
