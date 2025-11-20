package zmobile.tests;

import zmobile.annotations.ZephyrTest;
import zmobile.base.MobileBaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(zmobile.listeners.ZephyrScaleListener.class)
public class ValidateProfilePage extends MobileBaseTest {

	@Test
	@ZephyrTest(key = "HTH-T147", description = "Validate profile screen elements")
	public void testProfileScreenElements() throws InterruptedException {
		Thread.sleep(5000);
		header.selectProfileIcon();
		Assert.assertTrue(profilePage.editProfileIconDisplayed());
		Assert.assertTrue(profilePage.profileFullNameDisplayed());
		Assert.assertTrue(profilePage.backRightBtnDisplayed());
		profilePage.findElementByText("THE ZUMIEZ STASH FAQS");
		Assert.assertTrue(profilePage.allTheOtherStaffTitleDisplayed());

		String[] otherStaff = { "zumiezWebsite", "zumiezStashFaq", "findStore", "sendFeedback", "thisCouldBeYou",
				"applyForJob", "termsOfUse", "privacyPolicy" };

		for (String option : otherStaff) {

			switch (option) {

			case "zumiezWebsite":
				Assert.assertTrue(profilePage.zumiezBtnDisplayed());
				break;

			case "zumiezStashFaq":
				Assert.assertTrue(profilePage.stashFaqBtnDisplayed());
				break;

			case "findStore":
				profilePage.findElementByText("PRIVACY POLICY");
				Assert.assertTrue(profilePage.findStoreBtnDisplayed());
				break;

			case "sendFeedback":
				Assert.assertTrue(profilePage.sendFeedbackBtnDisplayed());
				break;

			case "thisCouldBeYou":
				Assert.assertTrue(profilePage.thisCouldBeYouBtnDisplayed());
				break;

			case "applyForJob":
				Assert.assertTrue(profilePage.applyForJobBtnDisplayed());
				break;

			case "termsOfUse":
				Assert.assertTrue(profilePage.termsOfUseBtnDisplayed());
				break;

			case "privacyPolicy":
				Assert.assertTrue(profilePage.privacyPolicyBtnDisplayed());
				break;

			default:
				System.out.println("Option not found");
			}
		}
		profilePage.findElementByText("CALL US");
		Assert.assertTrue(profilePage.callUsBtnDisplayed());
		profilePage.findElementByText("LOGOUT");
		Assert.assertTrue(profilePage.phoneDisplayed());
		Assert.assertTrue(profilePage.emailDisplayed());
		Assert.assertTrue(profilePage.customerServiceHoursDisplayed());
		Assert.assertTrue(profilePage.deleteAccountBtnDisplayed());

	}
}
