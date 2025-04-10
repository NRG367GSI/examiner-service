package pro.skyjava.course2.examinerservice.service;

import pro.skyjava.course2.examinerservice.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}