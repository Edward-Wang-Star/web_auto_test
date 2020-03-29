package com.lemon.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.lemon.utils.BrowserUtil;

/**
 * 基础Test案例
 * 
 * @author Edword
 *
 */
public class BaseTest {

	// 标名
	public static String bidName = "";
	

//	@BeforeSuite
	public void setUpSuite() throws InterruptedException {
		// 打开chrome浏览器
		BrowserUtil.openBrowser("chrome");
		// 有一部分的元素被遮挡住了，我们选择通过浏览器最大化来解决
		BrowserUtil.driver.manage().window().maximize();
		// 测试套件运行时的数据初始化
		addBid();
	}

	@AfterSuite
	public void tearDownSuite() {
		// 数据清理
		BrowserUtil.driver.quit();
	}

	/**
	 * 后台加标
	 * 
	 * @throws InterruptedException
	 */
	public void addBid() throws InterruptedException {
		// 登录页面
		BrowserUtil.driver.get("http://120.78.128.25:8765/Admin/Index/login.html");
		waitElementVisible(By.name("admin_name")).sendKeys("lemon7");
		waitElementVisible(By.name("admin_pwd")).sendKeys("lemonbest");
		waitElementVisible(By.name("code")).sendKeys("hapi");
		waitElementClickable(By.xpath("//button[text()='登陆后台']")).click();

		// 后台主页
		// 点击“借款管理”
		waitElementClickable(By.xpath("//span[text()='借款管理']")).click();
		// 切换iframe
		waitIframeAndSwitch(By.id("mainFrame"));
		Thread.sleep(3000);
		// 加标
		waitElementClickable(By.xpath("//span[text()='加标']")).click();
		// 输入借款人得信息
		WebElement webElement = waitElementVisible(By.xpath("//td[text()='借款人:']/following-sibling::td/span/input"));
		webElement.sendKeys("13323234545");
		Thread.sleep(1000);
		// 输入方向下键+enter键
		webElement.sendKeys(Keys.DOWN);
		Thread.sleep(1000);
		webElement.sendKeys(Keys.ENTER);
		bidName = "点点点" + System.currentTimeMillis();
		waitElementVisible(By.xpath("//td[text()='贷款标题:']/following-sibling::td/input")).sendKeys(bidName);
		waitElementVisible(By.xpath("//td[text()='年利率利息:']/following-sibling::td/input")).sendKeys("10");
		waitElementVisible(By.xpath("//td[text()='借款期限:']/following-sibling::td/input")).sendKeys("6");
		waitElementVisible(By.xpath("//td[text()='借款额度:']/following-sibling::td/input")).sendKeys("200000");
		waitElementVisible(By.xpath("//td[text()='竞标期限:']/following-sibling::td/input")).sendKeys("5");

		// 录入风控评测得信息
		waitElementClickable(By.xpath("//span[text()='风控评测']")).click();
		waitElementVisible(By.xpath("//td[text()='评估价值:']/following-sibling::td/input")).sendKeys("2000000");

		// 录入项目得信息
		waitElementClickable(By.xpath("//span[text()='项目录入']")).click();
		waitElementVisible(By.xpath("//td[text()='籍贯:']/following-sibling::td/input")).sendKeys("湖南长沙");
		waitElementVisible(By.xpath("//td[text()='职业:']/following-sibling::td/input")).sendKeys("测试工程师");
		waitElementVisible(By.xpath("//td[text()='年龄:']/following-sibling::td/input")).sendKeys("24");

		// 点击提交
		waitElementClickable(By.xpath("//span[text()='提交']")).click();

		Thread.sleep(3000);
		// 审核
		// 选中标
		int i = 3;
		while (i-- > 0) {
			waitElementClickable(By.id("datagrid-row-r1-2-0")).click();
			// 点击审核
			waitElementClickable(By.xpath("//span[text()='审核']")).click();
			// 点击审核通过
			waitElementClickable(By.xpath("//span[text()='审核通过']")).click();
			Thread.sleep(2000);
		}
		// 关闭浏览器
		BrowserUtil.closeBrowser();
	}

	/**
	 * 封装的等待元素可被点击的方法
	 * 
	 * @param by 元素定位
	 * @return
	 */
	public WebElement waitElementClickable(By by) {
		WebDriverWait webDriverWait = new WebDriverWait(BrowserUtil.driver, 6);
		WebElement webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
		return webElement;
	}

	/**
	 * 封装的等待元素可见的方法
	 * 
	 * @param by
	 * @return
	 */
	public WebElement waitElementVisible(By by) {
		WebDriverWait webDriverWait = new WebDriverWait(BrowserUtil.driver, 6);
		WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return webElement;
	}

	/**
	 * 封装的等待iframe可用并且切换iframe
	 * 
	 * @param by
	 */
	public void waitIframeAndSwitch(By by) {
		WebDriverWait webDriverWait = new WebDriverWait(BrowserUtil.driver, 6);
		// frameToBeAvailableAndSwitchToIt 当iframe可用的时候，切换到里面去
		webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
	}

}
