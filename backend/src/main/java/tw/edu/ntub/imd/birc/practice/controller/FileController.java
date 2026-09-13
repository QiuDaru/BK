package tw.edu.ntub.imd.birc.practice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tw.edu.ntub.imd.birc.practice.dto.file.uploader.MultipartFileUploader;
import tw.edu.ntub.imd.birc.practice.dto.file.uploader.UploadResult;
import tw.edu.ntub.imd.birc.practice.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.practice.util.json.object.ObjectData;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/files")
public class FileController {
    private final MultipartFileUploader multipartFileUploader;

    // 上傳一張圖，回傳可存取的 URL
    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        UploadResult result = multipartFileUploader.upload(file, "upload");  // 存到 file/upload/ 底下
        ObjectData obj = new ObjectData();
        obj.add("url", result.getUrl());
        return ResponseEntityBuilder.success().message("上傳成功").data(obj).build();
    }
}