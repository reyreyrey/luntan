package com.android.mj.exception;

/**
 * author: Rea.X
 * date: 2017/10/25.
 */

public class LibraryException extends RuntimeException{

    public LibraryException() {
    }

    public LibraryException(String message) {
        super(message);
    }

    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibraryException(Throwable cause) {
        super(cause);
    }
}
