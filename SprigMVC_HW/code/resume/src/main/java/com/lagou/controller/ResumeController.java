package com.lagou.controller;

import com.lagou.pojo.Resume;
import com.lagou.service.impl.ResumeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: xuejiangtao
 * @Date: 2020-04-20 19:05
 * @Description: 业务处理controller
 */
@Controller
public class ResumeController {

    @Autowired
    ResumeServiceImpl resumeService;

    /**
     * 跳转到新增页面
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd() {
        return "add";
    }

    /**
     * 新增数据
     *
     * @param resume
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(Resume resume, ModelAndView model) throws Exception {
        resumeService.add(resume);
        List<Resume> resumeList = resumeService.queryAll();
        model.addObject("resumeList", resumeList);
        model.setViewName("resumeList");
        return model;

    }

    /**
     * 根据id删除数据
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public ModelAndView deleteById(Long id, ModelAndView model) throws Exception {
        resumeService.deleteById(id);
        List<Resume> resumeList = resumeService.queryAll();
        model.addObject("resumeList", resumeList);
        model.setViewName("resumeList");
        return model;
    }

    /**
     * 编辑数据
     *
     * @param resume
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(Resume resume, ModelAndView model) throws Exception {
        resumeService.edit(resume);
        List<Resume> resumeList = resumeService.queryAll();
        model.addObject("resumeList", resumeList);
        model.setViewName("resumeList");
        return model;
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public ModelAndView queryById(Long id, ModelAndView model) throws Exception {
        Resume resume1 = resumeService.queryById(id);
        model.addObject("resume", resume1);
        model.setViewName("edit");
        return model;
    }

    /**
     * 查询所有数据
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public ModelAndView allList(ModelAndView model) throws Exception {
        List<Resume> resumeList = resumeService.queryAll();
        model.addObject("resumeList", resumeList);
        model.setViewName("resumeList");
        return model;
    }

}
