package pro.skyjava.course2.examinerservice.exception;


public class NotEnoughQuestionsException extends IllegalArgumentException {

    private final int errorCode;

    public NotEnoughQuestionsException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
