package org.ryu.dev.bulletinapplication.service;

import jdk.jfr.StackTrace;
import lombok.RequiredArgsConstructor;
import org.ryu.dev.bulletinapplication.model.Post;
import org.ryu.dev.bulletinapplication.model.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    @Transactional(readOnly = true)
    public List<Post> getAll() {
        return postMapper.findAll();
    }

    public Post getById(Long id) {
        Post post = postMapper.findById(id);
        if (post == null) throw new IllegalArgumentException("Post not found");
        postMapper.incrementViewCount(id);
        return postMapper.findById(id);
    }

    public void create(Post post) {
        validatePost(post);
        postMapper.insert(post);
    }

    public void update(Long id, Post post, String inputPassword) {
        checkPassword(id, inputPassword);
        validatePost(post);
        post.setId(id);
        postMapper.update(post);
    }

    public void delete(Long id, String inputPassword) {
        checkPassword(id, inputPassword);
        postMapper.softDelete(id);
    }

    private void checkPassword(Long id, String inputPassword) {
        String stored = postMapper.findPasswordById(id);
        if (!stored.equals(inputPassword)) {
            throw new IllegalArgumentException("Password not match");
        }
    }

    private void validatePost(Post post) {
        if (post.getTitle() == null || post.getTitle().isBlank()) {
            throw new IllegalArgumentException("tittle cannot be null");
        }
        if (post.getAuthor() == null || post.getAuthor().isBlank()) {
            throw new IllegalArgumentException("author cannot be null");
        }
    }

}
