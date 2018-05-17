package applicationfolder.utils;

public class MyOwnRuntimeException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -7113698532297260092L;

    public MyOwnRuntimeException() {
    }

    public MyOwnRuntimeException(Exception e) {
    }

    public MyOwnRuntimeException(String message) {
        super(message);
    }

}
