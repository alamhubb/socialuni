package com.socialuni.social.sdk.platform.baidu;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BaiduDecode {
    private static Charset CHARSET = StandardCharsets.UTF_8;

    /**
     * 对密文进行解密
     *
     * @param text 需要解密的密文
     * @return 解密得到的明文
     */
    public String decrypt(String text, String sessionKey) {
        byte[] aesKey = Base64.decodeBase64(sessionKey + "=");
        byte[] original = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            byte[] encrypted = Base64.decodeBase64(text);
            original = cipher.doFinal(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String xmlContent = null;
        String fromClientId;
        try {
            // 去除补位字符
            byte[] bytes = PKCS7Encoder.decode(original);
            // 分离16位随机字符串,网络字节序和ClientId
            byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);
            int xmlLength = recoverNetworkBytesOrder(networkOrder);
            xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
            fromClientId = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length), CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlContent;
    }

    /**
     * 还原4个字节的网络字节序
     *
     * @param orderBytes 字节码
     * @return sourceNumber
     */
    private int recoverNetworkBytesOrder(byte[] orderBytes) {
        int sourceNumber = 0;
        int length = 4;
        int number = 8;
        for (int i = 0; i < length; i++) {
            sourceNumber <<= number;
            sourceNumber |= orderBytes[i] & 0xff;
        }
        return sourceNumber;
    }

    /**
     * 加密机密demo
     *
     * @param args
     */
    public static void main(String[] args) {
        /*String resultString = DigestUtils.md5Hex("appid=wx682b729afd398217&bank_type=OTHERS&cash_fee=100&fee_type=CNY&is_subscribe=N&mch_id=1577008821&nonce_str=bdf0ec1ef9e544e5808898c5a6b9664f&openid=o0eddwMgEjdxvrXu2mCRf5r8hfNA&out_trade_no=WAS47702020071701275186800000621&result_code=SUCCESS&return_code=SUCCESS&time_end=20200717012758&total_fee=100&trade_type=APP&transaction_id=4200000593202007172090511370&key=5a1847ff8a664d6f984f81f889985e62");
        System.out.println(resultString);
*/

        StringBuilder words = new StringBuilder();
        words.append("发手机快放假啊四路口附近开了撒就浪费点卡即刻类1");
        words.substring(0,words.length()-1);
        System.out.println(words.substring(0,words.length()-1));
        //连接本地的 Redis 服务
    }
//        String token = TokenUtils.getUUID();
//        String userId = TokenUtils.getIdByToken(token);
        /*Jwts.parser().setSigningKey("1234").parseClaimsJws(token);
        try {
            Jwts.parser().setSigningKey("1234").parseClaimsJws(token);
        } catch (MalformedJwtException e) {
        }*/


    /*//## 标题二分法查找,返回插入点索引
    public static int binary(Integer[] arr, Integer n) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid].equals(n)) {
                return mid;
            } else if (arr[mid] > n) {
                high = mid - 1;
            } else if (arr[mid] < n) {
                low = mid + 1;
            }
        }
        return mid + 1;
    }*/

}
