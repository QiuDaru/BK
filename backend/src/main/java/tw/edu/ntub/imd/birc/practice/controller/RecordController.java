package tw.edu.ntub.imd.birc.practice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.practice.bean.RecordsBean;
import tw.edu.ntub.imd.birc.practice.service.RecordService;
import tw.edu.ntub.imd.birc.practice.util.http.BindingResultUtils;
import tw.edu.ntub.imd.birc.practice.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.practice.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.practice.util.json.object.ObjectData;

import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/borrows")
public class RecordController {
    private final RecordService recordService;

    @PostMapping(path = "")                     // 借用
    public ResponseEntity<String> borrow(@Valid @RequestBody RecordsBean bean,
                                         BindingResult bindingResult) {
        BindingResultUtils.validate(bindingResult);
        recordService.borrow(bean);
        return ResponseEntityBuilder.success().message("借用成功").build();
    }

    @PatchMapping(path = "/{rentId}/return")
    public ResponseEntity<String> returnItem(@PathVariable Integer rentId,
                                             @RequestBody(required = false) RecordsBean bean) {
        String returnPhotoLink = (bean != null) ? bean.getReturnPhotoLink() : null;
        recordService.returnItem(rentId, returnPhotoLink);
        return ResponseEntityBuilder.success().message("歸還成功").build();
    }

    @GetMapping(path = "/overdue")              // 逾期清單
    public ResponseEntity<String> overdue() {
        ArrayData arrayData = new ArrayData();
        for (RecordsBean b : recordService.searchOverdue()) {
            ObjectData o = arrayData.addObject();
            o.add("recordsId", b.getId());
            o.add("rentId", b.getRentId());
            o.add("borrowUserId", b.getBorrowUserId());
            o.add("lendUserId", b.getLendUserId());
            o.add("returnDate", b.getReturnDate());
        }
        return ResponseEntityBuilder.success().message("查詢成功").data(arrayData).build();
    }

    @GetMapping(path = "/stats")                // 借用統計（SDG / 里長數據）
    public ResponseEntity<String> stats() {
        Map<String, Object> s = recordService.statistics();
        ObjectData o = new ObjectData();
        for (Map.Entry<String, Object> e : s.entrySet()) {
            o.add(e.getKey(), ((Number) e.getValue()).longValue());   // 明確轉 long，消除多載歧義
        }
        return ResponseEntityBuilder.success().message("查詢成功").data(o).build();
    }
}