package com.ourcompany.content.model.entity;

import com.ourcompany.content.dto.ResumeCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@With
@Table(name = "resume")
@Entity
public class ResumeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @Column(name = "tag_list")
    private List<Long> tagList;

    @Column(name = "free")
    private boolean free;

    @Column(name = "deleted")
    private boolean deleted;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ResumeEntity(final ResumeCreateDTO source) {
        this.description = source.getDescription();
        this.tagList = source.getTagList();
        this.free = source.isFree();
    }
}
