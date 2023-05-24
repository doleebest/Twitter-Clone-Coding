package efub.toyproject.twitter.account.controller;

import efub.toyproject.twitter.account.domain.Account;
import efub.toyproject.twitter.account.dto.AccountResponseDto;
import efub.toyproject.twitter.account.dto.SignUpRequestDto;
import efub.toyproject.twitter.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping // 계정 생성
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto){
        Long id = accountService.signUp(requestDto);
        Account findAccount = accountService.findAccountById(id);
        return AccountResponseDto.from(findAccount);
    }

    @GetMapping("/{accountId}") // 계정 조회
    @ResponseStatus(value = HttpStatus.OK)
    public AccountResponseDto getAccount(@PathVariable Long accountId)
    {
        Account findAccount = accountService.findAccountById(accountId);
        return AccountResponseDto.from(findAccount);
    }


}
