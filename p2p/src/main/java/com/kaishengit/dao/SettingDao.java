package com.kaishengit.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.kaishengit.entity.Setting;
import com.kaishengit.util.DbHelp;

public class SettingDao {

	public Setting findByName(String key) {
		String sql = "select * from t_setting where `key` = ?";
		return DbHelp.query(sql, new BeanHandler<>(Setting.class), key);
	}

}
