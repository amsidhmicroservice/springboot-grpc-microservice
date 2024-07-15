package com.amsidh.mvc.service;

import com.amsidh.mvc.stock.StockServiceGrpc;
import com.google.protobuf.Empty;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class PriceUpdateSubscriptionInitializer implements CommandLineRunner {

    @GrpcClient("stock-service")
    private StockServiceGrpc.StockServiceStub stockClient;

    @Autowired
    private PriceUpdateObserver listener;

    @Override
    public void run(String... args) throws Exception {
        this.stockClient
                .withWaitForReady()
                .getPriceUpdates(Empty.getDefaultInstance(), listener);
    }
}
