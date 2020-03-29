package com.lemon.listener;

import java.io.File;
import java.io.IOException;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

import com.google.common.io.Files;
import com.lemon.utils.BrowserUtil;

import io.qameta.allure.Attachment;

/**  
 * @Title: AllureReportListener.java
 * @Description: TODO(描述)
 * @author 歪歪欧巴
 * @date 2020年3月27日
 * @Copyright:湖南省零檬信息技术有限公司. All rights reserved. 
 * IHookable-->钩子，动态监听用例的执行状态，成功 or 失败
 */
public class AllureReportListener implements IHookable{

	@Override
	public void run(IHookCallBack callBack, ITestResult testResult) {
		//1、执行test方法
		//testResult-->测试结果
		callBack.runTestMethod(testResult);
		//2、判断测试结果是否有异常
		if(testResult.getThrowable() != null){		
			try {
				//发生了异常-->调用截图的方法
				//把截图嵌入到Allure报表中
				saveScreenshot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshot() throws IOException {
		//怎么样获取到项目的根目录
		String projectDir= System.getProperty("user.dir");
		//截图文件名字
		String fileName = System.currentTimeMillis()+".png";
		File file = BrowserUtil.takeScreenShot(projectDir+"\\target\\screenshot\\"+fileName);
		//toByteArray静态方法，可以把file对象转换成字节数组
	    return Files.toByteArray(file);
	}
	
}
