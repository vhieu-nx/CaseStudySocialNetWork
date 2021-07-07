package kl.socialnetwork.controller;

import kl.socialnetwork.model.AppUser;
import kl.socialnetwork.model.Post;
import kl.socialnetwork.service.post.IPostService;
import kl.socialnetwork.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
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

    @GetMapping("/get-all-post")
    public ResponseEntity<List<Post>> getAllMyPost() {

        AppUser currentUser = userService.getCurrentUser();

        List<Post> postList = postService.findAllByAppUser(currentUser);
        Collections.sort(postList, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                if (o1.getCreatedTime().getTime() > o2.getCreatedTime().getTime()) {
                    return 1;
                } else if (o1.getCreatedTime().getTime() < o2.getCreatedTime().getTime()) {
                    return -1;
                }
                return 0;
            }
        });
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<Post> deletePostById(@PathVariable Long id) {

//        AppUser currentUser = new AppUser();
//        currentUser.setId(1L);
        AppUser currentUser = userService.getCurrentUser();

        //to show the deleted post
        Post p = postService.findById(id);
        //check
        if (currentUser.equals(p.getAppUser())) {
            postService.deleteById(id);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping("/create-new-post")
    public ResponseEntity<Post> createNewPost(@RequestBody Post post) {
        AppUser currentUser = userService.getCurrentUser();
        post.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        post.setAppUser(currentUser);
        //to show latest created post
        Post p = postService.save(post);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PutMapping("/update-post")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        post.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        Post p = postService.save(post);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }



}
