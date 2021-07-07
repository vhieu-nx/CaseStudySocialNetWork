package kl.socialnetwork.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AppUser appUser;

    private String content;
    private Timestamp createdTime;
    private String image;
    @ManyToOne
    private PostStatus status;
    private int postLike;
    private int postDislike;

    public int getPostLike() {
        return postLike;
    }

    public void setPostLike(int postLike) {
        this.postLike = postLike;
    }

    public int getPostDislike() {
        return postDislike;
    }

    public void setPostDislike(int postDislike) {
        this.postDislike = postDislike;
    }


}
