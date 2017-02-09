package org.fire.platform.modules.report.bean;

import java.util.List;

/**
 * 包含一份检测报告的所有信息
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/11/30 030
 * Time: 15:09
 */
public class ReportInfoBean extends CheckReportInfoBean{

    private List<CheckItemResultBean> itemResultList;
    private List<CheckItemResultStatisBean> itemResultStatisList;
    private List<CheckItemUnqualifiedBean> itemUnqualifiedList;

    public List<CheckItemResultBean> getItemResultList() {
        return itemResultList;
    }

    public void setItemResultList(List<CheckItemResultBean> itemResultList) {
        this.itemResultList = itemResultList;
    }

    public List<CheckItemResultStatisBean> getItemResultStatisList() {
        return itemResultStatisList;
    }

    public void setItemResultStatisList(List<CheckItemResultStatisBean> itemResultStatisList) {
        this.itemResultStatisList = itemResultStatisList;
    }

    public List<CheckItemUnqualifiedBean> getItemUnqualifiedList() {
        return itemUnqualifiedList;
    }

    public void setItemUnqualifiedList(List<CheckItemUnqualifiedBean> itemUnqualifiedList) {
        this.itemUnqualifiedList = itemUnqualifiedList;
    }
}
