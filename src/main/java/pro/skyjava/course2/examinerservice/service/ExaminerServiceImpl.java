package pro.skyjava.course2.examinerservice.service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.model.Question;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(QuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) throws IllegalArgumentException, IllegalStateException {
        int totalQuestions = javaQuestionService.getAll().size();

        if (amount <= 0) {
            throw new IllegalArgumentException("Запрошено некорректное количество вопросов (должно быть больше 0).");
        }

        if (amount > totalQuestions) {
            throw new IllegalArgumentException("Запрошено вопросов больше, чем есть в хранилище (" + totalQuestions + ").");
        }

        return Stream.generate(javaQuestionService::getRandomQuestion)
                .filter(Objects::nonNull)
                .distinct()
                .limit(amount)
                .collect(Collectors.toSet());
    }
}
