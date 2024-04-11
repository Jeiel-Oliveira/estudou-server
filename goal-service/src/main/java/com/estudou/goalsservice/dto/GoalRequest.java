package com.estudou.goalsservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalRequest {
    @NotBlank
    private String title;

    private String text;
    private String color;
}
