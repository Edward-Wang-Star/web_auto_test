package com.lemon.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.Assert;
import org.testng.annotations.*;

import com.lemon.common.BaseTest;
import com.lemon.pageobject.IndexPage;
import com.lemon.pageobject.LoginPage;
import com.lemon.testdatas.LoginData;
import com.lemon.utils.BrowserUtil;
import com.lemon.utils.Constants;
/**
 * 登录测试
 * @author Edword
 *
 */
public class Login extends BaseTest {
	@Parameters({ "browserName" })
	@BeforeMethod
	public void setUp(String browserName) {
		// 打开chrome浏览器
		BrowserUtil.openBrowser(browserName);
		//浏览器最大化
		BrowserUtil.maxBrowser();
		// 输入登录网址
		BrowserUtil.driver.get(Constants.LOGIN_URL);
	}

	@Test
	public void testLogin_success() {
		//你需要用到哪个页面的元素，实例化对应的页面
		LoginPage loginPage = new LoginPage();
		// 找到⼿机号码输⼊框，输⼊13323234545
		loginPage.inputMobilephone(Constants.INVEST_ACCOUNTNAME);
		// 找到密码输⼊框，输⼊lemon123456
		loginPage.inputPassword(Constants.INVEST_PASSWORD);
		// 找到登录按钮，点击登录
		loginPage.clickLogin();	
		// 断言？？
		// 根据主页的‘我的帐户’元素是否出现
		// 等待元素可见
		IndexPage indexPage = new IndexPage();
		assertTrue(indexPage.isMyAccountExit());
		
	}

	@Test(dataProviderClass = LoginData.class,dataProvider = "getLoginFailure01Datas",enabled = false)
	public void testLogin_failure01(String mobilephone, String password, String tips) {
		LoginPage loginPage = new LoginPage();
		loginPage.inputMobilephone(mobilephone);
		loginPage.inputPassword(password);
		loginPage.clickLogin();
		// 断言，根据提示文本：此账号没有经过授权，请联系管理员!
		String actualValue = loginPage.getTipsShowText();
		String expectedValue = tips;
		Assert.assertEquals(actualValue, expectedValue);
	}

	@Test(dataProviderClass = LoginData.class,dataProvider = "getLoginFailure02Datas",enabled = false)
	public void testLogin_failure02(String mobilephone, String password, String tips) {
		LoginPage loginPage = new LoginPage();
		loginPage.inputMobilephone(mobilephone);
		loginPage.inputPassword(password);
		loginPage.clickLogin();
		// 断言，手机号码提示框文本
		String actualValue = loginPage.getTipsInputText();
		String expectedValue = tips;
		Assert.assertEquals(actualValue, expectedValue);
	}

	/**
	 * 记住“手机号码”功能验证
	 */
	@Test(enabled = false)
	public void testRemeberMobilehone() {
		LoginPage loginPage = new LoginPage();
		IndexPage indexPage = new IndexPage();

		loginPage.inputMobilephone(Constants.INVEST_ACCOUNTNAME);
		loginPage.inputPassword(Constants.INVEST_PASSWORD);
		loginPage.clickLogin();

		//定位“退出”元素
		indexPage.clickQuit();
		//点击“登录”元素
		indexPage.clickLogin();
		
		String actualValue = loginPage.getMobilephoneValue();
		String expectedValue = Constants.INVEST_ACCOUNTNAME;
		Assert.assertEquals(actualValue, expectedValue);

	}

	@AfterMethod
	public void tearDown() {
		BrowserUtil.driver.quit();
	}

}
