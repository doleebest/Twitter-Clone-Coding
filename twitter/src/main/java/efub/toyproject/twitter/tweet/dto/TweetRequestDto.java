package efub.toyproject.twitter.tweet.dto;

import lombok.Getter;

/*
{
    "accountId":"1",
    "comment": "오늘 이펍 세션이 있는 날이다. "
}
 */
@Getter
public class TweetRequestDto {
    private Long accountId;
    private String comment;

}
