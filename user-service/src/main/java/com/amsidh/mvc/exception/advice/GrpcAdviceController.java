package com.amsidh.mvc.exception.advice;

import com.amsidh.mvc.exception.InsufficientBalanceException;
import com.amsidh.mvc.exception.InsufficientSharesException;
import com.amsidh.mvc.exception.UnknownTickerException;
import com.amsidh.mvc.exception.UnknownUserException;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcAdviceController {

    @GrpcExceptionHandler({UnknownTickerException.class})
    public Status handleInvalidArguments(UnknownTickerException unknownTickerException) {
        return Status.INVALID_ARGUMENT.withDescription(unknownTickerException.getMessage());
    }

    @GrpcExceptionHandler({UnknownUserException.class})
    public Status handleUnknownUserException(UnknownUserException unknownUserException) {
        return Status.NOT_FOUND.withDescription(unknownUserException.getMessage());
    }

    @GrpcExceptionHandler({InsufficientBalanceException.class, InsufficientSharesException.class})
    public Status handlePreconditionFailures(Exception exception) {
        return Status.FAILED_PRECONDITION.withDescription(exception.getMessage());
    }
}
