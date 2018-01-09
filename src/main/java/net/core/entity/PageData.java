package net.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 表格实体类
 *
 * @author 汤国栋
 * @date 2016年7月14日 下午3:22:39
 */
public class PageData implements Serializable {

    private int draw; // 绘制计数器，接收后原样返回
    private int start; // 第一条数据的起始位置
    private int length;// 每页显示的条数
    private int recordsTotal;// 数据库里总共记录数
    private int recordsFiltered;// 过滤后的记录数
    private List<Object> data;// 数据

    public PageData() {

    }

    public PageData(int draw, int start, int length, int recordsTotal, int recordsFiltered, List<Object> data) {
        this.draw = draw;
        this.start = start;
        this.length = length;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
