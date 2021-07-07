package kl.socialnetwork.repository;

import kl.socialnetwork.model.AppUser;
import kl.socialnetwork.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findAllByAppUser(AppUser user);

    @Query(value = "select * from Post where post.app_user_id = ?1 and Post.content like ?2 order by post.created_time DESC; ", nativeQuery = true )
    List<Post> findPostByContent(Long id, String content);
}
