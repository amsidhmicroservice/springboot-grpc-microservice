package com.amsidh.mvc.config;

import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class ServerConfig {

    @Bean
    public GrpcServerConfigurer serverConfigurer() {
        return serverBuilder -> serverBuilder.executor(Executors.newVirtualThreadPerTaskExecutor());
    }
}
