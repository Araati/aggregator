package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class ProjectUpdateDTO {

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "tags")
    private List<Long> tagList;

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<List<Long>> getTagList() {
        return Optional.ofNullable(tagList);
    }

}
