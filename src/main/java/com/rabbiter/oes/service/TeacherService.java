package com.rabbiter.oes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.Teacher;

import java.util.List;

public interface TeacherService {

    IPage<Teacher> findAll(Page<Teacher> page);

    public List<Teacher> findAll();

    public Teacher findById(Integer teacherId);

    public int deleteById(Integer teacherId);

    public int update(Teacher teacher);

    public int add(Teacher teacher);
}
