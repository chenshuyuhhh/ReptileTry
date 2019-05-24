package com.chenshuyusc.getStatistic;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 根据获得数据写入表格中
 */
public class WriteNodes {
    public static void write(List<Data> datas) throws IOException {
        for (Data data : datas) {
            writeOne(data);
        }
    }

    /**
     * 一个
     *
     * @param data
     * @throws IOException
     */
    private static void writeOne(Data data) throws IOException {

        String filename = "datas/" + data.getName() + "_" + data.getNode() + "_" + data.getDate() + ".xlsx";
        FileOutputStream out = new FileOutputStream(filename);

        Workbook wb = new XSSFWorkbook(); // 定义一个新的工作簿
        Sheet sheet = wb.createSheet("Sheet1");  // 创建第一个Sheet页

        // node 也是从0 开始的, 并且第0个位置被控制器占领
        // 初始化第一行
        Row row = sheet.createRow(0);//创建第一行
        for (int i = 0; i <= data.getNode(); i++) {
            if (i > 255) {
                row.createCell(i).setCellValue(i);//创建第一行的第i个单元格
            } else {
                row.createCell(i).setCellValue(i);//创建第一行的第i个单元格
            }
        }

//        // 初始化第一列和第二列
//        for (int i = 1; i <= data.getNode(); i++) {
//            // 几列几行
//            Row rowtemp = sheet.createRow(i);
//            rowtemp.createCell(0).setCellValue(i);
//            rowtemp.createCell(1).setCellValue(0);
//        }

        Map<Integer, List<Integer>> map = processEdge(data.getEdges());

        Iterator<Map.Entry<Integer, List<Integer>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            int source = (Integer) entry.getKey();// 已加1
            // 先为这个源创建一行
            Row rowTemp = sheet.createRow(source);
            List<Integer> targets = (List<Integer>) entry.getValue();
            rowTemp.createCell(0).setCellValue(source);
            rowTemp.createCell(1).setCellValue(0);
            Collections.sort(targets);
            for (int i = 0; i < targets.size(); i++) {
                // 前面两列已经被占了
                rowTemp.createCell(i + 2).setCellValue(targets.get(i));
            }
        }
        wb.write(out);
        out.close();
    }

    /**
     * 根据边的信息构造邻接矩阵
     *
     * @return
     */
    private static Map<Integer, List<Integer>> processEdge(List<Edge> edges) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (Edge edge : edges) {
            // 还没有存过这个点的邻接点
            if (map.get(edge.getSource()) == null) {
                List<Integer> strs = new ArrayList<Integer>() {
                };
                strs.add(edge.getTarget());
                map.put(edge.getSource(), strs);
            } else {
                // 存过这个点的邻接点
                // 取出原来的
                List<Integer> strs = map.get(edge.getSource());
                // 加入补充
                strs.add(edge.getTarget());
                map.put(edge.getSource(), strs);
            }

            // 反过来做同样的操作
            if (map.get(edge.getTarget()) == null) {
                List<Integer> strs = new ArrayList<Integer>() {
                };
                strs.add(edge.getSource());
                map.put(edge.getTarget(), strs);
            } else {
                // 存过这个点的邻接点
                // 取出原来的
                List<Integer> strs = map.get(edge.getTarget());
                // 加入补充
                strs.add(edge.getSource());
                map.put(edge.getTarget(), strs);
            }
        }
        return map;
    }

}
