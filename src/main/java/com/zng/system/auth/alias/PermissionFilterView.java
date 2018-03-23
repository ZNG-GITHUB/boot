package com.zng.system.auth.alias;

/**
 * Created by John.Zhang on 2018/3/22.
 */
public class PermissionFilterView {

    private Long id;

    private String url;

    private String urlType;

    private Integer perType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public Integer getPerType() {
        return perType;
    }

    public void setPerType(Integer perType) {
        this.perType = perType;
    }
}
