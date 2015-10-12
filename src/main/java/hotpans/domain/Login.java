package hotpans.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    // ログインID
    private String loginId;

    // ログインパスワード
    private String loginPassword;
}
