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
@Table(name = "bread")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // パンの名前
    @Column(nullable = false)
    private String name;

    // パンの価格
    @Column(nullable = false)
    private String price;

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

    // パン屋のID
    @Column(nullable = false)
    private Integer bakeryId;
}
