package com.ourcompany.content.dao;

import com.ourcompany.content.exception.ResourceNotFoundException;
import com.ourcompany.content.model.entity.ResumeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ResumeRepository extends CrudRepository<ResumeEntity, Long> {

    Optional<ResumeEntity> findByIdAndDeletedIsFalse(final long id);

    default ResumeEntity mustFindById(final long id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Resume", id));
    }

    ResumeEntity findByDescription(final String description);

    default ResumeEntity mustFindByIdAndDeletedIsFalse(final long id) {
        return findByIdAndDeletedIsFalse(id).orElseThrow(() -> new ResourceNotFoundException("Resume", id));
    }
}
