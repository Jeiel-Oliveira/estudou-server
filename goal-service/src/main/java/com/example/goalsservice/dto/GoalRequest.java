package com.example.goalsservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalRequest {
    @NotNull(message = "dayId not sent")
    private String dayId;

    @NotBlank
    private String title;

    private String text;
    private String color;
}
