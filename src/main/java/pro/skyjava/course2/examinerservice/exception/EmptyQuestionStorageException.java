package pro.skyjava.course2.examinerservice.exception;

public class EmptyQuestionStorageException extends RuntimeException {
    public EmptyQuestionStorageException() {
        super("Хранилище вопросов пусто.");
    }

    public EmptyQuestionStorageException(String message) {
        super(message);
    }
}
