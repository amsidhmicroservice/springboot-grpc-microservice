package com.amsidh.mvc.util;

import com.amsidh.mvc.entity.Portfolio;
import com.amsidh.mvc.entity.User;
import com.amsidh.mvc.user.Holding;
import com.amsidh.mvc.user.StockTradeRequest;
import com.amsidh.mvc.user.StockTradeResponse;
import com.amsidh.mvc.user.UserInformation;

import java.util.List;

public class EntityMessageMapper {
    public static UserInformation toUserInformation(User user, List<Portfolio> portfolios) {
        var holdings = portfolios.stream()
                .map(i -> Holding.newBuilder().setTicker(i.getTicker()).setQuantity(i.getQuantity()).build())
                .toList();
        return UserInformation.newBuilder()
                .setUserId(user.getId())
                .setName(user.getName())
                .setBalance(user.getBalance())
                .addAllHoldings(holdings)
                .build();
    }

    public static Portfolio toPortfolioItem(StockTradeRequest request) {
        var item = new Portfolio();
        item.setUserId(request.getUserId());
        item.setTicker(request.getTicker());
        item.setQuantity(request.getQuantity());
        return item;
    }

    public static StockTradeResponse toStockTradeResponse(StockTradeRequest request, int balance) {
        return StockTradeResponse.newBuilder()
                .setUserId(request.getUserId())
                .setPrice(request.getPrice())
                .setTicker(request.getTicker())
                .setQuantity(request.getQuantity())
                .setAction(request.getAction())
                .setTotalPrice(request.getPrice() * request.getQuantity())
                .setBalance(balance)
                .build();
    }
}
