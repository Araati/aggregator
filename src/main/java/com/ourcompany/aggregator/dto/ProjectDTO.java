package com.ourcompany.aggregator.dto;

import com.ourcompany.aggregator.model.Project;
import com.ourcompany.aggregator.model.entity.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO implements Project {

    private long id;
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ProjectDTO(final ProjectEntity source) {
        this.id = source.getId();
        this.description = source.getDescription();
        this.createdAt = source.getCreatedAt();
        this.updatedAt = source.getUpdatedAt();
    }
}
