package com.ourcompany.content.dto;

import com.ourcompany.content.model.Project;
import com.ourcompany.content.model.entity.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO implements Project {

    private long id;
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<Long> tagList;

    public ProjectDTO(final ProjectEntity source) {
        this.id = source.getId();
        this.description = source.getDescription();
        this.createdAt = source.getCreatedAt();
        this.updatedAt = source.getUpdatedAt();
        this.tagList = source.getTagList();
    }
}
