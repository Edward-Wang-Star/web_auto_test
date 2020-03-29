package com.lemon.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 浏览器的工具类
 * @author Edword
 *
 */
public class BrowserUtil {
	// 添加日志
	private static Logger logger = Logger.getLogger(BrowserUtil.class);
	public static WebDriver driver;
	public static String browerName ="";

	/**
	 * 封装的一个浏览器打开的工具方法
	 * 
	 * @param browerType
	 */
	public static void openBrowser(String browserType) {
		browerName = browserType;
		if (browserType != "") {
			if (browserType.equals("chrome")) {
				// ----------------打开Chrome的代码--------------
				// 设置一个全局属性webdriver.chrome.driver ，让脚本认识到chrome驱动是在哪里
				System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
				// 实例化驱动，打开浏览器-chrome
				driver = new ChromeDriver();
				logger.info("=============打开Chrome浏览器=========");
			} else if (browserType.equals("firefox")) {
				// ---------------打开firefox的代码---------------
				// 设置一个全局属性webdriver.firefox.bin，让脚本认识到firefox可执行文件是再哪里
				// 设置一个全局属性webdriver.gecko.driver，让脚本认识到firefox驱动再哪里
				System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
				System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
				driver = new FirefoxDriver();
				logger.info("=============打开firefox浏览器=========");
			} else if (browserType.equals("IE")) {
				// -----------打开IE的代码--------------
				// 给予IE浏览器额外的能力
				// 1、忽略保护模式的设置
				// 2、忽略掉浏览器缩放设置
				DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
				desiredCapabilities
						.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				desiredCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
				// 在实例化IE驱动的时候，需要把额外的配置传给IE驱动
				driver = new InternetExplorerDriver(desiredCapabilities);
				logger.info("=============打开IE浏览器=========");
			}

		}

	}
	/**
	 * 封装的截图的API
	 * @param path 截图放置的路径
	 */
	public static File takeScreenShot(String path) {
		File file = null;
		// 判断打开的浏览器是什么？？
		if (browerName.equals("chrome")) {
			ChromeDriver chromeDriver = (ChromeDriver) BrowserUtil.driver;
			file = chromeDriver.getScreenshotAs(OutputType.FILE);
		} else if (browerName.equals("firefox")) {
			FirefoxDriver firefoxDriver = (FirefoxDriver) BrowserUtil.driver;
			file = firefoxDriver.getScreenshotAs(OutputType.FILE);
		} else if (browerName.equals("ie")) {
			InternetExplorerDriver ieDriver = (InternetExplorerDriver) BrowserUtil.driver;
			file = ieDriver.getScreenshotAs(OutputType.FILE);
		}

		File descFile = new File(path);
		// FileUtils类需要引入commons.io这个依赖
		// 将截图保存到本地
		try {
			FileUtils.copyFile(file, descFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descFile;
	}

	


	/**
	 * 封装的一个关闭浏览器的方法
	 */
	public static void closeBrowser() {
		logger.info("===========关闭浏览器===================");
		BrowserUtil.driver.quit();
	}

	/**
	 * 封装的一个让浏览器最大化的方法
	 */
	public static void maxBrowser() {
		logger.info("===============浏览器最大化================");
		BrowserUtil.driver.manage().window().maximize();
	}

}
