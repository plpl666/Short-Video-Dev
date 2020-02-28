package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "search_records")
public class SearchRecords {
    /**
     * 编号
     */
    @Id
    private String id;

    /**
     * 热搜词
     */
    private String content;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取热搜词
     *
     * @return content - 热搜词
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置热搜词
     *
     * @param content 热搜词
     */
    public void setContent(String content) {
        this.content = content;
    }
}