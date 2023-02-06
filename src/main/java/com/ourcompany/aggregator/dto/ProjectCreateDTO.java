package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Valid
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateDTO {

    @JsonProperty(value = "description", required = true)
    private String description;

    @JsonProperty(value = "tag_list", required = true)
    private List<Long> tagList;

}
