package com.estudou.missionservice.dto;

import com.estudou.missionservice.model.Mission;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissionRequest {
    @NotNull(message = "type not sent")

    @Enumerated(EnumType.STRING)
    private Mission.Type type;

    @NotBlank
    private String name;
    private Integer points;
}
