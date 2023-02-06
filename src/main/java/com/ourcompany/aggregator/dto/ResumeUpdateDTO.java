package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class ResumeUpdateDTO {

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "tag_list")
    private List<Long> tagList;

    @JsonProperty(value = "free")
    private boolean free;

    public Optional<String> getDescription() { return Optional.ofNullable(description); }

    public Optional<List<Long>> getTagList() { return Optional.ofNullable(tagList); }

    public Optional<Boolean> isFree() { return Optional.of(free); }
}
