package com.amsidh.mvc.service;

import com.amsidh.mvc.dto.PriceUpdateDto;
import com.amsidh.mvc.stock.PriceUpdate;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class PriceUpdateObserver implements StreamObserver<PriceUpdate> {

    private final Set<SseEmitter> emitters = Collections.synchronizedSet(new HashSet<>());
    private final long sseTimeout;

    public PriceUpdateObserver(@Value("${sse.timeout:300000}") long sseTimeout) {
        this.sseTimeout = sseTimeout;
    }

    public SseEmitter createEmitter() {
        var emitter = new SseEmitter(this.sseTimeout);
        this.emitters.add(emitter);
        emitter.onTimeout(() -> this.emitters.remove(emitter));
        emitter.onError(ex -> this.emitters.remove(emitter));
        return emitter;
    }

    @Override
    public void onNext(PriceUpdate priceUpdate) {
        var dto = new PriceUpdateDto(priceUpdate.getTicker().toString(), priceUpdate.getPrice());
        this.emitters.removeIf(e -> !this.send(e, dto));
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("streaming error", throwable);
        this.emitters.forEach(e -> e.completeWithError(throwable));
        this.emitters.clear();
    }

    @Override
    public void onCompleted() {
        this.emitters.forEach(ResponseBodyEmitter::complete);
        this.emitters.clear();
    }

    private boolean send(SseEmitter emitter, Object o) {
        try {
            emitter.send(o);
            return true;
        } catch (IOException e) {
            log.warn("sse error {}", e.getMessage());
            return false;
        }
    }

}
