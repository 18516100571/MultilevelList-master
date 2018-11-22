package com.demo.multilevellist.test;

/**
 * Created by xulc on 2018/7/27.
 */

public class TestTreePoint {
    private String ID;        // 7241,          //账号id
    private String NNAME; // "用户原因",    //原因名称
    private String PARENTID;   // 0,           //父id     0表示父节点
    private int DISPLAY_ORDER; // 1       //同一个级别的显示顺序
    private boolean isExpand = false;  //是否展开了
    private int layer = 1;//层级
    private boolean hasSubDatas = false;


    public TestTreePoint(String ID, String NNAME, String PARENTID, int DISPLAY_ORDER, boolean isExpand, int layer, boolean hasSubDatas) {
        this.ID = ID;
        this.NNAME = NNAME;
        this.PARENTID = PARENTID;
        this.DISPLAY_ORDER = DISPLAY_ORDER;
        this.isExpand = isExpand;
        this.layer = layer;
        this.hasSubDatas = hasSubDatas;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNNAME() {
        return NNAME;
    }

    public void setNNAME(String NNAME) {
        this.NNAME = NNAME;
    }

    public String getPARENTID() {
        return PARENTID;
    }

    public void setPARENTID(String PARENTID) {
        this.PARENTID = PARENTID;
    }

    public int getDISPLAY_ORDER() {
        return DISPLAY_ORDER;
    }

    public void setDISPLAY_ORDER(int DISPLAY_ORDER) {
        this.DISPLAY_ORDER = DISPLAY_ORDER;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public boolean isHasSubDatas() {
        return hasSubDatas;
    }

    public void setHasSubDatas(boolean hasSubDatas) {
        this.hasSubDatas = hasSubDatas;
    }
}