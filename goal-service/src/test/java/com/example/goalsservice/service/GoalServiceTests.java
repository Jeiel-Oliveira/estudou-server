package com.example.goalsservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.goalsservice.dto.GoalRequest;
import com.example.goalsservice.exception.GoalNotFoundException;
import com.example.goalsservice.model.Goal;
import com.example.goalsservice.repository.GoalRepository;

@ExtendWith(MockitoExtension.class)
public class GoalServiceTests {

    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private GoalService goalService;

    @Test
    void shouldCreate() {
        GoalRequest goalRequest = generateGoalRequest();
        when(goalRepository.save(any())).thenReturn(generateGoal());

        Goal goal = goalService.create(goalRequest);

        Assertions.assertEquals(goal.getText(), "Estudar");
        Assertions.assertEquals(goal.getTitle(), "Estudar");
        Assertions.assertInstanceOf(Goal.class, goal);
    }

    @Test
    void shoudlFindAll() {
        List<Goal> goals = Arrays.asList(generateGoal(), generateGoal());
        when(goalRepository.findAll()).thenReturn(goals);

        List<Goal> goalsRes = goalService.findAll();

        Assertions.assertEquals(goals.size(), goalsRes.size());
        Assertions.assertTrue(goalsRes.containsAll(goals));
        Assertions.assertInstanceOf(Goal.class, goalsRes.get(1));
    }

    @Test
    void shouldThrowWhenDeletingNoValue() {
        String goalId = "1";
        Long parsedGoalId = 1L;
        when(goalRepository.findById(parsedGoalId)).thenReturn(Optional.empty());

        Assertions.assertThrows(GoalNotFoundException.class, () ->
            goalService.delete(goalId)
        );
    }

	private GoalRequest generateGoalRequest() {
		GoalRequest goalRequest = new GoalRequest();

		goalRequest.setColor("blue");
		goalRequest.setText("Concluir o curso");
		goalRequest.setTitle("Curso");
		goalRequest.setDayId("1");

		return goalRequest;
	}

    private Goal generateGoal() {
        Goal goal = new Goal();

        goal.setTitle("Estudar");
        goal.setColor("blue");
        goal.setText("Estudar");
        goal.setId(1L);

        return goal;
    }
}
