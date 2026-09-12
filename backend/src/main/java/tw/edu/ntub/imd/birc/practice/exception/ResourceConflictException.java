package tw.edu.ntub.imd.birc.practice.exception;

import tw.edu.ntub.birc.common.exception.ProjectException;

public class ResourceConflictException extends ProjectException {
    public ResourceConflictException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "Resource - Conflict";
    }
}