package hotpans.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginWithGoogleAccount {
    // メールアドレス
    private String mailAddress;
}
