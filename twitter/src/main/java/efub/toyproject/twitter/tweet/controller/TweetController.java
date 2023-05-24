package efub.toyproject.twitter.tweet.controller;

import efub.toyproject.twitter.tweet.domain.Tweet;
import efub.toyproject.twitter.tweet.dto.TweetResponseDto;
import efub.toyproject.twitter.tweet.dto.TweetRequestDto;
import efub.toyproject.twitter.tweet.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tweets") // 경로 맵핑
@RequiredArgsConstructor //lombok
public class TweetController {
    private final TweetService tweetService;
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TweetResponseDto tweetAdd(@RequestBody TweetRequestDto requestDto){
        Tweet tweet = tweetService.addTweet(requestDto);
        return TweetResponseDto.from(tweet);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<TweetResponseDto> tweetListFind() {
        List<Tweet> tweetList = tweetService.findTweetList();
        return tweetList.stream().map(TweetResponseDto::from).collect(Collectors.toList());
    }

    @GetMapping("/{tweetId}")
    @ResponseStatus(value = HttpStatus.OK)
    public TweetResponseDto tweetFind(@PathVariable Long tweetId) {
        Tweet tweet = tweetService.findTweet(tweetId);
        return TweetResponseDto.from(tweet);
    }

    @DeleteMapping("/{tweetId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String tweetRemove(@PathVariable Long tweetId, @RequestParam Long accountId){
        tweetService.removeTweet(tweetId, accountId);
        return "성공적으로 삭제되었습니다.";
    }
}