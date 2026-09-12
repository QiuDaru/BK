package tw.edu.ntub.imd.birc.practice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.practice.bean.MenuBean;
import tw.edu.ntub.imd.birc.practice.service.MenuService;
import tw.edu.ntub.imd.birc.practice.util.http.BindingResultUtils;
import tw.edu.ntub.imd.birc.practice.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.practice.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.practice.util.json.object.ObjectData;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/menus")
public class MenuController {
    private final MenuService menuService;

    @GetMapping(path = "")
    public ResponseEntity<String> searchMenus() {
        ArrayData arrayData = new ArrayData();
        for (MenuBean menuBean : menuService.searchAll()) {
            ObjectData objectData = arrayData.addObject();
            objectData.add("id", menuBean.getId());
//            objectData.add("item_id", menuBean.getItem_id());
            objectData.add("item_name", menuBean.getItem().getName());
            objectData.add("amount", menuBean.getAmount());
            objectData.add("total", menuBean.getTotal());
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(arrayData)
                .build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getMenu(@PathVariable Integer id) {
        MenuBean menuBean = menuService.getById(id)
                .orElseThrow(() -> new RuntimeException("查無此訂單"));

        ObjectData objectData = new ObjectData();
        objectData.add("id", menuBean.getId());
//        objectData.add("item_id", menuBean.getItem_id());
        objectData.add("item_name", menuBean.getItem().getName());
        objectData.add("amount", menuBean.getAmount());
        objectData.add("total", menuBean.getTotal());

        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(objectData)
                .build();
    }

    @PostMapping(path = "")
    public ResponseEntity<String> createMenu(@Valid @RequestBody MenuBean menuBean,
                                             BindingResult bindingResult) {
        BindingResultUtils.validate(bindingResult);
        menuService.save(menuBean);
        return ResponseEntityBuilder.success()
                .message("新增成功")
                .build();
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<String> updateMenu(@PathVariable Integer id,
                                             @RequestBody MenuBean menuBean,
                                             BindingResult bindingResult ) {
        BindingResultUtils.validate(bindingResult);
        menuService.update(id, menuBean);
        return ResponseEntityBuilder.success()
                .message("更新成功")
                .build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable Integer id) {
        menuService.delete(id);
        return ResponseEntityBuilder.success()
                .message("刪除成功")
                .build();
    }
}