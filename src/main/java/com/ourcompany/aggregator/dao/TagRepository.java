package com.ourcompany.aggregator.dao;

import com.ourcompany.aggregator.exception.ResourceNotFoundException;
import com.ourcompany.aggregator.model.entity.TagEntity;
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
