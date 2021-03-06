package hotpans.api;

import hotpans.domain.Bakery;
import hotpans.service.BakeryService;
import hotpans.util.HotPansException;
import hotpans.util.HotPansUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
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
@RequestMapping("api/bakerys")
public class BakeryRestController {
    @Autowired
    BakeryService bakeryService;

    // パン屋さん全件取得
    @RequestMapping(method = RequestMethod.GET)
    List<Bakery> getBakerys(){
        List<Bakery> bakerys = bakeryService.findAll();
        System.out.println("★パン屋さん全件取得");
        return bakerys;
    }

    // パン屋さん１件取得
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Bakery getBakery(@PathVariable Integer id){
        Bakery bakery = bakeryService.findOne(id);
        return bakery;
    }

    // パン屋さん新規登録
    @RequestMapping(method = RequestMethod.POST)
    public Bakery postBakery(
            @RequestParam("name") String name,
            @RequestParam("mailAddress") String mailAddress,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber1") String phoneNumber1,
            @RequestParam("phoneNumber2") String phoneNumber2,
            @RequestParam("introduction") String introduction,
            @RequestParam("loginId") String loginId,
            @RequestParam("loginPassword") String loginPassword,
            //@RequestParam("image") MultipartFile file){
            @RequestParam("image") String image) throws FileNotFoundException, IOException{

        System.out.println("★パン屋さん新規登録2");

        Bakery bakery = new Bakery();
        bakery.setName(name);
        bakery.setMailAddress(mailAddress);
        bakery.setAddress(address);
        bakery.setPhoneNumber1(phoneNumber1);
        bakery.setPhoneNumber2(phoneNumber2);
        bakery.setIntroduction(introduction);
        bakery.setLoginId(loginId);
        //bakery.setEncodedLoginPassword(loginPassword);
        bakery.setEncodedLoginPassword(HotPansUtil.getCipherString(loginPassword));  // パスワードを暗号化


        //image = "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";

        System.out.println("file : " + image.toString());
        byte[] imageBinary = Base64.decodeBase64(image.substring(image.indexOf("base64,") + 7));
        //byte[] imageBinary = Base64.decodeBase64(image);
        bakery.setImage(imageBinary);

        String imageEncoding = image.substring(0, image.indexOf("base64,") + 7);
        System.out.println("imageEncoding = " + imageEncoding);
        bakery.setImageEncoding(imageEncoding);

        //FileOutputStream fos = new FileOutputStream("output3.gif");
        //fos.write(imageBinary);

        // ToDo: ★ loginIdが既に登録済みの場合、登録できないようにする必要がある。
        List<Bakery> bakeryList = bakeryService.findAll();
        for(Bakery bakeryInDB : bakeryList){
            if(bakery.getLoginId().equals(bakeryInDB.getLoginId())){
                throw new HotPansException("ログインIDが重複しています。");
            }
        }

        return bakeryService.create(bakery);
    }

    // パン屋さん１件更新
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    Bakery putBakery(@PathVariable Integer id, @RequestBody Bakery bakery){
        bakery.setId(id);
        return bakeryService.update(bakery);
    }

    // パン屋さん１件削除
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBakery(@PathVariable Integer id){
        bakeryService.delete(id);
    }

}
