package kl.socialnetwork.service.post;

import kl.socialnetwork.model.AppUser;
import kl.socialnetwork.model.Post;
import kl.socialnetwork.service.IService;

import java.util.List;

public interface IPostService extends IService<Post> {
    List<Post> findAllByAppUser(AppUser user);
    List<Post> findPostByContent(Long id, String content);
}
