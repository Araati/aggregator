package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class TagUpdateDTO {

    @JsonProperty(value = "label")
    private String label;

    @JsonProperty(value = "type")
    private String type;

    public Optional<String> getLabel() { return Optional.ofNullable(label); }

    public Optional<String> getType() { return Optional.ofNullable(type); }
}
