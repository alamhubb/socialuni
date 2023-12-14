package com.socialuni.social.depoloy.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("upload")
public class UploadController {

    @PostMapping("img")
    public ResultRO<Void> uploadImg(@RequestParam("file") MultipartFile file, @RequestParam(value = "type", required = false) String uploadType) throws IOException {
//        if (uploadType == null) {
//            QingLogger.logger.warn("上传了错误的图片类型");
//            return new ResultVO<>("上传了错误的图片类型");
//        }
//        if (file == null || file.isEmpty()) {
//            return new ResultVO<>("文件为空，请重新上传");
//        }
//        if (file.getSize() > FileSizeConst._10MB) {
//            return new ResultVO<>("图片大小不能超过10MB！");
//        }
//
//        //校验图片是否合法
//        byte[] byt = file.getBytes();
//        if (file.getSize() > FileSizeConst.MB) {
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            Thumbnails.of(file.getInputStream()).size(700, 1300).toOutputStream(outputStream);
//            byt = outputStream.toByteArray();
//        }
//        if (TencentCloud.imgIsViolation(byt)) {
//            return new ResultVO<>(ErrorMsg.CHECK_IMG_VIOLATION_ERR_MSG);
//        }
//        String originalFilename = file.getOriginalFilename();
//        Objects.requireNonNull(originalFilename);
//        String[] separators = originalFilename.trim().split("/");
//        String lastSeparatorStr = separators[separators.length - 1];
//        //获取文件后缀名
//        String[] suffix = lastSeparatorStr.split("\\.");
//        //不包含.
//        String fileTypeName;
//        if (suffix.length < 2) {
//            fileTypeName = ".jpg";
//            QingLogger.logger.error(suffix[0]);
//        } else {
//            fileTypeName = "." + suffix[suffix.length - 1];
//        }
//        //文件名称
//        String fileName = TokenUtils.getUUID() + fileTypeName;
//        // 1 初始化用户身份信息（secretId, secretKey）。
//        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
//        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/DOcument/product/436/6224
//        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
//        Region region = new Region(regionName);
//        ClientConfig clientConfig = new ClientConfig(region);
//        // 3 生成 cos 客户端。
//        COSClient cosClient = new COSClient(cred, clientConfig);
//        File file1 = File.createTempFile("testrunoobtmp1", fileTypeName);
//        file.transferTo(file1);
//        Thumbnails.of(file1).size(2000, 2000).toFile(file1);
//        String imgNormalKey = "user/" + user.getId() + "/talk/normal/" + fileName;
//        cosClient.putObject(bucketName, imgNormalKey, file1);
//
//        //写入缩略图片
//        Thumbnails.of(file1).size(350, 350).toFile(file1);
//        String imgThumbnailKey = "user/" + user.getId() + "/talk/thumbnail/" + fileName;
//        cosClient.putObject(bucketName, imgThumbnailKey, file1);
//
//        ImageIcon imageIcon = new ImageIcon(file1.getAbsolutePath());
//        int imgWidth = imageIcon.getIconWidth();
//        int imgHeight = imageIcon.getIconHeight();
//        return new ResultVO<>(new UploadImgVO(fileName, imgWidth, imgHeight));
        return null;
    }

    //前端没有使用
    @PostMapping("imgs")
    public String uploadImgList(MultipartFile[] imgs) {
        /*for (MultipartFile file : imgs) {
            if (Objects.isNull(file) || file.isEmpty()) {
                return "文件为空，请重新上传";
            }
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
                //如果没有files文件夹，则创建
                if (!Files.isWritable(path)) {
                    Files.createDirectories(Paths.get(UPLOAD_FOLDER));
                }
                //文件写入指定路径
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        return "成功";
    }
}
