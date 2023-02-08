package com.ourcompany.content.model.entity;

import com.ourcompany.content.dto.PositionCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@With
@Table(name = "position")
@Entity
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "project_id")
    private long projectId;

    @Column(name = "position")
    private String position;

    @Column(name = "skills")
    private String skills;

    @Column(name = "deleted")
    private boolean deleted;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public PositionEntity(final PositionCreateDTO source) {
        this.projectId = source.getProjectId();
        this.position = source.getPosition();
        this.skills = source.getSkills();
    }
}
