package com.socialuni.social.depoloy.sdk.controller;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.system.SystemUtil;
import com.github.odiszapc.nginxparser.*;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.depoloy.sdk.constant.FileSizeConst;
import com.socialuni.social.depoloy.sdk.dao.DO.SocialuniDeployProjectDO;
import com.socialuni.social.depoloy.sdk.dao.DO.SocialuniDeployProjectTempNameDO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
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
    public static final String systemDomain = "velox";
    public static final String domainSuffix = "run";

    public static String getDomainSuffix() {
        return domainSuffix;
    }

    public static final String httpSplit = ".";

    public static String getSystemDomain() {
//        if (SystemUtils.IS_OS_WINDOWS) {
//            return "localhost";
//        }
        return systemDomain + httpSplit + getDomainSuffix();
    }

    public static String getHttpDomain(String projectName) {
        String commonDomain = getSystemDomain();

        String httpDomain;
        if (systemDomain.equals(projectName)) {
            // velox.run
            httpDomain = commonDomain;
        } else {
            // [projectName].velox.run
            httpDomain = projectName + httpSplit + commonDomain;
        }
        return httpDomain;
    }

    public static final String winNginxPath = "/devtools/nginx";
    public static final String linuxNginxPath = "/www/server/nginx";


    @GetMapping("queryDomainName")
    @ResponseBody
    public ResultRO<String> queryDomainName() {
        return ResultRO.success(UploadFileController.systemDomain);
    }

    @GetMapping("queryProjectName/{projectName}")
    @ResponseBody
    public ResultRO<Boolean> checkProjectName(@PathVariable("projectName") String projectName) {
        try {
            return ResultRO.success(checkProject(projectName));
        } catch (Exception e) {
            return ResultRO.success(false);
        }
    }

    public static boolean checkProject(String projectName) {
        if (StringUtils.isEmpty(projectName)) {
            throw new SocialBusinessException("项目名称不能为空");
        } else if (projectName.length() < 3) {
            throw new SocialBusinessException("项目名需大于2个字符");
        } else if (projectName.length() > 16) {
            throw new SocialBusinessException("项目名需小于17个字符");
        }
        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();
        if (projectName.equals(systemDomain)) {
            Integer systemUserId = SocialuniUserUtil.getSystemUserIdNotNull();
            if (!systemUserId.equals(mineUserId)) {
                throw new SocialBusinessException("项目名称不可用");
            }
            return true;
        }
        //如果项目名存在
        SocialuniDeployProjectDO socialuniDeployProjectDO = SocialuniUserRepositoryFacede.findByCustomFieldAndStatus("projectName", projectName, SocialuniCommonStatus.enable, SocialuniDeployProjectDO.class);
        //不存在可以直接使用
        if (socialuniDeployProjectDO == null) {
            return true;
        }
        Integer userId = socialuniDeployProjectDO.getUserId();
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
            String nginxPathRoot;
            if (SystemUtils.IS_OS_WINDOWS) {
                nginxPathRoot = winNginxPath;
            } else {
                nginxPathRoot = linuxNginxPath;
            }
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
                String fileName = String.join("/", newOriginalFileNames);

                System.out.println(file);
                System.out.println(file.getOriginalFilename());
                System.out.println(fileName);
                byte[] bytes = file.getBytes();
                Path nginxPath = Paths.get(nginxPathRoot + "/project/", fileName);
                Path parentDir = nginxPath.getParent();
                System.out.println(parentDir);
                //如果没有files文件夹，则创建
                if (!Files.isWritable(parentDir)) {
                    Files.createDirectories(parentDir);
                }
                //文件写入指定路径
                Files.write(nginxPath, bytes);
            }
            SocialuniDeployProjectDO socialuniDeployProjectDO = SocialuniUserRepositoryFacede.findByCustomFieldAndStatus("projectName", projectName, SocialuniCommonStatus.enable, SocialuniDeployProjectDO.class);

            UploadFileController.pushNginxConfig(socialuniDeployProjectDO);

            if (socialuniDeployProjectDO == null) {
                Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();
                socialuniDeployProjectDO = new SocialuniDeployProjectDO();
                socialuniDeployProjectDO.setProjectName(projectName);
                socialuniDeployProjectDO.setUserId(mineUserId);
                socialuniDeployProjectDO.setMainFile(mainFile);
                SocialuniRepositoryFacade.save(socialuniDeployProjectDO);
            }

            if (SystemUtils.IS_OS_WINDOWS) {
                String command = nginxPathRoot + "/nginx -s reload";

                // 执行一个Windows命令，例如启动记事本
                Process process = Runtime.getRuntime().exec(command);

                // 等待命令执行完成
                process.waitFor();
            } else {
                String command = nginxPathRoot + "/sbin/nginx -s reload";

                // 执行一个Windows命令，例如启动记事本
                Process process = Runtime.getRuntime().exec(command);

                // 等待命令执行完成
                process.waitFor();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(files);
        System.out.println(files.length);
        String httpUrl = getHttpDomain(projectName);
        return ResultRO.success("http://" + httpUrl);
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

    public static void main(String[] args) throws IOException {
        SocialuniDeployProjectDO socialuniDeployProjectDO = new SocialuniDeployProjectDO();
        socialuniDeployProjectDO.setProjectName("velox");
        socialuniDeployProjectDO.setMainFile("index.html");
        UploadFileController.pushNginxConfig(socialuniDeployProjectDO);
    }

    public static void pushNginxConfig(SocialuniDeployProjectDO socialuniDeployProjectDO) throws IOException {
        String nginxPath;
        if (SystemUtils.IS_OS_WINDOWS) {
            nginxPath = winNginxPath;
        } else {
            nginxPath = linuxNginxPath;
        }
        NgxConfig conf = NgxConfig.read(nginxPath + "/conf/nginx.conf");
        NgxBlock http = conf.findBlock("http");


        String projectName = socialuniDeployProjectDO.getProjectName();
        String mainFile = socialuniDeployProjectDO.getMainFile();

        String httpDomain = getHttpDomain(projectName);

        List<NgxEntry> rtmpServers = conf.findAll(NgxConfig.PARAM, "http", "server", "server_name");
        for (NgxEntry entry : rtmpServers) {
            String hasValue = ((NgxParam) entry).getValue();
            if (httpDomain.equals(hasValue)) {
                log.info("已经存在，{}，直接返回", hasValue);
                //已经存在，则直接返回
                return;
            }
        }
//        if (SystemUtils.IS_OS_WINDOWS) {
//            NgxBlock newServer80 = new NgxBlock();
//
//            newServer80.addValue("server");
//
//            NgxParam newListen80 = new NgxParam();
//            newListen80.addValue("listen");
//            newListen80.addValue("80");
//            newServer80.addEntry(newListen80);
//
//            NgxParam server_name80 = new NgxParam();
//            server_name80.addValue("server_name");
//            server_name80.addValue(httpDomain);
//            newServer80.addEntry(server_name80);
//
//            NgxBlock newLocation80 = new NgxBlock();
//            newLocation80.addValue("location");
//            newLocation80.addValue("/");
//
//            NgxParam root443 = new NgxParam();
//            root443.addValue("root");
//            root443.addValue("project/" + projectName);
//            newLocation80.addEntry(root443);
//
//            NgxParam try_files443 = new NgxParam();
//            try_files443.addValue("try_files");
//            try_files443.addValue("$uri $uri/ /" + mainFile);
//            newLocation80.addEntry(try_files443);
//
//            newServer80.addEntry(newLocation80);
//
//            http.addEntry(newServer80);
//        } else {
        NgxBlock newServer80 = new NgxBlock();

        newServer80.addValue("server");

        NgxParam newListen80 = new NgxParam();
        newListen80.addValue("listen");
        newListen80.addValue("80");
        newServer80.addEntry(newListen80);

        NgxParam server_name80 = new NgxParam();
        server_name80.addValue("server_name");
        server_name80.addValue(httpDomain);
        newServer80.addEntry(server_name80);

        NgxBlock newLocation80 = new NgxBlock();
        newLocation80.addValue("location");
        newLocation80.addValue("/");

        NgxParam return301 = new NgxParam();
        return301.addValue("return 301 https://" + httpDomain + "$request_uri");
        newLocation80.addEntry(return301);

        newServer80.addEntry(newLocation80);


        NgxBlock newServer443 = new NgxBlock();
        newServer443.addValue("server");

        NgxParam newListen443 = new NgxParam();
        newListen443.addValue("listen");
        newListen443.addValue("443 ssl");
        newServer443.addEntry(newListen443);

        NgxParam server_name443 = new NgxParam();
        server_name443.addValue("server_name");
        server_name443.addValue(httpDomain);
        newServer443.addEntry(server_name443);

        NgxParam ssl_certificate443 = new NgxParam();
        ssl_certificate443.addValue("ssl_certificate");
        ssl_certificate443.addValue("velox.run_bundle.crt");
        newServer443.addEntry(ssl_certificate443);

        NgxParam ssl_certificate_key443 = new NgxParam();
        ssl_certificate_key443.addValue("ssl_certificate_key");
        ssl_certificate_key443.addValue("velox.run.key");
        newServer443.addEntry(ssl_certificate_key443);


        NgxParam ssl_session_timeout443 = new NgxParam();
        ssl_session_timeout443.addValue("ssl_session_timeout");
        ssl_session_timeout443.addValue("5m");
        newServer443.addEntry(ssl_session_timeout443);

        NgxParam ssl_protocols443 = new NgxParam();
        ssl_protocols443.addValue("ssl_protocols");
        ssl_protocols443.addValue("TLSv1 TLSv1.1 TLSv1.2");
        newServer443.addEntry(ssl_protocols443);

        NgxParam ssl_ciphers443 = new NgxParam();
        ssl_ciphers443.addValue("ssl_ciphers");
        ssl_ciphers443.addValue("ECDHE-RSA-AES128-GCM-SHA256:HIGH:!aNULL:!MD5:!RC4:!DHE");
        newServer443.addEntry(ssl_ciphers443);

        NgxParam ssl_prefer_server_ciphers443 = new NgxParam();
        ssl_prefer_server_ciphers443.addValue("ssl_prefer_server_ciphers");
        ssl_prefer_server_ciphers443.addValue("on");
        newServer443.addEntry(ssl_prefer_server_ciphers443);

        NgxBlock newLocation443 = new NgxBlock();
        newLocation443.addValue("location");
        newLocation443.addValue("/");

        NgxParam root443 = new NgxParam();
        root443.addValue("root");
        root443.addValue("project/" + projectName);
        newLocation443.addEntry(root443);

        NgxParam try_files443 = new NgxParam();
        try_files443.addValue("try_files");
        try_files443.addValue("$uri $uri/ /" + mainFile);
        newLocation443.addEntry(try_files443);

        newServer443.addEntry(newLocation443);


        http.addEntry(newServer80);
        http.addEntry(newServer443);
//        }


//        server {
//            listen       80;
//            server_name  socialuni.cn;
//            location / {
//            return 301 https://socialuni.cn$request_uri;
//             }
//        }


        NgxDumper dumper = new NgxDumper(conf);
        String newConfig = dumper.dump();

        FileWriter writer = new FileWriter(nginxPath + "/conf/nginx.conf");
        writer.write(newConfig);
    }
}
