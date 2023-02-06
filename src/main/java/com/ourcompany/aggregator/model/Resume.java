package com.ourcompany.aggregator.model;

import java.time.LocalDateTime;
import java.util.List;

public interface Resume {
    long getId();
    String getDescription();
    List<Long> getTagList();
    boolean isFree();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
