package pro.skyjava.course2.examinerservice.exception;

public class DuplicateQuestionException extends IllegalArgumentException {
    public DuplicateQuestionException(String message) {
        super(message);
    }

    public DuplicateQuestionException() {
        super("Такой вопрос уже существует.");
    }
}
