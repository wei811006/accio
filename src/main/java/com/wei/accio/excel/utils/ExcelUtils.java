package com.wei.accio.excel.utils;

import com.alibaba.excel.metadata.data.FormulaData;
import com.alibaba.excel.metadata.data.WriteCellData;

public class ExcelUtils {

    public static String convertToExcelColumn(int number) {
        StringBuilder column = new StringBuilder();

        while (number > 0) {
            int remainder = (number - 1) % 26;
            column.insert(0, (char)('A' + remainder));
            number = (number - 1) / 26;
        }

        return column.toString();
    }

    public static WriteCellData<String> transToCellData(String value) {

        FormulaData formulaData = new FormulaData();
        formulaData.setFormulaValue(value);

        WriteCellData<String> formula = new WriteCellData<>();
        formula.setFormulaData(formulaData);

        return formula;
    }
}
