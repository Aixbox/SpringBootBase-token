package com.aixbox.usercenter.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 60 * 60 *1000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文 这里的值设置的别太长
    public static final String JWT_KEY = "ls";

    /**
     * 生成一个随机的UUID（Universally Unique Identifier）字符串，并将其中的"-"字符移除后返回。
     * UUID是一个128位的数字，通常用32位的十六进制字符串表示。它是由计算机系统中的各种元素（如MAC地址、时间戳等）生成的唯一标识符，可以用于在分布式系统中唯一标识实体。
     * 在这段代码中，使用UUID类的randomUUID()方法生成一个随机的UUID对象，然后使用toString()方法将其转换为字符串。由于UUID字符串中包含了"-"字符，所以使用replaceAll()方法将其移除。最后将处理后的字符串作为结果返回。
     * @return
     */
    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    /**
     * 创建一个JWT（JSON Web Token）字符串。
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        //首先调用了一个名为getJwtBuilder()的方法，该方法返回一个JwtBuilder对象。然后，使用该对象设置JWT的主题（subject）、过期时间和一个随机生成的UUID作为JWT的唯一标识符。最后，调用JwtBuilder对象的compact()方法将JWT构建为一个字符串，并将其作为结果返回。
        //需要注意的是，这段代码中并没有设置签名，所以生成的JWT是未签名的。在实际应用中，通常需要使用密钥对JWT进行签名，以确保其真实性和完整性。
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间

        return builder.compact();
    }

    /**
     * 创建一个带有过期时间的JWT（JSON Web Token）字符串。
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis 用于设置JWT的过期时间。ttlMillis是一个Long类型的参数，表示JWT的有效期限，单位为毫秒。
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        //首先调用了一个名为getJwtBuilder()的方法，该方法返回一个JwtBuilder对象。然后，使用该对象设置JWT的主题（subject）、过期时间（ttlMillis）和一个随机生成的UUID作为JWT的唯一标识符。最后，调用JwtBuilder对象的compact()方法将JWT构建为一个字符串，并将其作为结果返回。\
        //需要注意的是，这段代码中并没有设置签名，所以生成的JWT是未签名的。在实际应用中，通常需要使用密钥对JWT进行签名，以确保其真实性和完整性。
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * JwtBuilder对象，用于构建JWT（JSON Web Token）
     * @param subject
     * @param ttlMillis
     * @param uuid
     * @return
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        //首先定义了签名算法为HS256（HMAC SHA-256）
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //然后调用generalKey()方法生成一个密钥（SecretKey）
        SecretKey secretKey = generalKey();
        //获取当前时间的毫秒数，
        long nowMillis = System.currentTimeMillis();
        //将其转换为Date对象表示当前时间
        Date now = new Date(nowMillis);
        //如果传入的过期时间（ttlMillis）为null，则使用默认的过期时间（JwtUtil.JWT_TTL）
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }

        //计算过期时间的毫秒数
        long expMillis = nowMillis + ttlMillis;
        //将其转换为Date对象表示过期时间
        Date expDate = new Date(expMillis);
        //使用Jwts.builder()方法创建一个JwtBuilder对象。
        // 接下来，通过调用JwtBuilder对象的一系列方法设置JWT的各个属性，包括唯一ID（setId()）、主题（setSubject()）、签发者（setIssuer()）、签发时间（setIssuedAt()）、签名算法和密钥（signWith()）以及过期时间（setExpiration()）。
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("ls")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建一个带有自定义ID、主题和过期时间的JWT（JSON Web Token）字符串
     * @param id 参数id，用于设置JWT的唯一ID
     * @param subject 主题
     * @param ttlMillis 过期时间
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        //用了一个名为getJwtBuilder()的方法，该方法返回一个JwtBuilder对象。然后，使用该对象设置JWT的唯一ID（id）、主题（subject）和过期时间（ttlMillis）。
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        //调用JwtBuilder对象的compact()方法将JWT构建为一个字符串，并将其作为结果返回。
        return builder.compact();
    }

    public static void main(String[] args) throws Exception {
        String jwt = createJWT("132456");
        System.out.println(jwt);
    }

    /**
     * 生成一个密钥（SecretKey）用于JWT的签名。
     * @return
     */
    public static SecretKey generalKey() {
        //首先通过Base64解码将字符串JwtUtil.JWT_KEY转换为字节数组encodedKey
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        //使用SecretKeySpec类将字节数组encodedKey转换为一个SecretKey对象。这里使用的是AES对称加密算法。最后，返回生成的密钥对象。
        //这段代码中使用的密钥是从字符串JwtUtil.JWT_KEY解码而来的，实际应用中可能需要根据具体情况进行调整，例如从配置文件中读取密钥或使用其他方式生成密钥。
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析JWT（JSON Web Token）字符串，并返回其中的Claims对象。
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        //首先调用generalKey()方法获取密钥（SecretKey）
        SecretKey secretKey = generalKey();
        //然后，使用Jwts.parser()创建一个JwtParser对象，并通过调用setSigningKey()方法设置解析器的签名密钥为获取到的密钥。
        //接下来，调用JwtParser对象的parseClaimsJws()方法，传入JWT字符串jwt进行解析。该方法会返回一个Jws对象，通过调用getBody()方法获取其中的Claims对象。
        //最后，返回解析得到的Claims对象。
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}