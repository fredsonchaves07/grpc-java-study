package com.fredsonchaves07.domain.exception;

import io.grpc.Status;

public class NotFoundException extends BaseBusinessException{

    private static final String ERROR_MESSAGE = "Produto com id %s não encontrado";

    private final Long id;

    public NotFoundException(Long id) {
        super(String.format(ERROR_MESSAGE, id));
        this.id = id;
    }

    @Override
    public Status getStatusCode() {
        return Status.NOT_FOUND;
    }

    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, id);
    }
}
