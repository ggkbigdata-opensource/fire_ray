package org.fire.platform.modules.notice.domain;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.fire.platform.common.base.BaseEntity;

/**
 * No modifying
 * Company: Scho Co. Ltd
 *
 * @author ZKT
 * @date 2017-1-5 14:08:21
 */

@SuppressWarnings("serial")
public class Notice extends BaseEntity {
    // Fields
    private Long id;
    private String uuid;
    private String title;
    private String content;
    private String url;
    private Long publisher;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+08:00"
    )
    private Date pubTime;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+08:00"
    )
    private Date createdTime;
    private Integer state;
    private Integer enabled;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+08:00"
    )
    private Date modTime;

    public Notice() {
        this.clear();
    }

    public Notice(Long id) {
        this();

        this.id = id;
    }

    public void clear() {
        id = null;
        uuid = null;
        title = null;
        content = null;
        url = null;
        publisher = null;
        pubTime = null;
        createdTime = null;
        state = null;
        enabled = null;
        modTime = null;
    }

    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getPublisher() {
        return publisher;
    }

    public void setPublisher(Long publisher) {
        this.publisher = publisher;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Notice=[");
        builder.append("id=" + id + ",");
        builder.append("uuid=" + uuid + ",");
        builder.append("title=" + title + ",");
        builder.append("content=" + content + ",");
        builder.append("url=" + url + ",");
        builder.append("publisher=" + publisher + ",");
        builder.append("pubTime=" + pubTime + ",");
        builder.append("createdTime=" + createdTime + ",");
        builder.append("state=" + state + ",");
        builder.append("enabled=" + enabled + ",");
        builder.append("modTime=" + modTime + ",");
        builder.append("]");
        return builder.toString();
    }


}
