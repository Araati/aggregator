package model;

import java.time.LocalDateTime;
import java.util.List;

public interface Project {

    long getId();
    String getDescription();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    List<Long> getTagList();

}
