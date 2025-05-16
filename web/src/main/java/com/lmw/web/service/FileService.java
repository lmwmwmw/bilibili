package com.lmw.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(MultipartFile file);

    /**
     * 根据URL删除文件
     * @param url 文件URL
     * @return 是否删除成功
     */
    boolean deleteByUrl(String url);
}
