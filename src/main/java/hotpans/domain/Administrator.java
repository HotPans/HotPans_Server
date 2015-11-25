package hotpans.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrator")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 名前
    @Column(nullable = false)
    private String name;

    // メールアドレス
    @Column(nullable = false)
    private String mailAddress;

    // ログインID
    @Column(nullable = true)
    private String loginId;

    // 暗号化されたログインパスワード
    @Column(nullable = true)
    private String encodedLoginPassword;

    // 認証用トークン
    @Column(nullable = true)
    private String tokenForCertification;
}
