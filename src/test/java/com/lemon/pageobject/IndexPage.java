package com.lemon.pageobject;

import org.openqa.selenium.By;

import com.lemon.common.BasePage;
import com.lemon.common.BaseTest;

public class IndexPage extends BasePage {

	// 我的账户元素定位
	private By myAccountBy = By.xpath("//a[contains(text(),'我的帐户')]");
	// 退出的元素定位
	private By quitBy = By.xpath("//a[text()='退出']");
	// 登录的元素定位
	private By loginBy = By.linkText("登录");
	// “抢投标”的定位表达式
	private String locator = "//span[contains(text(),'" + BaseTest.bidName
			+ "')]/parent::div/parent::a/following-sibling::div/div[3]/div/a";
	// “抢投标”的元素定位
	private By goInvertBy = By.xpath(locator);

	// 滚动到“抢头标”元素
	public void scrollIntoGoInvert() {
		scrollIntoElement(goInvertBy);
	}

	// 点击抢投标元素
	public void clickGoInvert() {
		click(goInvertBy);
	}

	// 查看我的账户是否存在(断言)
	public boolean isMyAccountExit() {
		return isElementExist(myAccountBy);
	}

	// 点击退出
	public void clickQuit() {
		click(quitBy);
	}

	// 点击登录
	public void clickLogin() {
		click(loginBy);
	}

}
