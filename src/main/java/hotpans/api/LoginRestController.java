package hotpans.api;

import hotpans.domain.Administrator;
import hotpans.domain.Bakery;
import hotpans.domain.Customer;
import hotpans.domain.Login;
import hotpans.service.AdministratorService;
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

    @Autowired
    AdministratorService administratorService;

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

    // パン屋のお客さんのログイン
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    Customer loginCustomer(@RequestBody Login login){
        System.out.println("★LoginRestController#loginCustomer");
        System.out.println("★LoginId : " + login.getLoginId());
        System.out.println("★LoginPassword : " + login.getLoginPassword());

        List<Customer> customerList = customerService.findAll();

        //System.out.println(login.getLoginId());

        for(Customer customer : customerList){
            // ログインIDが存在するかをチェック
            if(customer.getLoginId() != null && customer.getLoginId().equals(login.getLoginId())){
                String encodedPassword = HotPansUtil.getCipherString(login.getLoginPassword());
                // ログインIDが存在した場合、暗号化パスワードが一致するかチェック
                if(customer.getEncodedLoginPassword() != null &&
                        customer.getEncodedLoginPassword().equals(encodedPassword)){
                    // ログイン認証成功

                    // 認証トークンを生成する。
                    // ここでは、ログインIDとログインパスワードと現在日時を連結した文字列を
                    // 不可逆暗号化した文字列をトークンとする。
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String time = sdf.format(date);
                    System.out.println("★time: " + time);
                    String token = HotPansUtil.getCipherString(customer.getId() + customer.getEncodedLoginPassword() + time);
                    System.out.println("★Token: " + token);
                    customer.setTokenForCertification(token);

                    return customerService.update(customer);
                }
            }
        }

        // ログイン認証失敗
        Customer customer = new Customer();
        customer.setTokenForCertification("NG");
        return customer;
    }

    // パン屋のお客さんのログイン状態を取得
    @RequestMapping(value = "/customer/isLogined", method = RequestMethod.POST)
    boolean isLogined(@RequestBody Customer requestCustomer){
        System.out.println("★LoginRestController#isLogined");
        System.out.println("id=" + requestCustomer.getId());
        System.out.println("token = " + requestCustomer.getTokenForCertification());

        System.out.println("requestCustomer=" + requestCustomer);

        if(requestCustomer.getId() == null){
            System.out.println("未ログイン");
            return false;
        }

        Customer customer = customerService.findOne(requestCustomer.getId());

        if(requestCustomer.getTokenForCertification() == null || customer == null){
            System.out.println("未ログイン");
            return false;
        }

        if(requestCustomer.getTokenForCertification().equals(customer.getTokenForCertification())){
            System.out.println("ログイン済み");
            return true;
        }else{
            System.out.println("未ログイン");
            return false;
        }
    }

    // 管理者さんのログイン
    @RequestMapping(value = "/administrator", method = RequestMethod.POST)
    Administrator loginAdministrator(@RequestBody Login login){
        System.out.println("★LoginRestController#loginAdministrator");
        System.out.println("★LoginId : " + login.getLoginId());
        System.out.println("★LoginPassword : " + login.getLoginPassword());

        List<Administrator> administratorList = administratorService.findAll();

        //System.out.println(login.getLoginId());

        for(Administrator administrator : administratorList){
            // ログインIDが存在するかをチェック
            if(administrator.getLoginId() != null && administrator.getLoginId().equals(login.getLoginId())){
                String encodedPassword = HotPansUtil.getCipherString(login.getLoginPassword());
                // ログインIDが存在した場合、暗号化パスワードが一致するかチェック
                if(administrator.getEncodedLoginPassword() != null &&
                        administrator.getEncodedLoginPassword().equals(encodedPassword)){
                    // ログイン認証成功

                    // 認証トークンを生成する。
                    // ここでは、ログインIDとログインパスワードと現在日時を連結した文字列を
                    // 不可逆暗号化した文字列をトークンとする。
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String time = sdf.format(date);
                    System.out.println("★time: " + time);
                    String token = HotPansUtil.getCipherString(administrator.getId() + administrator.getEncodedLoginPassword() + time);
                    System.out.println("★Token: " + token);
                    administrator.setTokenForCertification(token);

                    return administratorService.update(administrator);
                }
            }
        }

        // ログイン認証失敗
        Administrator administrator = new Administrator();
        administrator.setTokenForCertification("NG");
        return administrator;
    }

    // 管理者さんのログイン状態を取得
    @RequestMapping(value = "/administrator/isLogined", method = RequestMethod.POST)
    boolean isLogined(@RequestBody Administrator requestAdministrator){
        System.out.println("★LoginRestController#isLogined");
        System.out.println("id=" + requestAdministrator.getId());
        System.out.println("token = " + requestAdministrator.getTokenForCertification());

        System.out.println("requestAdministrator=" + requestAdministrator);

        if(requestAdministrator.getId() == null){
            System.out.println("未ログイン");
            return false;
        }

        Administrator administrator = administratorService.findOne(requestAdministrator.getId());

        if(requestAdministrator.getTokenForCertification() == null || administrator == null){
            System.out.println("未ログイン");
            return false;
        }

        if(requestAdministrator.getTokenForCertification().equals(administrator.getTokenForCertification())){
            System.out.println("ログイン済み");
            return true;
        }else{
            System.out.println("未ログイン");
            return false;
        }
    }
}
