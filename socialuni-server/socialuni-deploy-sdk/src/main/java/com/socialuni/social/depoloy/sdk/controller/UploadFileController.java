package com.socialuni.social.depoloy.sdk.controller;

import cn.hutool.core.io.file.FileWriter;
import com.github.odiszapc.nginxparser.NgxBlock;
import com.github.odiszapc.nginxparser.NgxConfig;
import com.github.odiszapc.nginxparser.NgxDumper;
import com.github.odiszapc.nginxparser.NgxParam;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.depoloy.sdk.constant.FileSizeConst;
import com.socialuni.social.depoloy.sdk.dao.DO.SocialuniDeployProjectDO;
import com.socialuni.social.depoloy.sdk.dao.DO.SocialuniDeployProjectTempNameDO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequestMapping("socialuni/deploy")
@RestController
@Slf4j
public class UploadFileController {
    @GetMapping("queryProjectName/{projectName}")
    @ResponseBody
    public ResultRO<Boolean> checkProjectName(@PathVariable("projectName") String projectName) {
        return ResultRO.success(checkProject(projectName));
    }

    public static boolean checkProject(String projectName) {
        if (StringUtils.isEmpty(projectName)) {
            throw new SocialBusinessException("项目名称不能为空");
        }
        //如果项目名存在
        SocialuniDeployProjectDO socialuniDeployProjectDO = SocialuniUserRepositoryFacede.findByCustomFieldAndStatus("path", projectName, SocialuniCommonStatus.enable, SocialuniDeployProjectDO.class);
        //不存在可以直接使用
        if (socialuniDeployProjectDO == null) {
            return true;
        }
        Integer userId = socialuniDeployProjectDO.getUserId();
        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();
        return userId != null && userId.equals(mineUserId);
    }

    @PostMapping("deployProject")
    @ResponseBody
    public ResultRO<String> deployProject(
            @RequestParam(value = "files") MultipartFile[] files,
            @RequestParam(value = "projectName") String projectName,
            @RequestParam(value = "mainFile") String mainFile
    ) {
        boolean nameCanUse = checkProject(projectName);
        if (!nameCanUse) {
            throw new SocialBusinessException("项目名称已存在");
        }
        if (StringUtils.isEmpty(mainFile)) {
            mainFile = "index.html";
        }
        if (Objects.isNull(files) || files.length < 1) {
            throw new SocialSystemException("文件为空，请重新上传");
        }
        try {
            for (MultipartFile file : files) {
                if (Objects.isNull(file) || file.isEmpty()) {
                    throw new SocialSystemException("文件为空，请重新上传");
                }
                if (file.getSize() > FileSizeConst._30MB) {
                    throw new SocialSystemException("图片大小不能超过30MB！");
                }
                String originalFileName = file.getOriginalFilename();
                if (StringUtils.isEmpty(originalFileName)) {
                    continue;
                }
                List<String> originalFileNames = Arrays.asList(originalFileName.split("/"));
                List<String> newOriginalFileNames = new ArrayList<>();
                newOriginalFileNames.add(projectName);
                newOriginalFileNames.addAll(originalFileNames.subList(1, originalFileNames.size()));

                String newOriginalFileName = String.join("/", newOriginalFileNames);

                System.out.println(file);
                System.out.println(file.getOriginalFilename());
                System.out.println(newOriginalFileName);
                byte[] bytes = file.getBytes();
                Path nginxPath = Paths.get("/devtools/nginx/project/", newOriginalFileName);
                Path parentDir = nginxPath.getParent();
                System.out.println(parentDir);
                //如果没有files文件夹，则创建
                if (!Files.isWritable(parentDir)) {
                    Files.createDirectories(parentDir);
                }
                //文件写入指定路径
                Files.write(nginxPath, bytes);
            }
            SocialuniDeployProjectDO socialuniDeployProjectDO = new SocialuniDeployProjectDO();
            socialuniDeployProjectDO.setPath(projectName);
            socialuniDeployProjectDO.setMainFile(mainFile);

            UploadFileController.pushNginxConfig(socialuniDeployProjectDO);

            SocialuniRepositoryFacade.save(socialuniDeployProjectDO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(files);
        System.out.println(files.length);
        return ResultRO.success("http://localhost:9536/" + projectName);
//        return ResultRO.success("https://app.socialuni.cn/" + projectName);
    }

    @GetMapping("autoCreateCanUseProjectName")
    @ResponseBody
    public ResultRO<String> autoCreateCanUseProjectName() {

        //获取一个可用的项目名
        SocialuniDeployProjectTempNameDO socialuniDeployProjectTempNameDO = SocialuniRepositoryFacade.findByStatus(
                SocialuniCommonStatus.enable,
                SocialuniDeployProjectTempNameDO.class
        );
        socialuniDeployProjectTempNameDO.setStatus(SocialuniCommonStatus.delete);
        socialuniDeployProjectTempNameDO.setUpdateTime(new Date());
        SocialuniRepositoryFacade.save(socialuniDeployProjectTempNameDO);

        return ResultRO.success(socialuniDeployProjectTempNameDO.getName());
    }

    public static void pushNginxConfig(SocialuniDeployProjectDO socialuniDeployProjectDO) throws IOException {
        String rootPath = socialuniDeployProjectDO.getPath();

        NgxConfig conf = NgxConfig.read("/devtools/nginx/conf/nginx.conf");
        NgxBlock http = conf.findBlock("http");


        NgxBlock new80Server = new NgxBlock();
//        NgxBlock new443Server = new NgxBlock();
//        new80Server.addValue("server");
//
//        NgxParam listen80 = new NgxParam();
//        listen80.addValue("listen");
//        listen80.addValue("80");
//        new80Server.addEntry(listen80);
//
//
//        NgxParam server_name80 = new NgxParam();
//        server_name80.addValue("server_name");
//        server_name80.addValue(rootPath + ".velox.run");
//        new80Server.addEntry(server_name80);
//
//
//
//        NgxParam newFiles = new NgxParam();
//        newFiles.addValue("try_files");
//        newFiles.addValue("$uri $uri/ /" + socialuniDeployProjectDO.getPath() + "/" + socialuniDeployProjectDO.getMainFile());
//        new80Server.addEntry(newFiles);
//
//
//        NgxBlock newLocation = new NgxBlock();
//
//        newLocation.addValue("location");
//        newLocation.addValue("^~/" + socialuniDeployProjectDO.getPath());
//
//        NgxParam newAlias = new NgxParam();
//        newAlias.addValue("alias");
//        newAlias.addValue("/devtools/nginx/project/" + socialuniDeployProjectDO.getPath());
//        newLocation.addEntry(newAlias);
//
//        NgxParam newFiles = new NgxParam();
//        newFiles.addValue("try_files");
//        newFiles.addValue("$uri $uri/ /" + socialuniDeployProjectDO.getPath() + "/" + socialuniDeployProjectDO.getMainFile());
//        newLocation.addEntry(newFiles);

//        server {
//            listen       80;
//            server_name  admin.socialuni.cn;
//            location / {
//            return 301 https://admin.socialuni.cn$request_uri;
//                    }
//        }


        NgxBlock newLocation = new NgxBlock();

        newLocation.addValue("location");
        newLocation.addValue("^~/" + socialuniDeployProjectDO.getPath());

        NgxParam newAlias = new NgxParam();
        newAlias.addValue("alias");
        newAlias.addValue("/devtools/nginx/project/" + socialuniDeployProjectDO.getPath());
        newLocation.addEntry(newAlias);

        NgxParam newFiles = new NgxParam();
        newFiles.addValue("try_files");
        newFiles.addValue("$uri $uri/ /" + socialuniDeployProjectDO.getPath() + "/" + socialuniDeployProjectDO.getMainFile());
        newLocation.addEntry(newFiles);

        new80Server.addEntry(newLocation);

        http.addEntry(new80Server);

//        server {
//            listen       80;
//            server_name  admin.socialuni.cn;
//            location / {
//            return 301 https://admin.socialuni.cn$request_uri;
//                    }
//        }
//        server {
//            listen 443 ssl; #SSL 访问端口号为 443
//            server_name admin.socialuni.cn; #填写绑定证书的域名
//            ssl_certificate admin.socialuni.cn_bundle.crt; #证书文件名称
//            ssl_certificate_key admin.socialuni.cn.key; #私钥文件名称
//            ssl_session_timeout 5m;
//            ssl_protocols TLSv1 TLSv1.1 TLSv1.2; #请按照这个协议配置
//            ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:HIGH:!aNULL:!MD5:!RC4:!DHE; #请按照这个套件配置，配置加密套件，写法遵循 openssl 标准。
//            ssl_prefer_server_ciphers on;
//            location / {
//                    root   project/devadmin/dist;
//            try_files $uri $uri/ /index.html;
//                     }
//        }

        NgxDumper dumper = new NgxDumper(conf);
        String newConfig = dumper.dump();

        FileWriter writer = new FileWriter("/devtools/nginx/conf/nginx.conf");
        writer.write(newConfig);
    }
}
