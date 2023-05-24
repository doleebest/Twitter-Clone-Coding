package efub.toyproject.twitter.tweet.dto;

import efub.toyproject.twitter.tweet.domain.Tweet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class TweetListResponseDto {

    private List<TweetResponseDto> tweets;
    private Integer count;

    public static TweetListResponseDto of(List<Tweet> tweetList) {
        return TweetListResponseDto.builder()
                .tweets(tweetList.stream().map(TweetResponseDto::from).collect(Collectors.toList()))
                .count(tweetList.size())
                .build();
    }
}
