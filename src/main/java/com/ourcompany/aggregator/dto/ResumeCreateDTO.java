package com.ourcompany.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeCreateDTO {

    @NotBlank(message = "description should not be blank")
    @JsonProperty(value = "description", required = true)
    private String description;

    @JsonProperty(value = "tag_list", required = true)
    private List<Long> tagList;

    @JsonProperty(value = "free", required = true)
    private boolean free;
}
