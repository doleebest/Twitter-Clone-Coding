package efub.toyproject.twitter.tweet.service;

import efub.toyproject.twitter.account.domain.Account;
import efub.toyproject.twitter.account.service.AccountService;
import efub.toyproject.twitter.tweet.domain.Tweet;
import efub.toyproject.twitter.tweet.dto.TweetRequestDto;
import efub.toyproject.twitter.tweet.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final AccountService accountService;

    public Tweet addTweet(TweetRequestDto requestDto) {
        Account writer = accountService.findAccountById(requestDto.getAccountId());

        return tweetRepository.save(
                Tweet.builder()
                        .comment(requestDto.getComment())
                        .writer(writer)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public List<Tweet> findTweetList() {
        return tweetRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Tweet findTweet(Long tweetId) {
        return tweetRepository.findById(tweetId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public void removeTweet(Long tweetId, Long accountId) {
        Tweet tweet = tweetRepository.findByTweetIdAndWriter_AccountId(tweetId, accountId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        tweetRepository.delete(tweet);
    }

    public List<Tweet> findTweetListByWriter(Long accountId) {
        Account account = accountService.findAccountById(accountId);
        return tweetRepository.findAllByWriter(account);
    }
}
