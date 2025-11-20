package zmobile.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import zmobile.annotations.ZephyrTest;
import zmobile.utils.ZephyrScaleUploader;

public class ZephyrScaleListener implements ITestListener {

	@Override
	public void onTestSuccess(ITestResult result) {
		String testCaseKey = getZephyrTestKey(result);
		if (testCaseKey != null) {
			System.out.println("Test passed, uploading to Zephyr: " + testCaseKey);
			ZephyrScaleUploader.uploadResult(testCaseKey, true);
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testCaseKey = getZephyrTestKey(result);
		if (testCaseKey != null) {
			System.out.println("Test failed, uploading to Zephyr: " + testCaseKey);
			String failureReason = result.getThrowable() != null ? result.getThrowable().getMessage() : "Test failed";
			ZephyrScaleUploader.uploadResult(testCaseKey, false, failureReason);
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testCaseKey = getZephyrTestKey(result);
		if (testCaseKey != null) {
			System.out.println("Test skipped, uploading to Zephyr: " + testCaseKey);
			ZephyrScaleUploader.uploadResult(testCaseKey, false, "Test skipped");
		}
	}

	private String getZephyrTestKey(ITestResult result) {
		ZephyrTest annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(ZephyrTest.class);

		return annotation != null ? annotation.key() : null;
	}

}
