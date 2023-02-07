package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PositionCreateDTO {

    @JsonProperty(value = "projectId", required = true)
    private long projectId;

    @NotBlank(message = "position should not be blank")
    @JsonProperty(value = "position", required = true)
    private String position;

    @NotBlank(message = "skills should not be blank")
    @JsonProperty(value = "skills", required = true)
    private String skills;

}
