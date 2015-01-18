package com.supinfo.sup.dao;

import com.supinfo.sup.rest.CategoryRestDao;
import com.supinfo.sup.rest.ProjectRestDao;
import com.supinfo.sup.rest.UserRestDao;



public class DaoFactory {
	public DaoFactory () {}
	public static ProjectRestDao retreiveDaoFactory(){
		ProjectRestDao mPd = new ProjectRestDao();
		return mPd;
}
	public static UserRestDao  retreiveUserDaoFactory(){
		UserRestDao mud = new UserRestDao();
		return mud;
	}
	public static CategoryRestDao retreiveCategoryDaoFactory(){
		CategoryRestDao mcd = new CategoryRestDao();
		return mcd;
	}
}
