package com.maple.setter.entity;

/**
 * @author 杨锋
 * @date 2022/10/28 23:19
 * desc:
 */


public class Person {

    private Header header;

    private Face face;

    @Override
    public String toString() {
        return "Person{" +
                "header=" + header +
                ", face=" + face +
                '}';
    }

    public void setAbc(Header header) {
        this.header = header;
    }

    public void setFace(Face face) {
        this.face = face;
    }
}
