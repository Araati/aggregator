package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PositionCreateDTO {

    @JsonProperty(value = "projectId")
    private long projectId;

    @JsonProperty(value = "position")
    private String position;

    @JsonProperty(value = "skills")
    private String skills;

}
