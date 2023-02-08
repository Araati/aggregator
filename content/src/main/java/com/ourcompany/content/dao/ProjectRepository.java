package com.ourcompany.content.dao;

import exception.ResourceNotFoundException;
import com.ourcompany.content.model.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByIdAndDeletedIsFalse(final long id);

    default ProjectEntity mustFindById(final long id)   {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", id));
    }

    default ProjectEntity mustFindByIdAndDeletedIsFalse(final long id)   {
        return findByIdAndDeletedIsFalse(id).orElseThrow(() -> new ResourceNotFoundException("Project", id));
    }

    List<ProjectEntity> findAllByDeletedIsFalse();

}
