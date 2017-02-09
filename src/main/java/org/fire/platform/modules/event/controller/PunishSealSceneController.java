package org.fire.platform.modules.event.controller;

import org.fire.platform.common.base.BaseController;
import org.fire.platform.modules.event.service.IPunishSealSceneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Company: Scho Techonlogy Co. Ltd
 *
 * @author ZKT
 * @date 2017-1-18 17:32:53
 */

@Controller
@RequestMapping("/punishSealScene")
public class PunishSealSceneController extends BaseController {

    @Autowired
    private IPunishSealSceneService service;
    private static Logger log = LoggerFactory.getLogger(PunishSealSceneController.class);


}
