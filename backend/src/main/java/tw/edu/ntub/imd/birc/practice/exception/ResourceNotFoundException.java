package tw.edu.ntub.imd.birc.practice.exception;

import tw.edu.ntub.birc.common.exception.ProjectException;

public class ResourceNotFoundException extends ProjectException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "Resource - NotFound";
    }
}