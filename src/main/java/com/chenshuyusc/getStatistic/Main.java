package com.chenshuyusc.getStatistic;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 先获得所有基本信息
        String url = "http://www.topology-zoo.org/dataset.html";
        List<Data> datas = GetHtml.GetDatas(url);


        for (Data data : datas) {
            GetHtml.getMsg(data);
            System.out.println(data.getName() + "_" + data.getDate() + ":" + data.getGraphml() + "(" + data.getNode() + ")");
        }

        try {
            WriteNodes.write(datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
