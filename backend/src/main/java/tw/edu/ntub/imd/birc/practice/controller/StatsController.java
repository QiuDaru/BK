package tw.edu.ntub.imd.birc.practice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.VRentCategoryDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.VRentCategory;
import tw.edu.ntub.imd.birc.practice.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.practice.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.practice.util.json.object.ObjectData;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/stats")
public class StatsController {
    private final VRentCategoryDAO vRentCategoryDAO;

    @GetMapping(path = "/category")   // 各分類物品數（長條圖用）
    public ResponseEntity<String> categoryChart() {
        ArrayData arrayData = new ArrayData();
        for (VRentCategory v : vRentCategoryDAO.findAll()) {
            ObjectData o = arrayData.addObject();
            o.add("category", v.getCategory());
            o.add("total", v.getTotal());
        }
        return ResponseEntityBuilder.success().message("查詢成功").data(arrayData).build();
    }
}