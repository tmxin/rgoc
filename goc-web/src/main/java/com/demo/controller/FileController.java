package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.mymodule.domain.GocFile;
import cn.com.mymodule.domin.Page;
import cn.com.mymodule.serviceapi.GocFileService;
 
@Controller
public class FileController {
	@Autowired
	GocFileService gocFileService;
	Logger logger = LoggerFactory.getLogger(FileController.class);
	
	/**
	 * 获取授权
	 */
	@ResponseBody
	@RequestMapping(value="/file/list", method = RequestMethod.GET)
    public Page<GocFile> getFileList(int pageindex,int pagesize){
		Page<GocFile> files = gocFileService.findListByPage(null, pageindex, pagesize);
		return files;
    }
	
	/**
	 * 获取授权
	 */
	@ResponseBody
	@RequestMapping(value="/file/res", method = RequestMethod.GET)
    public GocFile getFileByNum(String resNum){
		GocFile file = gocFileService.findByResNum(null, resNum);//
		return file;
    }
}