package com.lemon.pageobject;

import org.openqa.selenium.By;

import com.lemon.common.BasePage;

public class LoginPage extends BasePage {
	// 手机号码输入框的元素定位信息
	private By mobilephoneBy = By.cssSelector("input[placeholder='手机号']");
	// 密码输入框的元素定位信息
	private By passwordBy = By.cssSelector("input[placeholder='密码']");
	// 登录按钮的元素定位信息
	private By loginButtonBy = By.xpath("//button[text()='登录']");

	// 提示信息：此账号没有经过授权，请联系管理员!
	private By tipsShowBy = By.className("layui-layer-content");
	// 提示信息：请输入正确的手机号码
	private By tipsInputBy = By.className("form-error-info");

	// 输入手机号
	public void inputMobilephone(String datas) {
		type(mobilephoneBy, datas);
	}

	// 输入密码
	public void inputPassword(String datas) {
		type(passwordBy, datas);
	}

	// 点击登录
	public void clickLogin() {
		click(loginButtonBy);
	}

	// 获取展示的提示信息

	public String getTipsShowText() {
		return getElementText(tipsShowBy);
	}

	// 获取输入的提示信息
	public String getTipsInputText() {
		return getElementText(tipsInputBy);
	}

	// 获取手机号的值
	public String getMobilephoneValue() {
		return getElementValue(mobilephoneBy, "value");
	}
}
