package org.fire.platform.util;

import com.alibaba.fastjson.JSON;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by 吴桂媚 on 2017/01/09
 */
public class QRCodeWParam {
    /**
     * 公共的生成参数二维码
     * @param path    根目录  一般写二维码生成文件夹  配置文件里的qr_code_dir
     * @param param   一系列键值参数
     * @param objType 类型
     * @param rqUrl
     * @param key     文件名  相同类型、相同文件名将覆盖
     * @return          返回二维码生成的本地地址
     * @throws IOException
     * @throws WriterException
     */
    public static String generateQRCode(String path, Map<String,Object> param, String objType, String rqUrl, String key) throws IOException, WriterException {
        String format = "gif";
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        // 内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        String folder = path + File.separator + objType;
        File folderFile = new File(folder);
        if(!folderFile.exists()){
            folderFile.mkdirs();
        }
        String signUrl = folder + File.separator + key + ".gif";
        String object = getJSONString(param,objType,rqUrl);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(object,
                BarcodeFormat.QR_CODE, 1000, 1000, hints);
        File outputFile = new File(signUrl);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        ImageIO.write(image, format, outputFile);
        return signUrl;
    }

    /**
     * 生成参数二维码
     * 扫码打开链接
     * @param path  根目录  一般写二维码生成文件夹  配置文件里的qr_code_dir
     * @param redirectUrl
     * @param key       文件名  相同类型、相同文件名将覆盖
     * @return           返回二维码生成的本地地址
     * @throws IOException
     * @throws WriterException
     */
    public static String generateQRCode(String path,String redirectUrl, String key) throws IOException, WriterException {
        String format = "gif";
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        // 内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        String folder = path + File.separator + "NOTICE_SHARE_QRCODE";
        File folderFile = new File(folder);
        if(!folderFile.exists()){
            folderFile.mkdirs();
        }
        String signUrl = folder + File.separator + key + ".gif";
        BitMatrix bitMatrix = new MultiFormatWriter().encode(redirectUrl,
                BarcodeFormat.QR_CODE, 200, 200, hints);
        File outputFile = new File(signUrl);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        ImageIO.write(image, format, outputFile);
        return signUrl;
    }

    private static String getJSONString(Map<String, Object> param, String objType, String rqUrl){
        Map<String,Object> map = new HashMap<>();
        map.put("objType",objType);
        map.put("rqUrl",rqUrl);
        map.put("otherParams", param);
//        JSONObject object = JSONObject.fromObject(map);
//        return object.toString();
        String json = JSON.toJSONString(map,true);
        System.out.println(json);
        return json;

    }

    private static String getJSONString(Map<String, Object> param, String objType){
        Map<String,Object> map = new HashMap<>();
        map.put("type",objType);
        map.put("params", param);
//        JSONObject object = JSONObject.fromObject(map);
//        return object.toString();
        String json = JSON.toJSONString(map,true);
        System.out.println(json);
        return json;
    }

    public static void main(String[] args) {
        try {
            Long i = System.currentTimeMillis();
            Map<String,Object> map = new HashMap<>();
            map.put("classId",123456);
            map.put("signDefineId","34567");
            map.put("rqUrl","http://192.168.1.11:8380/front/classes/signInTrainingClass?signDefineId=34567");
            String path = QRCodeWParam.generateQRCode("E:\\qr_code_dir",map,"CLASS_SIGN_IN","","123456_34567");
            System.out.println(System.currentTimeMillis()-i);
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
