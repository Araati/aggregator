package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TagCreateDTO {

    @NotBlank(message = "label should not be blank")
    @JsonProperty(value = "label", required = true)
    private String label;

    @NotBlank(message = "type should not be blank")
    @JsonProperty(value = "type", required = true)
    private String type;

}
