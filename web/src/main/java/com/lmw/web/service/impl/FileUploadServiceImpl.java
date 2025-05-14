package com.lmw.web.service.impl;

import com.lmw.common.minio.MinioProperties;
import com.lmw.web.service.FileUploadService;
import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Service
public class FileUploadServiceImpl implements FileUploadService {


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
