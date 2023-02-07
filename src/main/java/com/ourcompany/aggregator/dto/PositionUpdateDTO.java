package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class PositionUpdateDTO {

    @JsonProperty(value = "position")
    private String position;

    @JsonProperty(value = "skills")
    private String skills;

    public Optional<String> getPosition() { return Optional.ofNullable(position); }

    public Optional<String> getSkills() { return Optional.ofNullable(skills); }

}
