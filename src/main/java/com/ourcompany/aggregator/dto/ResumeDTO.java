package com.ourcompany.aggregator.dto;

import com.ourcompany.aggregator.model.Resume;
import com.ourcompany.aggregator.model.entity.ResumeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDTO implements Resume {
    private long id;
    private String description;
    private List<Long> tagList;
    private boolean free;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ResumeDTO(final ResumeEntity source) {
        this.id = source.getId();
        this.description = source.getDescription();
        this.tagList = source.getTagList();
        this.free = source.isFree();
        this.createdAt = source.getCreatedAt();
        this.updatedAt = source.getUpdatedAt();
    }
}
