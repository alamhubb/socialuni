package com.socialuni.social.depoloy.sdk.controller;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.depoloy.sdk.constant.FileSizeConst;
import com.socialuni.social.depoloy.sdk.dao.DO.SocialuniDeployProjectDO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RequestMapping("socialuni/deploy")
@RestController
@Slf4j
public class UploadFileController {
    @GetMapping("queryProjectName/{projectName}")
    @ResponseBody
    public ResultRO<Boolean> checkProjectName(@PathVariable("projectName") String projectName) {
        return ResultRO.success(checkProject(projectName));
    }

    private static boolean checkProject(String projectName) {
        if (StringUtils.isEmpty(projectName)) {
            throw new SocialBusinessException("项目名称不能为空");
        }
        //如果项目名存在
        SocialuniDeployProjectDO socialuniDeployProjectDO = SocialuniUserRepositoryFacede.findByCustomFieldAndStatus("projectName", projectName, SocialuniCommonStatus.enable, SocialuniDeployProjectDO.class);
        //不存在可以直接使用
        if (socialuniDeployProjectDO == null) {
            return true;
        }
        Integer userId = socialuniDeployProjectDO.getUserId();
        if (userId == null) {
            //无归属，可使用
            return true;
        }
        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();
        //不一致，则不可用
        return userId.equals(mineUserId);
    }

    @PostMapping("deployProject")
    @ResponseBody
    public ResultRO<String> deployProject(
            @RequestParam(value = "files") MultipartFile[] files,
            @RequestParam(value = "path") String path,
            @RequestParam(value = "mainFile") String mainFile
    ) {
        boolean nameCanUse = checkProject(path);
        if (!nameCanUse) {
            throw new SocialBusinessException("项目名称已存在");
        }
        if (StringUtils.isEmpty(mainFile)) {
            mainFile = "index.html";
        }
        if (Objects.isNull(files) || files.length < 1) {
            throw new SocialSystemException("文件为空，请重新上传");
        }
        for (MultipartFile file : files) {
            if (Objects.isNull(file) || file.isEmpty()) {
                throw new SocialSystemException("文件为空，请重新上传");
            }
            if (file.getSize() > FileSizeConst._30MB) {
                throw new SocialSystemException("图片大小不能超过100MB！");
            }
            System.out.println(file);
            System.out.println(file.getOriginalFilename());
            try {
                byte[] bytes = file.getBytes();
                Path nginxPath = Paths.get("/devtools/nginx/project/" + file.getOriginalFilename());
                System.out.println(nginxPath.getParent());
                System.out.println(nginxPath.getFileSystem());
                System.out.println(nginxPath.getFileName());
                System.out.println(nginxPath.getName(0));
                System.out.println(nginxPath.getName(1));
                System.out.println(nginxPath.getRoot());
                System.out.println(file.getOriginalFilename());
                //如果没有files文件夹，则创建
                if (!Files.isWritable(nginxPath.getParent())) {
                    Files.createDirectories(nginxPath.getParent());
                }
                //文件写入指定路径
                Files.write(nginxPath, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(files);
        System.out.println(files.length);
        return ResultRO.success("http://localhost:9536/" + path);
//        return ResultRO.success("https://app.socialuni.cn/" + projectName);
    }
}
