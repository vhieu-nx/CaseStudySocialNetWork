package kl.socialnetwork.controller;

import kl.socialnetwork.model.AppUser;
import kl.socialnetwork.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("")
    public ResponseEntity<List<AppUser>> findAll() {
        return new ResponseEntity<>(userService.findALl(), HttpStatus.OK);
    }
    @GetMapping("findUserById/{id}")
    public ResponseEntity<AppUser> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/update/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long id, @RequestBody AppUser user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AppUser>> searchUserByName(@RequestParam String name){
        name = "%" + name + "%";
        return new ResponseEntity<>(userService.searchUserByName(name), HttpStatus.OK);
    }

    @PutMapping("/update/{username}/password")
    public ResponseEntity<AppUser> updatePassword(@PathVariable String username, @RequestBody AppUser user){
        AppUser appUser = this.userService.findUserByUsername(username);

        user.setId(appUser.getId());
        user.setUsername(appUser.getUsername());
        user.setPassword(passwordEncoder.encode(appUser.getPassword()));
        user.setAvatar(appUser.getAvatar());
        user.setFirstName(appUser.getFirstName());
        user.setLastName(appUser.getLastName());
        user.setBirthday(appUser.getBirthday());
        user.setGender(appUser.getGender());
        user.setRoles(appUser.getRoles());
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PutMapping("/resetPassword")
    private ResponseEntity<AppUser> resetPassword( @RequestBody AppUser appUser) {
        AppUser currentUser = userService.getCurrentUser();
        AppUser oldAppUser = userService.findUserByUsername(currentUser.getUsername());
        oldAppUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return new ResponseEntity<>(userService.save(oldAppUser), HttpStatus.OK);
    }
}
