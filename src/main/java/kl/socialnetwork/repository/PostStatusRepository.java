package kl.socialnetwork.repository;

import kl.socialnetwork.model.PostStatus;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostStatusRepository extends JpaRepository<PostStatus, Long> {

}
