package com.aixbox.usercenter.mapper;

import com.aixbox.usercenter.model.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> findPermsByUserId(Long id);
}