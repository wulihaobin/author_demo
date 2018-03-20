package com.nf.web;

import com.nf.dao.EchartsDao;

import com.nf.entity.Echarts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private EchartsDao echartsDao;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("list",echartsDao.findOne(1L));
        return "echarts/index";
    }

    @PostMapping("/data")
    @ResponseBody
    public Echarts data(){
        Echarts echarts=echartsDao.findOne(1L);
        System.out.println(echarts.getShirt());
        return echarts;
    }
}
