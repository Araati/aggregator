package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TagCreateDTO {

    @JsonProperty(value = "label")
    private String label;

    @JsonProperty(value = "type")
    private String type;

}
