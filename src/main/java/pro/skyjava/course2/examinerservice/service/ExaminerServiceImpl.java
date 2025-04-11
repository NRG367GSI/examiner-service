package pro.skyjava.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.model.Question;
import pro.skyjava.course2.examinerservice.exception.NotEnoughQuestionsException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final Random random = new Random();
    private static final int NOT_ENOUGH_QUESTIONS_ERROR_CODE = 1001; // Код ошибки

    public ExaminerServiceImpl(QuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int totalQuestions = javaQuestionService.getAll().size();
        if (amount > totalQuestions || amount <= 0) {
            throw new NotEnoughQuestionsException(NOT_ENOUGH_QUESTIONS_ERROR_CODE, "Запрошено " + amount + " вопросов, доступно " + totalQuestions);
        }
        return Stream.generate(javaQuestionService::getRandomQuestion)
                .distinct()
                .limit(amount)
                .collect(Collectors.toSet());
    }
}
