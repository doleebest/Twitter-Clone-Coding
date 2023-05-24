package efub.toyproject.twitter.account.controller;

// url: /accounts/{accountId}/tweets

import efub.toyproject.twitter.account.service.AccountService;
import efub.toyproject.twitter.tweet.domain.Tweet;
import efub.toyproject.twitter.tweet.dto.TweetListResponseDto;
import efub.toyproject.twitter.tweet.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// url: /accounts/{accountId}/tweets
//
@RestController
@RequestMapping("/accounts/{accountId}/tweets")
@RequiredArgsConstructor
public class AccountTweetController {

    // 의존관계 : AccountTweetController -> PostService
    private final TweetService tweetService;
    // 의존관계 : AccountTweetController -> AccountService
    private final AccountService accountService;

    // 특정 유저의 게시글 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TweetListResponseDto readAccountTweets(@PathVariable Long accountId) {
        List<Tweet> tweetList = tweetService.findTweetListByWriter(accountId);
        return TweetListResponseDto.of(tweetList);
    }


}
