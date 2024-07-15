package com.amsidh.mvc.service;

import com.amsidh.mvc.handler.StockSellOrBuyRequestHandler;
import com.amsidh.mvc.handler.UserInformationRequestHandler;
import com.amsidh.mvc.user.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@RequiredArgsConstructor
@GrpcService
@Slf4j
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    private final UserInformationRequestHandler userRequestHandler;
    private final StockSellOrBuyRequestHandler stockSellOrBuyRequestHandler;

    @Override
    public void getUserInformation(UserInformationRequest request, StreamObserver<UserInformation> responseObserver) {
        log.info("user information for id {}", request.getUserId());
        var userInformation = this.userRequestHandler.getUserInformation(request);
        responseObserver.onNext(userInformation);
        responseObserver.onCompleted();
    }

    @Override
    public void tradeStock(StockTradeRequest request, StreamObserver<StockTradeResponse> responseObserver) {
        log.info("{}", request);
        var response = TradeAction.SELL.equals(request.getAction()) ?
                this.stockSellOrBuyRequestHandler.sellStock(request) :
                this.stockSellOrBuyRequestHandler.buyStock(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
