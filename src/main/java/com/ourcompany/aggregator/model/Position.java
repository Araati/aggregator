package com.ourcompany.aggregator.model;

import java.time.LocalDateTime;

public interface Position {
    long getId();
    long getProjectId();
    String getPosition();
    String getSkills();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
