package pro.skyjava.course2.examinerservice.exception;

public class NullQuestionException extends IllegalArgumentException {
    public NullQuestionException(String message) {
        super(message);
    }

    public NullQuestionException() {
        super("Вопрос не может быть null.");
    }
}
