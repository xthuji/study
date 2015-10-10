package com.hj.test.excel;

import java.util.Map;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

public class ExcelTools {
    public static void addCells(WritableSheet sheet, String[] values, int row,Map<String,WritableCellFormat> format) throws Exception {
        
        for(int i = 0, iLen = values.length; i < iLen; ++i) {
            Label label;
            if(format != null && format.get(i+"") != null) {
                label = new Label(i, row, values[i], format.get(i+""));
            } else {
                label = new Label(i, row, values[i]);
            }
            sheet.addCell(label);
        }
    }
    /**
     * 合并单元格
     * @param ws WritableSheet
     * @param leftColumnIndex 最左上的列索引
     * @param leftRowIndex 最左上的行索引
     * @param rightColumnIndex 最右下的列索引
     * @param rightRowIndex 最右下的航索引
     * @param content 内容
     * @param fontSize 字体大小
     * @throws WriteException 
     */
    
    public static void mergeCell(WritableSheet ws,int leftColumnIndex,int leftRowIndex,int rightColumnIndex,int rightRowIndex,String content,int fontSize) throws WriteException{
        WritableFont wf_merge = new WritableFont(WritableFont.ARIAL, fontSize,WritableFont.NO_BOLD, false);
        WritableCellFormat wff_merge = new WritableCellFormat(wf_merge);
        jxl.write.Label label = new Label(leftColumnIndex, leftRowIndex, content, wff_merge);
        WritableCellFormat cellFormat = new WritableCellFormat(wf_merge);
        cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
        label.setCellFormat(cellFormat);
        ws.addCell(label);
        // 合并单元格
        ws.mergeCells(leftColumnIndex, leftRowIndex, rightColumnIndex, rightRowIndex);
    }
}

