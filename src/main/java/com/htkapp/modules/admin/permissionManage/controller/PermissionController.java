package com.htkapp.modules.admin.permissionManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by terabithia on 12/31/17.
 */
@Controller
@RequestMapping(value = "/admin/permission")
public class PermissionController {

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public String page() {
        return "";
    }
}
