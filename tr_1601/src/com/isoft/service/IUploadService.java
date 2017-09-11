package com.isoft.service;

import com.isoft.model.Tattenchment;

public interface IUploadService {
public boolean uploadFile(Tattenchment att);//添加一个图片
public boolean updateFile(Tattenchment att);//更新图片的属性
public boolean deleteFile(Tattenchment att);//删除图片
public boolean checkFile(String tid);//检查该人是否有对应的图片
public String getAllAttJSON(String tid);//找到一个人对应图片
}
