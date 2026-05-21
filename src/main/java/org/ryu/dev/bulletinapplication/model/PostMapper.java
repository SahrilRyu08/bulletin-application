package org.ryu.dev.bulletinapplication.model;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> findAll();
    Post findById(Long id);
    void insert(Post post);
    void update(Post post);
    void incrementViewCount(Long id);

    void softDelete(Long id);
    String findPasswordById(Long id);

}
