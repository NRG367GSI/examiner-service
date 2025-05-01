package pro.skyjava.course2.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.skyjava.course2.examinerservice.exception.NotEnoughQuestionsException;
import pro.skyjava.course2.examinerservice.model.Question;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getCorrectUniqueQuestions() {
        Question q1 = new Question("Q1", "A1");
        Question q2 = new Question("Q2", "A2");
        Question q3 = new Question("Q3", "A3");
        Set<Question> availableQuestions = Set.of(q1, q2, q3);
        int amount = 2;

        when(questionService.getAll()).thenReturn(availableQuestions);
        when(questionService.getRandomQuestion())
                .thenReturn(q1)
                .thenReturn(q2)
                .thenReturn(q1) // Повторение для проверки уникальности
                .thenReturn(q3);

        Collection<Question> result = examinerService.getQuestions(amount);

        assertEquals(amount, result.size());
        assertEquals(result.stream().distinct().count(), result.size());
        assertTrue(availableQuestions.containsAll(result));
        verify(questionService, atLeast(1)).getRandomQuestion();
    }

    @Test
    void throwsNotEnoughQuestions() {
        Question q1 = new Question("Q1", "A1");
        Question q2 = new Question("Q2", "A2");
        Set<Question> availableQuestions = Set.of(q1, q2);
        int amount = 3;

        when(questionService.getAll()).thenReturn(availableQuestions);

        assertThrows(NotEnoughQuestionsException.class, () -> examinerService.getQuestions(amount));
        verify(questionService, times(1)).getAll();
        verify(questionService, never()).getRandomQuestion();
    }

    @Test
    void throwsExceptionForZeroOrNegativeAmount() {
        Question q1 = new Question("Q1", "A1");
        when(questionService.getAll()).thenReturn(Set.of(q1));

        assertThrows(NotEnoughQuestionsException.class, () -> examinerService.getQuestions(0));
        assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(-1));
        verify(questionService, times(2)).getAll();
        verify(questionService, never()).getRandomQuestion();
    }
}