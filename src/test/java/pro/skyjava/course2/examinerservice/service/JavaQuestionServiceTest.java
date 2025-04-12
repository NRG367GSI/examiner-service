package pro.skyjava.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.skyjava.course2.examinerservice.exception.DuplicateQuestionException;
import pro.skyjava.course2.examinerservice.exception.QuestionNotFoundException;
import pro.skyjava.course2.examinerservice.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void addValidQuestionSucceeds() {
        Question q1 = javaQuestionService.add("Q1", "A1");
        assertEquals("Q1", q1.getQuestion());
        assertEquals("A1", q1.getAnswer());
        assertTrue(javaQuestionService.getAll().contains(q1));
    }

    @Test
    void addDuplicateQuestionThrowsException() {
        javaQuestionService.add("Q1", "A1");
        assertThrows(DuplicateQuestionException.class, () -> javaQuestionService.add("Q1", "A1"));
    }

    @Test
    void addQuestionWithIdenticalTextAndAnswerThrowsException() {
        // Проверка для String
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.add("Same", "Same"));
        // Проверка для Question
        Question invalidQuestion = new Question("Same", "Same");
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.add(invalidQuestion));
    }

    @Test
    void removeQuestion() {
        Question question = javaQuestionService.add("Q1", "A1");
        Question removed = javaQuestionService.remove(question);
        assertEquals(question, removed);
        assertFalse(javaQuestionService.getAll().contains(question));
    }

    @Test
    void removeNonExistentQuestionThrowsException() {
        Question question = new Question("Q1", "A1");
        assertThrows(QuestionNotFoundException.class, () -> javaQuestionService.remove(question));
    }

    @Test
    void getAllQuestions() {
        Question q1 = javaQuestionService.add("Q1", "A1");
        Question q2 = javaQuestionService.add("Q2", "A2");
        Collection<Question> allQuestions = javaQuestionService.getAll();
        assertEquals(2, allQuestions.size());
        assertTrue(allQuestions.containsAll(Set.of(q1, q2)));
    }

    @Test
    void getRandomQuestion() {
        Question q1 = javaQuestionService.add("Q1", "A1");
        Question q2 = javaQuestionService.add("Q2", "A2");
        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertTrue(randomQuestion.equals(q1) || randomQuestion.equals(q2));
    }

    @Test
    void getRandomQuestionFromEmptySetReturnsNull() {
        assertNull(javaQuestionService.getRandomQuestion());
    }
}