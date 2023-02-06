package com.ourcompany.aggregator.dto;

import com.ourcompany.aggregator.model.Tag;
import com.ourcompany.aggregator.model.entity.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO implements Tag {
    private long id;
    private String label;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TagDTO(final TagEntity source) {
        this.id = source.getId();
        this.label = source.getLabel();
        this.type = source.getType();
        this.createdAt = source.getCreatedAt();
        this.updatedAt = source.getUpdatedAt();
    }
}
