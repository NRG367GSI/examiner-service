package pro.skyjava.course2.examinerservice.service;

import pro.skyjava.course2.examinerservice.model.Question;

import java.util.Collection;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();
    Question getRandomQuestion();
}
