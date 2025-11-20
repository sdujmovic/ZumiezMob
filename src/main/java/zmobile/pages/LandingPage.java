package zmobile.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LandingPage extends BasePage {

	public LandingPage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='GET STARTED']")
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'GET STARTED'")
	private WebElement getStartedBtn;

	public boolean ifGetStartedBtnEnabled() {
		if (getStartedBtn.isEnabled())
			return true;
		else
			return false;
	}

	public void goToCreateAccountPage() {
		getStartedBtn.click();
	}

}
