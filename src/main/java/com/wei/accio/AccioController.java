package com.wei.accio;

import com.alibaba.excel.util.MapUtils;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
public class AccioController {

    private AccioService service;

    private AccioController(AccioService accioService) {
        this.service = accioService;
    }

    @PostMapping("/upload")
    public void uploadExcel(
            @RequestParam("file") MultipartFile file,
            @RequestParam("month") String month,
            HttpServletResponse response
    ) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName(month));

        try {
            OutputStream outputStream = response.getOutputStream();
            service.schedule(file, month, outputStream);
        } catch (Exception e) {
            log.error("process failed", e);
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "Failure");
            map.put("message", "Message: " + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }


    private String fileName(String month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmm");
        return "elly_schedule_" + month + "_" + sdf.format(new Date()) + ".xlsx";
    }
}
