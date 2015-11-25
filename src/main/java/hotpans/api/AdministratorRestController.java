package hotpans.api;

import hotpans.domain.Administrator;
import hotpans.service.AdministratorService;
import hotpans.util.HotPansException;
import hotpans.util.HotPansUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/administrators")
public class AdministratorRestController {
    @Autowired
    AdministratorService administratorService;

    // 管理者さん全件取得
    @RequestMapping(method = RequestMethod.GET)
    List<Administrator> getAdministrators(){
        List<Administrator> administrators = administratorService.findAll();
        System.out.println("★管理者さん全件取得");
        return administrators;
    }

    // 管理者さん１件取得
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Administrator getAdministrator(@PathVariable Integer id){
        Administrator administrator = administratorService.findOne(id);
        return administrator;
    }

    // 管理者さん新規登録
    @RequestMapping(method = RequestMethod.POST)
    public Administrator postAdministrator(
            @RequestParam("name") String name,
            @RequestParam("mailAddress") String mailAddress,
            @RequestParam("loginId") String loginId,
            @RequestParam("loginPassword") String loginPassword)
                    throws FileNotFoundException, IOException{

        System.out.println("★管理者さん新規登録2");

        Administrator administrator = new Administrator();
        administrator.setName(name);
        administrator.setMailAddress(mailAddress);
        administrator.setLoginId(loginId);
        administrator.setEncodedLoginPassword(HotPansUtil.getCipherString(loginPassword));  // パスワードを暗号化

        // ToDo: ★ loginIdが既に登録済みの場合、登録できないようにする必要がある。
        List<Administrator> administratorList = administratorService.findAll();
        for(Administrator administratorInDB : administratorList){
            if(administrator.getLoginId().equals(administratorInDB.getLoginId())){
                throw new HotPansException("ログインIDが重複しています。");
            }
        }

        return administratorService.create(administrator);
    }

    // 管理者さん１件更新
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    Administrator putAdministrator(@PathVariable Integer id, @RequestBody Administrator administrator){
        administrator.setId(id);
        return administratorService.update(administrator);
    }

    // 管理者さん１件削除
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAdministrator(@PathVariable Integer id){
        administratorService.delete(id);
    }

}
