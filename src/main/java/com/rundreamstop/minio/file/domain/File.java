package com.rundreamstop.minio.file.domain;

/**
 * 文件信息
 *
 * @author zhangzihaopk@gmail.com
 */
public class File {
    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SysFile{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
