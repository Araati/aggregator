package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateDTO {

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "tags")
    private List<Long> tagList;

}
