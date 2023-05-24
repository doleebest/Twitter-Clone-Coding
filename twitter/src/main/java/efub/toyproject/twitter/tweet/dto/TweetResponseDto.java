package efub.toyproject.twitter.tweet.dto;

import efub.toyproject.twitter.tweet.domain.Tweet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TweetResponseDto {
    private Long tweetId;
    private String writerName;
    private String comment;
    private LocalDateTime createdDate;

    public TweetResponseDto(Long tweetId, String writerName, String comment, LocalDateTime createdDate) {
        this.tweetId = tweetId;
        this.writerName = writerName;
        this.comment = comment;
        this.createdDate = createdDate;
    }

    public static TweetResponseDto from(Tweet tweet) {
        return new TweetResponseDto(
                tweet.getTweetId(),
                tweet.getWriter().getUsername(),
                tweet.getComment(),
                tweet.getCreatedDate());
    }
}