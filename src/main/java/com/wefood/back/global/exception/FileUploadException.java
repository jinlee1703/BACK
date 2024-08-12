package com.wefood.back.global.exception;

/**
 * class: FileUploadException.
 *
 * @author JBumLee
 * @version 2024/08/11
 */
public class FileUploadException extends RuntimeException {
    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}