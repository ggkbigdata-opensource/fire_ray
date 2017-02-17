package org.fire.platform.modules.building.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.fire.platform.App;
import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.domain.Keypart;
import org.fire.platform.modules.building.domain.Management;
import org.fire.platform.modules.building.service.IKeypartService;
import org.fire.platform.modules.building.service.IManagementService;
import org.fire.platform.modules.sys.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Company: Scho Techonlogy Co. Ltd
 * 
 * @author ZKT
 * @date 2016-11-22 10:10:38
 */

@Controller
@RequestMapping("/keypart")
public class KeypartController extends BaseController {

    @Autowired
    private IKeypartService service;

    @Autowired
    private IManagementService managementService;

    private static Logger log = LoggerFactory
            .getLogger(KeypartController.class);

    @RequestMapping(value = "/queryPage")
    @ResponseBody
    public Map<String, Object> queryPage(
            @RequestParam(value = "page", defaultValue = "1") int pageNo,
            @RequestParam(value = "rows", defaultValue = "10") int pageSize,
            HttpSession session,
            @RequestParam(required = false) String keypartName) {
        log.info("query,page={},pageSize={},keypartName={}", pageNo, pageSize,
                keypartName);
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.hasText(keypartName)) {
            map.put("keypartName", keypartName);
        }
        PageInfo<Keypart> page = service.queryPageByMap(map, pageNo, pageSize);
        Map<String, Object> data = new HashMap<String, Object>();
        User user = (User) session.getAttribute(App.USER_SESSION_KEY);
        if (user != null) {
            if (page != null) {
                data.put("total", page.getTotal());
                data.put("rows", page.getList());
            }
        }
        return data;
    }

    @RequestMapping(value = "/queryAll")
    @ResponseBody
    public Map<String, Object> queryAll(HttpSession session,
            @RequestParam(required = false) String keypartName,
            @RequestParam(required = false) String buildingId) {
        log.info("queryAll,buildingId={}", buildingId);
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.hasText(keypartName)) {
            map.put("keypartName", keypartName);
        }
        if (StringUtils.hasText(buildingId)) {
            map.put("buildingId", buildingId);
        }
        List<Keypart> list = service.queryByMap(map);
        for(Keypart part : list) {
            System.out.println("anotherPart=" + part.getAnotherPart());
        }
        Map<String, Object> data = new HashMap<String, Object>();
        User user = (User) session.getAttribute(App.USER_SESSION_KEY);
        if (user != null) {
            if (list != null && list.size() > 0) {
                data.put("total", list.size());
                data.put("rows", list);
            }
        }
        return data;
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public Keypart get(@RequestParam(value = "id") Long id) {
        log.info("get,id={}", id);
        return service.get(id);
    }

    @RequestMapping(value = "/insertData")
    @ResponseBody
    public CommonResult create(Keypart bean, HttpSession session,
            @RequestBody List<Keypart> keyparts) {
        log.info("create,bean={}", bean);
        try {
            User user = (User) session.getAttribute(App.USER_SESSION_KEY);
            if (user == null) {
                log.error("新增失败，请重新登录");
                return CommonResult.fail("新增失败，请重新登录");
            }
            if (keyparts == null || keyparts.size() <= 0) {
                return CommonResult.fail("请填写信息后再保存！");
            }
            for (Keypart keypart : keyparts) {
                Keypart newBean = new Keypart();
                Long buildingId = keypart.getBuildingId();
                Management management = managementService
                        .getByBuildingId(buildingId);
                if (management != null) {
                    newBean.setManagementId(management.getId());
                }
                newBean.setBuildingId(buildingId);
                newBean.setKeypartName(keypart.getKeypartName());
                newBean.setPosition(keypart.getPosition());
                newBean.setArea(keypart.getArea());
                newBean.setFireEquipment(keypart.getFireEquipment());
                newBean.setDutyNum(keypart.getDutyNum());
                newBean.setDiplomaNum(keypart.getDiplomaNum());
                newBean.setFirePumpNum(keypart.getFirePumpNum());
                newBean.setSprayPumpNum(keypart.getSprayPumpNum());
                newBean.setPressurePumpNum(keypart.getPressurePumpNum());
                newBean.setAirTankVolume(keypart.getAirTankVolume());
                newBean.setStorageArea(keypart.getStorageArea());
                newBean.setOilVolume(keypart.getOilVolume());
                newBean.setUserId(user.getUid());
                newBean.setCreateDate(new Date());
                newBean.setModDate(new Date());
                service.insert(newBean);
            }
            return CommonResult.success("新增成功");
        } catch (Exception e) {
            log.error("新增失败", e);
            return CommonResult.fail("新增失败");
        }

    }

    @RequestMapping(value = "/updateData")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResult update(Keypart bean, HttpSession session,
            @RequestBody List<Keypart> keyparts) {
        log.info("update,bean={}", bean);
        try {
            User user = (User) session.getAttribute(App.USER_SESSION_KEY);
            if (user == null) {
                log.error("修改失败，请重新登录");
                return CommonResult.fail("修改失败，请重新登录");
            }
            if (keyparts == null || keyparts.size() <= 0) {
                return CommonResult.fail("请填写信息后再保存！");
            }
            for (Keypart keypart : keyparts) {
                Long buildingId = keypart.getBuildingId();
                Management management = managementService
                        .getByBuildingId(buildingId);
                keypart.setBuildingId(buildingId);
                if (management != null) {
                    keypart.setManagementId(management.getId());
                }
                keypart.setKeypartName(keypart.getKeypartName());
                keypart.setPosition(keypart.getPosition());
                keypart.setArea(keypart.getArea());
                keypart.setFireEquipment(keypart.getFireEquipment());
                keypart.setDutyNum(keypart.getDutyNum());
                keypart.setDiplomaNum(keypart.getDiplomaNum());
                keypart.setFirePumpNum(keypart.getFirePumpNum());
                keypart.setSprayPumpNum(keypart.getSprayPumpNum());
                keypart.setPressurePumpNum(keypart.getPressurePumpNum());
                keypart.setAirTankVolume(keypart.getAirTankVolume());
                keypart.setStorageArea(keypart.getStorageArea());
                keypart.setOilVolume(keypart.getOilVolume());
                keypart.setUserId(user.getUid());
                keypart.setModDate(new Date());
                service.update(keypart);
            }
            return CommonResult.success("修改成功");
        } catch (Exception e) {
            log.error("修改失败", e);
            return CommonResult.fail("修改失败");
        }

    }

    @RequestMapping(value = "/deleteData")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "id") Long id,
            HttpSession session) {
        log.info("delete,id={}", id);
        try {
            User user = (User) session.getAttribute(App.USER_SESSION_KEY);
            if (user == null) {
                log.error("删除失败，请重新登录");
                return CommonResult.fail("删除失败，请重新登录");
            }
            service.delete(id);
            return CommonResult.success("删除成功");
        } catch (Exception e) {
            log.error("删除失败", e);
            return CommonResult.fail("删除失败");
        }
    }

    @RequestMapping(value = "/deleteDataByIds", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delDataByIds(String deleteIds, HttpSession session) {
        log.info("delete,deleteIds={}", deleteIds);
        int ok = 0;
        List<String> deleteIdsList = Arrays.asList(deleteIds.split(","));
        if (deleteIdsList == null || deleteIdsList.size() == 0) {
            return CommonResult.fail("批量删除失败");
        }

        User user = (User) session.getAttribute(App.USER_SESSION_KEY);
        if (user == null) {
            log.error("批量删除失败，请登陆");
            return CommonResult.fail("批量删除失败，请登陆");
        }
        for (String id : deleteIdsList) {
            int res = service.delete(Long.parseLong(id));
            if (res > 0) {
                ok++;
            }
        }
        return CommonResult.success("删除成功记录：" + ok + "条" + "！");
    }

}
