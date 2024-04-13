package com.estudou.goalsservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.estudou.goalsservice.dto.GoalRequest;
import com.estudou.goalsservice.exception.GoalNotFoundException;
import com.estudou.goalsservice.model.Goal;
import com.estudou.goalsservice.repository.GoalRepository;

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

        verify(goalRepository, times(1)).save(any());
    }

    @Test
    void shouldUpdate() {
        GoalRequest goalRequest = generateGoalRequest();
        String goalId = "1";

        Goal updatedGoal = generateGoal();
        updatedGoal.setTitle("Estudando");
        goalRequest.setTitle("Estudando");

        when(goalRepository.save(any())).thenReturn(updatedGoal);
        when(goalRepository.findById(Long.parseLong(goalId))).thenReturn(generateOptionsGoal());

        Goal goalResponse = goalService.update(goalId, goalRequest);

        Assertions.assertEquals(goalResponse.getText(), "Estudar");
        Assertions.assertEquals(goalResponse.getTitle(), "Estudando");
        Assertions.assertEquals(goalResponse.getId(), 1L);
        Assertions.assertInstanceOf(Goal.class, goalResponse);

        verify(goalRepository, times(1)).save(any());
        verify(goalRepository, times(1)).findById(any());
    }

    @Test
    void shoudlFindAll() {
        List<Goal> goals = Arrays.asList(generateGoal(), generateGoal());
        when(goalRepository.findAll()).thenReturn(goals);

        List<Goal> goalsRes = goalService.findAll();

        Assertions.assertEquals(goals.size(), goalsRes.size());
        Assertions.assertTrue(goalsRes.containsAll(goals));
        Assertions.assertInstanceOf(Goal.class, goalsRes.get(1));

        verify(goalRepository, times(1)).findAll();
    }

    @Test
    void shouldFindById() {
        Long goalId = 1L;

        when(goalRepository.findById(goalId)).thenReturn(generateOptionsGoal());
        Goal goal = goalService.findById(goalId);

        Assertions.assertInstanceOf(Goal.class, goal);
        Assertions.assertEquals(goal.getId(), 1);

        verify(goalRepository, times(1)).findById(any());
    }

    @Test
    void shouldThrowWhenDeletingNoValue() {
        String goalId = "1";
        Long parsedGoalId = 1L;
        when(goalRepository.findById(parsedGoalId)).thenReturn(Optional.empty());

        Assertions.assertThrows(GoalNotFoundException.class, () ->
            goalService.delete(goalId)
        );

        verify(goalRepository, times(1)).findById(any());
    }

	private GoalRequest generateGoalRequest() {
		GoalRequest goalRequest = new GoalRequest();

		goalRequest.setColor("blue");
		goalRequest.setText("Concluir o curso");
		goalRequest.setTitle("Curso");

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

    private Optional<Goal> generateOptionsGoal() {
        return Optional.ofNullable(generateGoal());
    }
}
