package org.fire.platform.modules.notice.domain;

import org.fire.platform.common.base.BaseEntity;

/**
 * No modifying
 * Company: Scho Co. Ltd
 *
 * @author ZKT
 * @date 2017-1-5 14:08:21
 */

@SuppressWarnings("serial")
public class NoticeAttachment extends BaseEntity {
    // Fields
    private Long id;
    private Long noticeId;
    private String originalName;
    private String fileSize;
    private String uuid;
    private Integer state;

    public NoticeAttachment() {
        this.clear();
    }

    public NoticeAttachment(Long id) {
        this();

        this.id = id;
    }

    public void clear() {
        id = null;
        noticeId = null;
        originalName = null;
        fileSize = null;
        uuid = null;
        state = null;
    }

    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("NoticeAttachment=[");
        builder.append("id=" + id + ",");
        builder.append("noticeId=" + noticeId + ",");
        builder.append("originalName=" + originalName + ",");
        builder.append("fileSize=" + fileSize + ",");
        builder.append("uuid=" + uuid + ",");
        builder.append("state=" + state + ",");
        builder.append("]");
        return builder.toString();
    }


}
