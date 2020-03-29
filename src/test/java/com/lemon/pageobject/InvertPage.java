package com.lemon.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.lemon.common.BasePage;
import com.lemon.common.BaseTest;
import com.lemon.utils.BrowserUtil;

/**
 * 投资页面对象
 * 
 * @author Edword
 *
 */
public class InvertPage extends BasePage {

	

	// 投资金额的输入框的元素定位
	private By invertMoneyBy = By.xpath("//input[@data-url='/Invest/invest']");

	// 投标的按钮的元素定位
	private By invertBy = By.xpath("//button[text()='投标']");

	// 投标成功的提示元素定位
	private By invertSuccessBy = By.xpath("//div[text()='投标成功！']");

	// 投标按钮的提示元素定位
	private By tipElementBy = By.xpath("//button[contains(@class,'btn-special')]");

	// 可用余额和剩余金额的提示元素定位
	private By availableAmountBy = By.xpath("//input[contains(@class,'form-control')]");

	// 滚动到“投标”的元素
	public void scrollIntoInvert() {
		scrollIntoElement(invertBy);
	}

	// 点击投标元素
	public void clickInvert() {
		click(invertBy);
	}

	// 2、金额的输入框输入投资金额
	public void inputInvertMoney(String datas) {
		type(invertMoneyBy, datas);
	}

	

	// 根据投标成功的提示信息出现作为断言(断言)
	public boolean isInvertSuccessExit() {
		WebElement webElement = waitElementVisible(invertSuccessBy);
		System.out.println(webElement.getText());
		return webElement.isDisplayed();
	}

	// 获取“投标”投标按钮的文本判断是否投标
	public boolean isInvertText(String textString) {
		System.out.println(textString.equals(getElementText(tipElementBy)));
		return textString.equals(getElementText(tipElementBy));
	}

	// 点击tipElementBy按钮
	public void clickTipElementBy() {
		click(tipElementBy);
	}

	// 获取元素invertSurplusAmountBy的文本值
	public  String getInvertSurplusAmountByValue() {
		int i = Integer.parseInt(getElementValue(availableAmountBy, "data-left")) + 100;
		return i+"";
	}

	// 获取元素availableAmountBy的属性值
	public  String getAvailableAmountByValue() {
		int i = Integer.parseInt(getElementValue(availableAmountBy, "data-amount")) + 100;
		return i+"";
	}
	

}
