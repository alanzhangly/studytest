package com.alan.tpl;

import java.io.Serializable;

/**
 * Created by Ke Zhang on 2017/11/14.
 */
public class FaceImgRequest implements Serializable{
    private String id;
    private String imgData;

    public FaceImgRequest() {
    }

    public FaceImgRequest(String id, String imgData) {
        this.id = id;
        this.imgData = imgData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgData() {
        return imgData;
    }

    public void setImgData(String imgData) {
        this.imgData = imgData;
    }

    @Override
    public String toString() {
        return "{\"id\":\""+id+"\",\"pic\":\"" + imgData + "\"}";
    }

    public static void main(String[] args){
        String s = StringUtils.GetImageStr("d:\\img\\1.jpg");
        FaceImgRequest image = new FaceImgRequest("myImage",s);
        System.out.println(image.toString());
    }
}
