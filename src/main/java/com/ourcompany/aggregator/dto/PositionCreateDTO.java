package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Valid
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PositionCreateDTO {

    @JsonProperty(value = "projectId", required = true)
    private long projectId;

    @JsonProperty(value = "position", required = true)
    private String position;

    @JsonProperty(value = "skills", required = true)
    private String skills;

}
