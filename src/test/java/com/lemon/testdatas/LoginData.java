package com.lemon.testdatas;

import org.testng.annotations.DataProvider;

/**
 * @Title: LoginData.java
 * @Description: TODO(描述)
 * @author 歪歪欧巴
 * @date 2020年3月25日
 * @Copyright:湖南省零檬信息技术有限公司. All rights reserved.
 */
public class LoginData {

	/**
	 * 当提示信息是请输入手机号情况下用例数据源
	 * 
	 * @return
	 */
	@DataProvider
	public Object[][] getLoginFailure01Datas() {
		Object[][] datas = { { "15859019266", "Ab%12345", "此账号没有经过授权，请联系管理员!" },
				{ "13323234545", "123456", "帐号或密码错误!" } };
		return datas;
	}

	/**
	 * 1、当输入的手机号码没有注册的情况 2、手机号码输入、密码错误的情况
	 * 
	 * @return
	 */
	@DataProvider
	public Object[][] getLoginFailure02Datas() {
		Object[][] datas = { { "", "Ab%12345", "请输入手机号" }, { "1585901925", "123456", "请输入正确的手机号" },
				{ "158590192534", "12345", "请输入正确的手机号" }, { "1585901925%", "12345", "请输入正确的手机号" },
				{ "13323234545", "", "请输入密码" } };
		return datas;
	}

}
