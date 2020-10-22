package Exception;

public class IplAnalyserException extends RuntimeException {
    public enum ExceptionType {
    	CRICKET_FILE_PROBLEM,CRICKET_DATA_NOT_FOUND;
    }

    public ExceptionType type;

    public IplAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
/*----*/
/*****/