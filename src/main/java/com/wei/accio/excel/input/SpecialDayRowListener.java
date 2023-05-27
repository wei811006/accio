package com.wei.accio.excel.input;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SpecialDayRowListener implements ReadListener<SpecialDayRow> {

    @Getter
    private List<SpecialDayRow> days = new ArrayList<>();

    @Override
    public void invoke(SpecialDayRow data, AnalysisContext analysisContext) {
        log.info("Get Special Day New Row:{}", JSON.toJSONString(data));
        days.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("Finished reading all Special Day data.");
    }

}
