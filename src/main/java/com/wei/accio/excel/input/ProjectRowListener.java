package com.wei.accio.excel.input;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProjectRowListener implements ReadListener<ProjectRow> {

    @Getter
    private List<ProjectRow> projects = new ArrayList<>();

    @Override
    public void invoke(ProjectRow data, AnalysisContext analysisContext) {
        log.info("Get New Row:{}", JSON.toJSONString(data));
        projects.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("Finished reading all data.");
    }
}
