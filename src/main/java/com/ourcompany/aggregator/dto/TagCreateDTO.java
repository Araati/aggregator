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
public class TagCreateDTO {

    @JsonProperty(value = "label", required = true)
    private String label;

    @JsonProperty(value = "type", required = true)
    private String type;

}
