package com.ef.golf.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

public class EsRegions implements Serializable{
    private Integer regionId;

    private Integer pRegionId;

    private String regionPath;

    private Integer regionGrade;
    private String localName;

    private String zipcode;

    private String cod;

    /************以下为非数据库字段*************/
    private List<EsRegions> children;  //子类别
   // private String state;

   public String toJson(){

       return "{" +
               "'regionId':'" + regionId + '\'' +
               ", 'localName':'" + localName + '\'' +
               ", 'pRegionId':'" + pRegionId + '\'' +
               ", 'regionPath':'" + regionPath + '\'' +
               ", 'regionGrade':'" + regionGrade + '\'' +
               ", 'zipcode':'" + zipcode + '\'' +
               ", 'cod':'" + cod + '\'' +
               ", 'children':" + children +
              /* ", 'operations':'" + operations + '\'' +
               ", 'state':'" + state + '\'' +
               ", 'order':'" + order + '\'' +*/

               '}';
   }

    @Override
    public String toString() {
        return "{" +
                "'regionId':'" + regionId + '\'' +
                ", 'localName':'" + localName + '\'' +
                ", 'pRegionId':'" + pRegionId + '\'' +
                ", 'regionPath':'" + regionPath + '\'' +
                ", 'regionGrade':'" + regionGrade + '\'' +
                ", 'zipcode':'" + zipcode + '\'' +
                ", 'cod':'" + cod + '\'' +
                ", 'children':" + children +
              /* ", 'operations':'" + operations + '\'' +
               ", 'state':'" + state + '\'' +
               ", 'order':'" + order + '\'' +*/

                '}';

    }




    public List<EsRegions> getChildren() {
        return children;
    }

    public void setChildren(List<EsRegions> children) {
        this.children = children;
    }

  /*  public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }*/


    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getpRegionId() {
        return pRegionId;
    }

    public void setpRegionId(Integer pRegionId) {
        this.pRegionId = pRegionId;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath == null ? null : regionPath.trim();
    }

    public Integer getRegionGrade() {
        return regionGrade;
    }

    public void setRegionGrade(Integer regionGrade) {
        this.regionGrade = regionGrade;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName == null ? null : localName.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod == null ? null : cod.trim();
    }
}