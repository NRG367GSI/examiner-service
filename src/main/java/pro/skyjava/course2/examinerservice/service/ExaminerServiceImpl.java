package pro.skyjava.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.exception.NotEnoughQuestionsException;
import pro.skyjava.course2.examinerservice.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int totalQuestions = questionService.getAll().size();
        if (amount > totalQuestions || amount < 1) {
            throw new NotEnoughQuestionsException(1, "Запрошено " + amount + " вопросов, доступно " + totalQuestions);
        }
        Set<Question> randomQuestions = new HashSet<>();
        while (randomQuestions.size() < amount) {
            randomQuestions.add(questionService.getRandomQuestion());
        }
        return randomQuestions;
    }
}
