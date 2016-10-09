package com.itheima.zhbj74.domain;

import java.util.List;

/**
 *
 */
public class NewsMenu {

    private Integer retcode;
    private List<Integer> extend;
    private List<NewsMenuData> data;

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public List<Integer> getExtend() {
        return extend;
    }

    public void setExtend(List<Integer> extend) {
        this.extend = extend;
    }

    public List<NewsMenuData> getData() {
        return data;
    }

    public void setData(List<NewsMenuData> data) {
        this.data = data;
    }

    //侧边栏菜单对象
    public class NewsMenuData{
        private Integer id;
        private String title;
        private Integer type;

        private List<NewsTabData> children;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public List<NewsTabData> getChildren() {
            return children;
        }

        public void setChildren(List<NewsTabData> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "NewsMenuData{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", children=" + children +
                    '}';
        }
    }

    //页签的对象
    public class NewsTabData{
        private Integer id;
        private String title;
        private Integer type;
        private String url;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "NewsTabData{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsMenu{" +
                "retcode=" + retcode +
                ", extend=" + extend +
                ", data=" + data +
                '}';
    }
}
