package tw.edu.ntub.imd.birc.practice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.practice.bean.RentBean;
import tw.edu.ntub.imd.birc.practice.exception.ResourceNotFoundException;
import tw.edu.ntub.imd.birc.practice.service.RentService;
import tw.edu.ntub.imd.birc.practice.util.http.BindingResultUtils;
import tw.edu.ntub.imd.birc.practice.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.practice.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.practice.util.json.object.ObjectData;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/rents")
public class RentController {
    private final RentService rentService;

    @GetMapping               // 瀏覽：只回可借
    public ResponseEntity<String> searchRent() {
        ArrayData arrayData = new ArrayData();
        for (RentBean bean : rentService.searchAvailable()) {
            ObjectData obj = arrayData.addObject();
            obj.add("id", bean.getId());
            obj.add("item", bean.getItem());
            obj.add("categoryName", bean.getCategoryName());
            obj.add("photoLink", bean.getPhotoLink());
            obj.add("remark", bean.getRemark());
            obj.add("ownerId", bean.getOwnerId());
            obj.add("ownerName", bean.getOwnerName());
        }
        return ResponseEntityBuilder.success().message("查詢成功").data(arrayData).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getRent(@PathVariable Integer id) {
        RentBean bean = rentService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("查無此物品"));
        ObjectData obj = new ObjectData();
        obj.add("id", bean.getId());
        obj.add("item", bean.getItem());
        obj.add("categoryName", bean.getCategoryName());
        obj.add("photoLink", bean.getPhotoLink());
        obj.add("remark", bean.getRemark());
        obj.add("rentEnable", bean.getRentEnable());
        obj.add("ownerId", bean.getOwnerId());
        obj.add("ownerName", bean.getOwnerName());
        return ResponseEntityBuilder.success().message("查詢成功").data(obj).build();
    }

    @PostMapping            // 上架
    public ResponseEntity<String> createRent(@Valid @RequestBody RentBean bean,
                                             BindingResult bindingResult) {
        BindingResultUtils.validate(bindingResult);
        rentService.save(bean);
        return ResponseEntityBuilder.success().message("上架成功").build();
    }

    @PatchMapping(path = "/{id}")       // 編輯
    public ResponseEntity<String> updateRent(@PathVariable Integer id,
                                             @RequestBody RentBean bean) {
        rentService.update(id, bean);
        return ResponseEntityBuilder.success().message("更新成功").build();
    }

    @PatchMapping(path = "/{id}/enable") // 上/下架
    public ResponseEntity<String> updateEnable(@PathVariable Integer id,
                                               @RequestParam boolean enable) {
        rentService.updateEnable(id, enable);
        return ResponseEntityBuilder.success().message(enable ? "已上架" : "已下架").build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteRent(@PathVariable Integer id) {
        rentService.delete(id);
        return ResponseEntityBuilder.success().message("刪除成功").build();
    }
}