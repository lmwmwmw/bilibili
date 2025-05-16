package com.lmw.web.service.impl;

import com.lmw.common.minio.MinioProperties;
import com.lmw.web.service.FileService;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {


    @Autowired
    private MinioClient minioClient;


    @Autowired
    private MinioProperties minioPropertites;
    @Override
    public String upload(MultipartFile file) {
        try {
            boolean bucketExists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(minioPropertites
                                    .getBucketName())
                            .build());
            if (!bucketExists) {
                minioClient.makeBucket
                        (MakeBucketArgs
                                .builder()
                                .bucket(minioPropertites.getBucketName())
                                .build());
                minioClient.setBucketPolicy
                        (SetBucketPolicyArgs.builder()
                                .bucket(minioPropertites.getBucketName())
                                .config(createBucketPolicyConfig(minioPropertites.getBucketName()))
                                .build());
            }
            String fileName = new SimpleDateFormat(
                    "yyyyMMdd").format(new Date())
                    + "/"
                    + UUID.randomUUID()
                    + "-"
                    + file.getOriginalFilename();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioPropertites
                                    .getBucketName())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .object(fileName)
                            .contentType
                                    (file.getContentType())
                            .build());

            String url = String.join("/",minioPropertites.getEndpoint(),minioPropertites.getBucketName(),fileName);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean deleteByUrl(String url) {
        try {
            // 1. 检查URL是否为空
            if (url == null || url.trim().isEmpty()) {
                log.error("删除文件失败: URL为空");
                return false;
            }

            // 2. 从URL中提取对象名称
            String objectName = extractObjectNameFromUrl(url);
            if (objectName == null) {
                log.error("删除文件失败: 无法从URL中提取对象名称 - {}", url);
                return false;
            }

            // 3. 调用MinIO客户端删除对象
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minioPropertites.getBucketName())
                            .object(objectName)
                            .build()
            );

            log.info("成功删除文件: {}", objectName);
            return true;

        } catch (Exception e) {
            log.error("删除文件出错: {}", e.getMessage(), e);
            return false;
        }

    }


    private String extractObjectNameFromUrl(String url) {
        try {
            // 去除URL中的协议部分 (http:// 或 https://)
            String[] parts = url.split("//", 2);
            String urlWithoutProtocol = parts.length > 1 ? parts[1] : parts[0];

            // 分割路径部分
            String[] pathParts = urlWithoutProtocol.split("/", 3);

            // URL格式应该是: host/bucketName/objectName
            if (pathParts.length < 3) {
                return null;
            }

            // 返回objectName部分
            return pathParts[2];

        } catch (Exception e) {
            log.error("解析URL出错: {}", e.getMessage());
            return null;
        }
    }

    private String createBucketPolicyConfig(String bucketName) {

        return """
                {
                  "Statement" : [ {
                    "Action" : "s3:GetObject",
                    "Effect" : "Allow",
                    "Principal" : "*",
                    "Resource" : "arn:aws:s3:::%s/*"
                  } ],
                  "Version" : "2012-10-17"
                }
                """.formatted(bucketName);
    }
}
