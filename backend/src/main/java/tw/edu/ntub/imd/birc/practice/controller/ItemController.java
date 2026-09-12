package tw.edu.ntub.imd.birc.practice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.practice.bean.ItemBean;
import tw.edu.ntub.imd.birc.practice.service.ItemService;
import tw.edu.ntub.imd.birc.practice.util.http.BindingResultUtils;
import tw.edu.ntub.imd.birc.practice.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.practice.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.practice.util.json.object.ObjectData;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping(path = "")
    public ResponseEntity<String> searchItem() {
        ArrayData arrayData = new ArrayData();
        for (ItemBean itemBean : itemService.searchAll()) {
            ObjectData objectData = arrayData.addObject();
            objectData.add("id", itemBean.getId());
            objectData.add("name", itemBean.getName());
            objectData.add("price", itemBean.getPrice());
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getItem(@PathVariable Integer id) {
        ItemBean itemBean = itemService.getById(id)
                .orElseThrow(() -> new RuntimeException("查無此品項"));

        ObjectData objectData = new ObjectData();
        objectData.add("id", itemBean.getId());
        objectData.add("name", itemBean.getName());
        objectData.add("price", itemBean.getPrice());

        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(objectData)
                .build();
    }

    @PostMapping(path = "")
    public ResponseEntity<String> createItem(@Valid @RequestBody ItemBean itemBean,
                                             BindingResult bindingResult) {
        BindingResultUtils.validate(bindingResult);
        itemService.save(itemBean);
        return ResponseEntityBuilder.success()
                .message("新增成功")
                .build();
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Integer id,
                                             @Valid @RequestBody ItemBean itemBean,
                                             BindingResult bindingResult ) {
        BindingResultUtils.validate(bindingResult);
        itemService.update(id, itemBean);
        return ResponseEntityBuilder.success()
                .message("更新成功")
                .build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Integer id) {
        itemService.delete(id);
        return ResponseEntityBuilder.success()
                .message("刪除成功")
                .build();
    }
}