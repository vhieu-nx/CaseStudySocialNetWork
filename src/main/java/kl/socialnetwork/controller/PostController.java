package kl.socialnetwork.controller;

import kl.socialnetwork.model.AppUser;
import kl.socialnetwork.model.Post;
import kl.socialnetwork.service.post.IPostService;
import kl.socialnetwork.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class PostController {
    @Autowired
    IPostService postService;

    @Autowired
    IUserService userService;

    @GetMapping("/get-post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @GetMapping("/get-all-post-by-user-id/{id}")
    public ResponseEntity<List<Post>> getAllPostByUserId(@PathVariable Long id) {
        AppUser guestUser = userService.findById(id);

        return new ResponseEntity<>(postService.findAllByAppUser(guestUser), HttpStatus.OK);
    }

}
