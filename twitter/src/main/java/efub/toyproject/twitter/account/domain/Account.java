package efub.toyproject.twitter.account.domain;

import efub.toyproject.twitter.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static efub.toyproject.twitter.account.domain.AccountStatus.REGISTERED;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", updatable = false)
    private Long accountId;

    @Column(nullable = false, length = 60)
    private String username;

    @Column(nullable = false, length = 60)
    private String userId;

    @Column(nullable = false)
    private String encodedPassword;

    /*
    @Column(nullable = false, updatable = false, length = 16)
    private String nickname;
    private String bio;
     */

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Builder
    public Account(String username, String userId, String password) {

        this.username = username;
        this.encodedPassword = password;
        this.userId = userId;
        this.status = REGISTERED;
    }
}