package com.ourcompany.aggregator.dto;

import com.ourcompany.aggregator.model.Position;
import com.ourcompany.aggregator.model.entity.PositionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO implements Position {
    private long id;
    private String position;

    private String skills;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public PositionDTO(final PositionEntity source) {
        this.id = source.getId();
        this.position = source.getPosition();
        this.skills = source.getSkills();
        this.createdAt = source.getCreatedAt();
        this.updatedAt = source.getUpdatedAt();
    }
}
