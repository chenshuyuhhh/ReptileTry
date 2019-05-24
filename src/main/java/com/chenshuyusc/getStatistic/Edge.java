package com.chenshuyusc.getStatistic;

public class Edge {
    private int source;
    private int target;

    public Edge(){

    }

    public Edge(int source,int target){
        this.source = source;
        this.target = target;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }
}

