package com.brad.web.webspringboot.oaSystem.service;


import com.brad.web.webspringboot.oaSystem.bean.SysDictionaryBean;

import java.util.List;
import java.util.Map;

public interface SysDicionaryService {

     /**
      * 查询系统选项列表（只查名字和ID和可选值）
      * @return
      */
     List<SysDictionaryBean> getSysDictionaryList(Map<String, Object> paramap, String pageNum, String pageSize);

     /**
      * 查询系统选项列表（查父类）
      * @return
      */
     List<SysDictionaryBean> getSysDictionaryParentList(Map<String, Object> paramap, String pageNum, String pageSize);

     /**
      * 查询系统选项数量
      * @return
      */
     int getOptionParentCount(Map<String, Object> paramap);



}