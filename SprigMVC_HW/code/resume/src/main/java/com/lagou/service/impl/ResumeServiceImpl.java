package com.lagou.service.impl;

import com.lagou.dao.ResumeDao;
import com.lagou.pojo.Resume;
import com.lagou.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Auther: xuejiangtao
 * @Date: 2020-04-20 19:25
 * @Description: 简历类service实现层接口
 */
@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    ResumeDao resumeDao;

    /**
     * 新增实体对象
     *
     * @param resume 实体对象
     * @throws Exception
     */
    @Override
    public void add(Resume resume) throws Exception {
        resumeDao.save(resume);
    }

    /**
     * 根据id删除数据
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteById(Long id) throws Exception {
        Optional<Resume> byId = resumeDao.findById(id);
        resumeDao.delete(byId.get());
    }

    /**
     * 编辑数据
     *
     * @param resume
     * @throws Exception
     */
    @Override
    public void edit(Resume resume) throws Exception {
        resumeDao.save(resume);
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Resume queryById(Long id) throws Exception {
        Resume resumeList = resumeDao.findById(id).orElseGet(null);
        return resumeList;
    }

    /**
     * 查询所有数据
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Resume> queryAll() throws Exception {
        List<Resume> resumeList = resumeDao.findAll();
        return resumeList;
    }

}
