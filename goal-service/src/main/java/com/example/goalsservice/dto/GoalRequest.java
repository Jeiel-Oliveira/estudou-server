package com.example.goalsservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @Min(100)
    private String text;
    private String color; 
}
