package com.estudou.goalsservice.model;

import com.estudou.goalsservice.dto.GoalRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_goal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String text;
    private String color;

    public static Goal factoryGoalRequest(GoalRequest goalRequest) {
        Goal goal = new Goal();

        goal.setColor(goalRequest.getColor());
        goal.setText(goalRequest.getText());
        goal.setTitle(goalRequest.getTitle());

        return goal;
    }
}
