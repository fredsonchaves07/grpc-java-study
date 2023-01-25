package com.fredsonchaves07.application.exception.handler;

import com.fredsonchaves07.application.exception.BaseBusinessException;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ExceptionHandler {

    @GrpcExceptionHandler(BaseBusinessException.class)
    public StatusRuntimeException handleBusinessException(BaseBusinessException exception) {
        return exception
                .getStatusCode()
                .withCause(exception.getCause())
                .withDescription(exception.getErrorMessage())
                .asRuntimeException();
    }
}
