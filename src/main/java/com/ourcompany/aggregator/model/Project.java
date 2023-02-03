package com.ourcompany.aggregator.model;

import java.time.LocalDateTime;

public interface Project {

    long getId();
    String getDescription();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

}
