package com.wotung.integration.member.web.v1;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Api(tags = "验证码")
@RestController
public class KaptchaController {
    private final static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "获取图片验证码",notes = "返回code, image")
    @PostMapping(path = "/getVerifyCode")
    public JSONObject getPictureCode(){
//        String param = "ImgVerificationCodeMark";
        Map<String, Object> verificationCode = createPicVerificationCode();
        return new JSONObject(verificationCode);
    }

    public Map<String, Object> createPicVerificationCode() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String verificationCodeText = defaultKaptcha.createText();
            result.put("code", verificationCodeText);
            //加入到session方便后面验证
            BufferedImage image = defaultKaptcha.createImage(verificationCodeText);
            ImageIO.write(image,"jpg",outputStream);
            byte[] captchaChallengeAsJpeg = outputStream.toByteArray();
            result.put("image",Base64.getEncoder().encodeToString(captchaChallengeAsJpeg) );
        }catch (Exception e){
            result.put("code", null);
            result.put("image", null);
            logger.error("生成图片验证码异常"+e);
        }finally {
            try {
                outputStream.close();
            }catch (IOException e){
                logger.error("生成图片验证码结束，关闭ByteArrayOutputStream资源失败"+e);
            }
        }
        return result;
    }


    @Bean(name="captchaProducer")
    public DefaultKaptcha getKaptchaBean(){
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties=new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.border.color", "white");
        properties.setProperty("kaptcha.textproducer.font.color", "255,192,55");
        properties.setProperty("kaptcha.image.width", "125");
        properties.setProperty("kaptcha.image.height", "45");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.font.size", "38");
        properties.setProperty("kaptcha.noise.color","21,113,171");
        properties.setProperty("kaptcha.background.clear.from","0,154,255");
        properties.setProperty("kaptcha.background.clear.to","0,202,255");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "Arial");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
