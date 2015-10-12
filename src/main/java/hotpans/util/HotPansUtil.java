package hotpans.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class HotPansUtil {
    /**
     * 認証キーの作成
     * @param target 認証元の文字列
     * @param signatureKey 認証キーを作成する署名キー
     * @param algorithm アルゴリズム <br>
     * AES, ARCFOUR, Blowfish, DES, DESede, HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512 が利用可
     * @return 生成した認証キー
     * @throws NoSuchAlgorithmException 存在しないアルゴリズムの場合throw
     * @throws InvalidKeyException "Message Authentication Code" (MAC) algorithmに適さないKeyを指定するとthrow
     */
    public static String generateAuthenticationKey(String target,
        String signatureKey, String algorithm)
        throws NoSuchAlgorithmException, InvalidKeyException {
      // 秘密鍵の作成
      SecretKey secretKey = new SecretKeySpec(signatureKey.getBytes(),
          algorithm);

      // 認証キーの作成
      Mac mac = Mac.getInstance(algorithm);
      mac.init(secretKey);
      mac.update(target.getBytes());

      // 暗号化
      byte[] encryptedData = mac.doFinal();

      // base64エンコード
      return base64Encode(encryptedData);
    }

    /**
     * Base64エンコードを行う.
     *
     * @param bytes Base64エンコードを施す対象データ
     * @return Base64エンコード処理後の文字列
     */
    public static String base64Encode(byte[] bytes) {
        return (new Base64()).encodeToString(bytes);
    }


    /**
     * 暗号化した文字列を取得する
     * @param target 暗号化したい文字列
     * @return 暗号化した文字列
     */
    public static String getCipherString(String target){
        String cipherString = "hotpanshotpans";
        try{
            cipherString = generateAuthenticationKey(target, "hotpansKey", "HmacSHA256");
            System.out.println("暗号化された文字列：" + cipherString);
        }catch(Exception e){
            e.printStackTrace();
        }
        return cipherString;
    }

    public static void main(String[] args){
        try{
            //String cipherString = generateAuthenticationKey("hotpans", "hotpansKey", "HmacSHA256");
            String cipherString = getCipherString("password2");
            System.out.println("暗号化された文字列：" + cipherString);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
