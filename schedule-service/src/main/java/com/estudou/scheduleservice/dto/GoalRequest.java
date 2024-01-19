package com.estudou.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalRequest {
    private String dayId;
    private String title;
    private String text;
    private String color;
}
