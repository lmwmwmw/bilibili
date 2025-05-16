package com.lmw.web.controller.file;

import com.lmw.common.result.Result;
import com.lmw.web.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
@Tag(name = "文件上传接口")
public class FileController {

    @Autowired
    private FileService fileUploadService;

    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam MultipartFile file) throws IOException {
        String url = fileUploadService.upload(file);
        return Result.ok(url);
    }
}
