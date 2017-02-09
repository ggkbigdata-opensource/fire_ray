package org.fire.platform.modules.notice.bean;

import org.fire.platform.modules.notice.domain.Notice;
import org.fire.platform.modules.notice.domain.NoticeAttachment;

import java.util.List;

/**
 * Created by Max on 2017/1/11.
 */
public class NoticeAttachmentsBean {

    private Notice notice;

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public List<NoticeAttachment> getNoticeAttachments() {
        return noticeAttachments;
    }

    public void setNoticeAttachments(List<NoticeAttachment> noticeAttachments) {
        this.noticeAttachments = noticeAttachments;
    }

    private List<NoticeAttachment> noticeAttachments;
}
