package com.joaowudarski.usecase;

import com.br.jvcw.domain.SecureUser;
import com.br.jvcw.usecase.SearchUser;
import com.joaowudarski.gateway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SearchUserImpl implements SearchUser {

    private final UserRepository userRepository;

    @Override
    public Optional<SecureUser> findSecureById(String id) {
        return userRepository.findById(id).map(x -> x);
    }
}
