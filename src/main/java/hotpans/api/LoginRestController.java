package hotpans.api;

import hotpans.domain.Bakery;
import hotpans.domain.Login;
import hotpans.service.BakeryService;
import hotpans.service.CustomerService;
import hotpans.util.HotPansUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginRestController {
    @Autowired
    BakeryService bakeryService;

    @Autowired
    CustomerService customerService;

    // パン屋さんのログイン
    @RequestMapping(value = "/bakery", method = RequestMethod.POST)
    Bakery loginBakery(@RequestBody Login login){
        System.out.println("★LoginRestController#loginBakery");
        List<Bakery> bakeryList = bakeryService.findAll();

        for(Bakery bakery : bakeryList){
            // ログインIDが存在するかをチェック
            if(bakery.getLoginId().equals(login.getLoginId())){
                String encodedPassword = HotPansUtil.getCipherString(login.getLoginPassword());
                // ログインIDが存在した場合、暗号化パスワードが一致するかチェック
                if(bakery.getEncodedLoginPassword().equals(encodedPassword)){
                    // ログイン認証成功

                    // 認証トークンを生成する。
                    // ここでは、ログインIDとログインパスワードと現在日時を連結した文字列を
                    // 不可逆暗号化した文字列をトークンとする。
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String time = sdf.format(date);
                    System.out.println("★time: " + time);
                    String token = HotPansUtil.getCipherString(bakery.getId() + bakery.getEncodedLoginPassword() + time);
                    System.out.println("Token: " + token);
                    bakery.setTokenForCertification(token);

                    return bakeryService.update(bakery);
                }
            }
        }

        // ログイン認証失敗
        Bakery bakery = new Bakery();
        bakery.setTokenForCertification("NG");
        return bakery;
    }

    // パン屋さんのログイン状態を取得
    @RequestMapping(value = "/bakery/isLogined", method = RequestMethod.POST)
    boolean isLogined(@RequestBody Bakery requestBakery){
        System.out.println("★LoginRestController#isLogined");
        System.out.println("id=" + requestBakery.getId());
        System.out.println("token = " + requestBakery.getTokenForCertification());

        System.out.println("requestBakery=" + requestBakery);

        if(requestBakery.getId() == null){
            System.out.println("未ログイン");
            return false;
        }

        Bakery bakery = bakeryService.findOne(requestBakery.getId());

        if(requestBakery.getTokenForCertification() == null || bakery == null){
            System.out.println("未ログイン");
            return false;
        }

        if(requestBakery.getTokenForCertification().equals(bakery.getTokenForCertification())){
            System.out.println("ログイン済み");
            return true;
        }else{
            System.out.println("未ログイン");
            return false;
        }
    }
}
