package efub.toyproject.twitter.account.service;

import efub.toyproject.twitter.account.domain.Account;
import efub.toyproject.twitter.account.dto.SignUpRequestDto;
import efub.toyproject.twitter.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Long signUp(SignUpRequestDto requestDto) {
        if (existsByUserId(requestDto.getUserId())) {
            throw new IllegalArgumentException("이미 존재하는 UserId입니다. " + requestDto.getUserId());
        }
        Account account = accountRepository.save(requestDto.toEntity());
        return account.getAccountId();
    }

    @Transactional(readOnly = true)
    public boolean existsByUserId(String userId){
        return accountRepository.existsByUserId(userId);
    }

    @Transactional(readOnly = true)
    public Account findAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id 를 가진 Account 를 찾을 수 없습니다. id ="+id));
    }
}
