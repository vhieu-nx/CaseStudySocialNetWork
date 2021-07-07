package kl.socialnetwork.service.postStatus;

import kl.socialnetwork.model.PostStatus;
import kl.socialnetwork.repository.PostStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostStatusService implements IPostStatusService {
    @Autowired
    PostStatusRepository postStatusRepository;
    @Override
    public List<PostStatus> findALl() {
        return postStatusRepository.findAll();
    }

    @Override
    public Page<PostStatus> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public PostStatus findById(Long id) {
        return null;
    }

    @Override
    public PostStatus save(PostStatus postStatus) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
    }
}
