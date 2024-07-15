package com.amsidh.mvc.handler;

import com.amsidh.mvc.entity.Portfolio;
import com.amsidh.mvc.entity.User;
import com.amsidh.mvc.exception.UnknownUserException;
import com.amsidh.mvc.repository.PortfolioRepository;
import com.amsidh.mvc.repository.UserRepository;
import com.amsidh.mvc.user.UserInformation;
import com.amsidh.mvc.user.UserInformationRequest;
import com.amsidh.mvc.util.EntityMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserInformationRequestHandler {

    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;

    public UserInformation getUserInformation(UserInformationRequest userInformationRequest) {
        userRepository.findAll().forEach(user -> log.info("User Detail: {}", user));
        final User user = userRepository.findById(userInformationRequest.getUserId()).orElseThrow(() -> new UnknownUserException(userInformationRequest.getUserId()));
        final List<Portfolio> portfolios = portfolioRepository.findAllByUserId(user.getId());
        return EntityMessageMapper.toUserInformation(user, portfolios);
    }
}
