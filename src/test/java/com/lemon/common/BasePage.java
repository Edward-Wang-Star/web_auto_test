package com.lemon.common;

/**
 * 基础Page对象
 * @author Edword
 *
 */

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lemon.utils.BrowserUtil;

public class BasePage {
	Logger logger = Logger.getLogger(BasePage.class);

	// 点击元素
	public void click(By by) {
		waitElementClickable(by).click();
		logger.info("点击了元素【" + by + "】");
	}

	// 往元素中输入数据
	public void type(By by, String datas) {
		waitElementVisible(by).sendKeys(datas);
		logger.info("往元素【" + by + "】输入了【" + datas + "】");
	}

	// 元素是否存在
	public boolean isElementExist(By by) {
		boolean isExist = waitElementVisible(by).isDisplayed();
		logger.info("元素【" + by + "】存在【" + isExist + "】");
		return isExist;
	}

	// 获取元素文本值
	public String getElementText(By by) {
		String text = waitElementVisible(by).getText();
		logger.info("获取元素【" + by + "】文本值为【" + text + "】");
		return text;
	}

	// 获取元素的属性值
	public String getElementValue(By by, String attributeName) {
		String attributeValue = waitElementVisible(by).getAttribute(attributeName);
		logger.info("获取元素【" + by + "】属性【" + attributeName + "属性值为【" + attributeValue + "】");
		return attributeValue;
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
	 * 滚动到指定元素上的方法
	 * 
	 * @param by
	 */
	public void scrollIntoElement(By by) {
		WebElement webElement = waitElementVisible(by);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) BrowserUtil.driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(0);", webElement);
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

	/**
	 * 滑动列表找元素并且进行点击(懒加载)
	 * 
	 * @param selectedText 选中元素文本
	 * @param by           正在加载类似元素的定位表达式
	 * @throws InterruptedException
	 */
	public static void clickElementInList(String selectedText, By by) throws InterruptedException {
		// 滑动之前的页面源代码信息
		String beforeSource = "";
		// 滑动之后的页面源代码信息
		String afterSource = "";
		// 循环条件
		// 1、找到了元素，跳出循环
		// 2、如果没有找到元素？？？怎么跳出循环
		while (true) {
			WebElement webElement = BrowserUtil.driver.findElement(by);
			beforeSource = BrowserUtil.driver.getPageSource();
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) BrowserUtil.driver;
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(0);", webElement);
			// 如果当前页面有途虎养车这个元素，怎么判断是否有？？--getPageSource
			if (BrowserUtil.driver.getPageSource().contains(selectedText)) {
				BrowserUtil.driver.findElement(By.linkText(selectedText)).click();
				break;
			}
			Thread.sleep(1000);
			afterSource = BrowserUtil.driver.getPageSource();
			// 页面元素没有变化---滑动到了最底部
			if (afterSource.equals(beforeSource)) {
				break;
			}
			Thread.sleep(1500);
		}
	}

	

	/**
	 * 切换窗口的方法
	 * 
	 * @param title 窗口的标题
	 */
	public void switchWindow(String title) {
		Set<String> handles = BrowserUtil.driver.getWindowHandles();
		// 切换窗口的方式--循环遍历handles集合
		for (String handle : handles) {
			// 判断是哪一个页面的句柄？？--根据什么来判断？？？title
			if (BrowserUtil.driver.getTitle().equals(title)) {
				break;
			} else {
				// 切换窗口--根据窗口标识来切换
				BrowserUtil.driver.switchTo().window(handle);
			}

		}
	}

}
