package com.chenshuyusc.getStatistic;

import java.util.List;

/**
 * 首页表格映射成的信息
 */
public class Data {
    private String name;
    private String type;
    private String geoExtent;
    private String geoLocation;
    private String classfication;
    private String layer;
    private String date;
    private String graphml;
    private String provenance;

    // 这两个属性都只能通过第二次爬虫获得
    private List<Edge> edges;
    private int node;

    public Data(){
    }

    public Data(String name, String type, String geoExtent, String geoLocation, String classfication, String layer, String date, String graphml, String provenance) {
        this.name = name;
        this.type = type;
        this.geoExtent = geoExtent;
        this.geoLocation = geoLocation;
        this.classfication = classfication;
        this.layer = layer;
        this.date = date;
        this.graphml = graphml;
        this.provenance = provenance;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getGeoExtent() {
        return geoExtent;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public String getClassfication() {
        return classfication;
    }

    public String getLayer() {
        return layer;
    }

    public String getDate() {
        return date;
    }

    public String getGraphml() {
        return graphml;
    }

    public String getProvenance() {
        return provenance;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getNode() {
        return node;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGeoExtent(String geoExtent) {
        this.geoExtent = geoExtent;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public void setClassfication(String classfication) {
        this.classfication = classfication;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGraphml(String graphml) {
        this.graphml = graphml;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
