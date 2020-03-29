package com.lemon.testdatas;

import java.util.jar.Attributes.Name;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

import com.lemon.pageobject.InvertPage;

import net.bytebuddy.asm.Advice.Return;

/**
 * @Title: LoginData.java
 * @Description: TODO(描述)
 * @author 歪歪欧巴
 * @date 2020年3月25日
 * @Copyright:湖南省零檬信息技术有限公司. All rights reserved.
 */
public class InvertData  {

	/**
	 * 投资的数据驱动
	 */
	@DataProvider(name = "Invest_failDatas")
	public Object[][] gettestInvest_failDatas() {
		String string = "1";
		Object[][] datas = { { "0", By.xpath("//div[text()='提示']/following-sibling::div/div"), "请正确填写投标金额" },
				{ "99", By.xpath("//div[@class='right']//button[@disabled]"), "请输入10的整数倍" },
				{ "100.01", By.xpath("//div[@class='right']//button[@disabled]"), "请输入10的整数倍" },
				{ "-100 ", By.xpath("//div[text()='提示']/following-sibling::div/div"), "请正确填写投标金额" },
				{ "10a0", By.xpath("//div[@class='right']//button[@disabled]"), "请输入10的整数倍" },
				{ "10 0", By.xpath("//div[@class='right']//button[@disabled]"), "请输入10的整数倍" },
				{ "101", By.xpath("//div[@class='right']//button[@disabled]"), "请输入10的整数倍" },
				{ string, By.xpath("//div[text()='提示']/following-sibling::div/div"), "请正确填写投标金额"},
				{ "  ", By.xpath("//div[text()='提示']/following-sibling::div/div"), "请正确填写投标金额" },
				};
				return datas;
	}
	
	/**
	 * "购买标的金额不能大于标总金额"
	 * @return
	 */
	@DataProvider(name = "Invest_failDatas01")
	public Object[][] gettestInvest_failDatas01(){
		Object[][] datas ={
		{By.xpath("//div[text()='提示']/following-sibling::div/div"), "购买标的金额不能大于标总金额" },
		};
	return datas;
	};
	
	/**
	 * "投标金额大于可用金额"
	 * @return
	 */
	@DataProvider(name = "Invest_failDatas02")
	public Object[][] gettestInvest_failDatas02(){
		Object[][] datas ={
		{By.xpath("//div[text()='提示']/following-sibling::div/div"), "投标金额大于可用金额" }
		};
	return datas;
	};
		
	

}
