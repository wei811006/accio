package com.wei.accio.excel.input;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmployeeRowListener  implements ReadListener<EmployeeRow> {

    @Getter
    private List<EmployeeRow> employees = new ArrayList<>();

    @Override
    public void invoke(EmployeeRow data, AnalysisContext analysisContext) {
        log.info("Get Employee New Row:{}", JSON.toJSONString(data));
        employees.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("Finished reading all Employee data.");
    }
}
