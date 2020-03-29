package com.lemon.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lemon.common.BaseTest;
import com.lemon.pageobject.IndexPage;
import com.lemon.pageobject.InvertPage;
import com.lemon.pageobject.LoginPage;
import com.lemon.testdatas.InvertData;
import com.lemon.utils.BrowserUtil;
import com.lemon.utils.Constants;

public class Invert extends BaseTest {
	@Parameters({ "browserName" })
	@BeforeMethod
	public void setUp(String browsername) throws InterruptedException {
		// 打开chrome浏览器
		BrowserUtil.openBrowser(browsername);
		// 有一部分的元素被遮挡住了，我们选择通过浏览器最大化来解决
		BrowserUtil.maxBrowser();
		// 输入http://120.78.128.25:8765/Index/login.html
		BrowserUtil.driver.get(Constants.LOGIN_URL);
		// 登录
		// 找到手机号码输入框，输入13323234545
		LoginPage loginPage = new LoginPage();
		loginPage.inputMobilephone(Constants.INVEST_ACCOUNTNAME);
		// 找到密码输入框，输入lemon123456
		loginPage.inputPassword(Constants.INVEST_PASSWORD);
		// 找到登录按钮，点击登录
		loginPage.clickLogin();
	}

	/**
	 * 投资成功
	 */
	@Test(priority = 1)
	public void testInvest_success() {
		InvertPage invertPage = new InvertPage();
		IndexPage indexPage = new IndexPage();
		// 滚动到“抢头标”元素，点击“抢头标”
		indexPage.scrollIntoGoInvert();
		indexPage.clickGoInvert();
		// 滚动到“投标”元素，金额的输入框输入投资金额
		invertPage.scrollIntoInvert();
		invertPage.inputInvertMoney(Constants.SUCESS_MONEY);
		// 点击投标
		invertPage.clickInvert();
		// 根据投标成功的提示信息出现作为断言(断言)
		Assert.assertTrue(invertPage.isInvertSuccessExit());
	}

	/**
	 * 错误提示
	 * 
	 * @param money
	 * @param by
	 * @param expectedValue
	 */
	@Test(dataProviderClass = InvertData.class, dataProvider = "Invest_failDatas", priority = 2, enabled = false)
	public void testInvest_fail(String money, By by, String expectedValue) {
		InvertPage invertPage = new InvertPage();
		IndexPage indexPage = new IndexPage();
		// 滚动到“抢头标”元素，点击“抢头标”
		indexPage.scrollIntoGoInvert();
		indexPage.clickGoInvert();
		// 滚动到“投标”元素，金额的输入框输入投资金额
		invertPage.scrollIntoInvert();
		invertPage.inputInvertMoney(money);
		// 断言
		if (invertPage.isInvertText("投标")) {
			// 如果按钮的文本是“投标”,则点击投标
			invertPage.clickTipElementBy();
			// 断言--根据投标失败的提示信息出现作为断言
			WebElement webElement = waitElementVisible(by);
			String actualValue = webElement.getText();
			System.out.println("实际结果：" + actualValue + ",期望结果：" + expectedValue);
			Assert.assertEquals(actualValue, expectedValue);
		} else {
			// 如果按钮的文本不是“投标”，断言按钮的文本提示
			WebElement webElement = waitElementVisible(by);
			String actualValue = webElement.getText();
			System.out.println("实际结果：" + actualValue + ",期望结果：" + expectedValue);
			Assert.assertEquals(actualValue, expectedValue);
		}

	}

	/**
	 * "购买标的金额不能大于标总金额"
	 * 
	 * @param by
	 * @param expectedValue
	 */
	@Test(dataProviderClass = InvertData.class, dataProvider = "Invest_failDatas01", priority = 3, enabled = false)
	public void testInvest_fail01(By by, String expectedValue) {
		InvertPage invertPage = new InvertPage();
		IndexPage indexPage = new IndexPage();
		// 滚动到“抢头标”元素，点击“抢头标”
		indexPage.scrollIntoGoInvert();
		indexPage.clickGoInvert();
		// 滚动到“投标”元素，金额的输入框输入投资金额大于标总金额
		invertPage.scrollIntoInvert();
		invertPage.inputInvertMoney(invertPage.getInvertSurplusAmountByValue());
		// 如果按钮的文本是“投标”,则点击投标
		invertPage.clickTipElementBy();
		// 断言--根据投标失败的提示信息出现作为断言
		WebElement webElement = waitElementVisible(by);
		String actualValue = webElement.getText();
		System.out.println("实际结果：" + actualValue + ",期望结果：" + expectedValue);
		Assert.assertEquals(actualValue, expectedValue);

	}

	/**
	 * "投标金额大于可用金额"
	 * 
	 * @param by
	 * @param expectedValue
	 */
	@Test(dataProviderClass = InvertData.class, dataProvider = "Invest_failDatas02", priority = 4,enabled = false)
	public void testInvest_fail02(By by, String expectedValue) {
		InvertPage invertPage = new InvertPage();
		IndexPage indexPage = new IndexPage();
		// 滚动到“抢头标”元素，点击“抢头标”
		indexPage.scrollIntoGoInvert();
		indexPage.clickGoInvert();
		// 滚动到“投标”元素，金额的输入框输入投资金额大于可用金额
		invertPage.scrollIntoInvert();
		invertPage.inputInvertMoney(invertPage.getAvailableAmountByValue());
		// 如果按钮的文本是“投标”,则点击投标
		invertPage.clickTipElementBy();
		// 断言--根据投标失败的提示信息出现作为断言
		WebElement webElement = waitElementVisible(by);
		String actualValue = webElement.getText();
		System.out.println("实际结果：" + actualValue + ",期望结果：" + expectedValue);
		Assert.assertEquals(actualValue, expectedValue);

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		BrowserUtil.driver.close();
	}

}
