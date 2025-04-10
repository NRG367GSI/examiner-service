package pro.skyjava.course2.examinerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.skyjava.course2.examinerservice.model.Question;
import pro.skyjava.course2.examinerservice.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final QuestionService javaQuestionService;

    public JavaQuestionController(QuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam("question") String questionText,
                                @RequestParam("answer") String answerText) {
        return javaQuestionService.add(questionText, answerText);
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam("question") String questionText,
                                   @RequestParam("answer") String answerText) {
        Question questionToRemove = new Question(questionText, answerText);
        return javaQuestionService.remove(questionToRemove);
    }

    @GetMapping
    public Collection<Question> getAllQuestions() {
        return javaQuestionService.getAll();
    }
}
