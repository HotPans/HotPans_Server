package hotpans.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bakery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bakery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 店名
    @Column(nullable = true)
    private String name;

    // メールアドレス
    @Column(nullable = false)
    private String mailAddress;

    // 住所
    @Column(nullable = true)
    private String address;

    // 電話番号1
    @Column(nullable = true)
    private String phoneNumber1;

    // 電話番号2
    @Column(nullable = true)
    private String phoneNumber2;

    // 紹介文
    @Column(nullable = true)
    private String introduction;

    // 画像
    @Lob
    @Column(nullable = true)
    private byte[] image;

    // 画像エンコーディング
    @Column(nullable = true)
    private String imageEncoding;
}
