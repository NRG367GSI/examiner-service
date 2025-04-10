package pro.skyjava.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.exception.*;
import pro.skyjava.course2.examinerservice.model.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String questionText, String answerText) {
        Question newQuestion = new Question(questionText, answerText);
        if (newQuestion.getQuestion() == null || newQuestion.getAnswer() == null) {
            throw new NullQuestionException("Вопрос и ответ не могут быть null.");
        }
        boolean exists = questions.stream()
                .anyMatch(q -> q.getQuestion().equalsIgnoreCase(newQuestion.getQuestion()) &&
                        q.getAnswer().equals(newQuestion.getAnswer()));
        if (exists) {
            throw new DuplicateQuestionException("Такой вопрос уже существует.");
        }
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        if (question == null || question.getQuestion() == null || question.getAnswer() == null) {
            throw new NullQuestionException("Вопрос не может быть null.");
        }
        boolean exists = questions.stream()
                .anyMatch(q -> q.getQuestion().equalsIgnoreCase(question.getQuestion()) &&
                        q.getAnswer().equals(question.getAnswer()));
        if (exists) {
            throw new DuplicateQuestionException("Такой вопрос уже существует.");
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question questionToRemove) {
        if (questionToRemove == null) {
            throw new IllegalArgumentException("Вопрос для удаления не может быть null.");
        }
        if (questions.remove(questionToRemove)) {
            return questionToRemove;
        }
        throw new QuestionNotFoundException("Вопрос не найден.");
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(questions.size());
        Iterator<Question> iterator = questions.iterator();
        Question randomQuestion = null;
        for (int i = 0; i <= randomIndex; i++) {
            randomQuestion = iterator.next();
        }
        return randomQuestion;
    }
}