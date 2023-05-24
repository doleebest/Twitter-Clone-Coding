package efub.toyproject.twitter.tweet.domain;

import efub.toyproject.twitter.account.domain.Account;
import efub.toyproject.twitter.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Tweet extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tweetId; // 카멜식 표기, mysql에서는 tweet_id
    @Column(columnDefinition = "TEXT") // string이 아니라 text임을 명시
    private String comment;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account writer; // tweet의 글 작성자이므로 변수명을 writer로

    @Builder
    public Tweet(Long tweetId, String comment, Account writer) {
        this.tweetId = tweetId;
        this.comment = comment;
        this.writer = writer;
    }
}
