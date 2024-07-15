package com.amsidh.mvc.controller;

import com.amsidh.mvc.service.TradeService;
import com.amsidh.mvc.user.StockTradeRequest;
import com.amsidh.mvc.user.StockTradeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("trade")
public class TradeController {

    private final TradeService tradeService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public StockTradeResponse trade(@RequestBody StockTradeRequest request){
        return this.tradeService.trade(request);
    }

}