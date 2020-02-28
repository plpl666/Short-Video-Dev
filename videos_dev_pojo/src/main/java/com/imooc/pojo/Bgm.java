package com.imooc.pojo;

import javax.persistence.*;

public class Bgm {
    /**
     * 编号
     */
    @Id
    private String id;

    /**
     * 作者
     */
    private String author;

    /**
     * 名称
     */
    private String name;

    /**
     * 背景音乐路径
     */
    private String path;

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
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取背景音乐路径
     *
     * @return path - 背景音乐路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置背景音乐路径
     *
     * @param path 背景音乐路径
     */
    public void setPath(String path) {
        this.path = path;
    }
}