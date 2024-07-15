package com.amsidh.mvc.repository;

import com.amsidh.mvc.common.Ticker;
import com.amsidh.mvc.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    List<Portfolio> findAllByUserId(Integer userId);

    Optional<Portfolio> findByUserIdAndTicker(Integer userId, Ticker ticker);
}
