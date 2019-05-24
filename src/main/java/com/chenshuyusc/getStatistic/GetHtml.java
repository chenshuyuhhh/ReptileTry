package com.chenshuyusc.getStatistic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 爬虫获取数据
 */
public class GetHtml {

    public GetHtml() {
    }

    /**
     * 根据 url 获得所有的 graphml 链接
     *
     * @param url
     * @return
     */
    public static List<Data> GetDatas(String url) {
        List<Data> dataList = new ArrayList<Data>();
        try {
            Document document = Jsoup.connect(url)
                    .timeout(10000)
                    .get();
            Element table = document.select("table").get(1).select("tbody").first();

            // 将表格切分成行
            Elements records = table.select("tr");
            for (Element record : records) {
                // 找出每行中的每一列
                Elements attributes = record.select("td");

                // 链接的首地址
                StringBuffer link = new StringBuffer("http://www.topology-zoo.org/");

                // 找出 href 属性的所有元素
                Elements links = attributes.get(7).select("a[href]");
                for (Element ele : links) {
                    String linkText = ele.attr("href");

                    String graphml = ".*\\.graphml";
                    // 正则表达式，找出 xml 图的链接
                    if (Pattern.matches(graphml, linkText)) {
                        link.append(linkText);
                    }
                }
                Data data = new Data(attributes.get(0).text(),
                        attributes.get(1).text(),
                        attributes.get(2).text(),
                        attributes.get(3).text(),
                        attributes.get(4).text(),
                        attributes.get(5).text(),
                        attributes.get(6).text(),
                        link.toString(),
                        attributes.get(9).text());
                dataList.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return dataList;
        }
    }

    /**
     * 获得结点对
     *
     * @param data
     * @return
     */
    public static void getMsg(Data data) {
        String url = data.getGraphml();

        // 边
        List<Edge> edges = new ArrayList<Edge>();
        // 最大结点数
        try {
            // 获取 document 对象
            Document document = Jsoup.connect(url)
                    .timeout(10000)
                    .get();

            // 统计边的信息
            String[] edgesTemp = document.html().trim().split("<edge");
            for (int i = 1; i < edgesTemp.length; i++) {
                String temp = edgesTemp[i].substring(9);
                String[] nodes = temp.split("\" target=\"");
                String source = nodes[0];
                String target = nodes[1].split("\"> \n" +
                        "     <data key=\"")[0];
               // System.out.println("source:" + source + "   target:" + target);
                // 因为从0开始，但是在表中是从1开始，所以要加1
                edges.add(new Edge(Integer.parseInt(source)+1, Integer.parseInt(target)+1));
            }
            data.setEdges(edges);

            // 结点数，注意从 0 开始数
            String[] nodesTemp = document.html().split("<node id=\"");
            ArrayList<Integer> nodes = new ArrayList<Integer>();
            for (int i = 1; i < nodesTemp.length; i++) {
                nodes.add(Integer.parseInt(nodesTemp[i].split("\"> \n" +
                        "     <data key=\"")[0]));
            }
            data.setNode(nodes.get(nodes.size()-1)+1);
//            node = Collections.max(nodes);
//            data.setNode(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
