package com.lagou.service;

import com.lagou.pojo.Resume;

import java.util.List;

/**
 * @Auther: xuejiangtao
 * @Date: 2020-04-20 19:25
 * @Description: 简历类service层接口
 */
public interface ResumeService {
    /**
     * 新增实体对象
     *
     * @param resume 实体对象
     * @throws Exception
     */
    void add(Resume resume) throws Exception;

    /**
     * 根据id删除数据
     *
     * @param id
     * @throws Exception
     */
    void deleteById(Long id) throws Exception;

    /**
     * 编辑数据
     *
     * @param resume
     * @throws Exception
     */
    void edit(Resume resume) throws Exception;

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    Resume queryById(Long id) throws Exception;

    /**
     * 查询所有数据
     *
     * @return
     * @throws Exception
     */
    List<Resume> queryAll() throws Exception;

}
