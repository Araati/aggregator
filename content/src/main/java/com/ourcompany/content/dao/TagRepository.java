package com.ourcompany.content.dao;

import com.ourcompany.content.exception.ResourceNotFoundException;
import com.ourcompany.content.model.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends CrudRepository<TagEntity, Long> {
    List<TagEntity> findAllByDeletedIsFalse();

    Optional<TagEntity> findByIdAndDeletedIsFalse(final long id);

    default TagEntity mustFindById(final long id)   {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", id));
    }

    default TagEntity mustFindByIdAndDeletedIsFalse(final long id)   {
        return findByIdAndDeletedIsFalse(id).orElseThrow(() -> new ResourceNotFoundException("Tag", id));
    }
}
