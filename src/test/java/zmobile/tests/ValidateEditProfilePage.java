package zmobile.tests;

import zmobile.annotations.ZephyrTest;
import zmobile.base.MobileBaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(zmobile.listeners.ZephyrScaleListener.class)
public class ValidateEditProfilePage extends MobileBaseTest {

	@Test
	@ZephyrTest(key = "HTH-T148", description = "Validate edit profile screen elements")
	public void testEditProfileScreenElements() throws InterruptedException {
		Thread.sleep(5000);
		header.selectProfileIcon();
		profilePage.editProfile();
		Assert.assertEquals(editProfilePage.validateAccountInformationTitle(), "Account Information");

		String[] accountInformation = { "firstName", "lastName", "email", "phone", "birthday", "gender", "ethnicity",
				"memberSince" };

		for (String field : accountInformation) {

			switch (field) {

			case "firstName":
				Assert.assertTrue(editProfilePage.firstNameDisplayed());
				break;

			case "lastName":
				Assert.assertTrue(editProfilePage.lastNameDisplayed());
				break;

			case "email":
				Assert.assertTrue(editProfilePage.emailDisplayed());
				break;

			case "phone":
				Assert.assertTrue(editProfilePage.phoneDisplayed());
				break;

			case "birthday":
				Assert.assertTrue(editProfilePage.birthdayDisplayed());
				break;

			case "gender":
				Assert.assertTrue(editProfilePage.genderDisplayed());
				break;

			case "ethnicity":
				Assert.assertTrue(editProfilePage.ethnicityDisplayed());
				break;

			case "memberSince":
				Assert.assertTrue(editProfilePage.memberSinceDisplayed());
				break;

			default:
				System.out.println("Field not found");
			}
		}

		profilePage.findElementByText("Zumiez Employee");
		Assert.assertEquals(editProfilePage.validateHomeAddressTitle(), "Home Address");

		String[] homeAddress = { "address", "city", "state", "zipcode", "country", "phone" };

		for (String field : homeAddress) {

			switch (field) {

			case "address":
				Assert.assertTrue(editProfilePage.addressDisplayed());
				break;

			case "city":
				Assert.assertTrue(editProfilePage.cityDisplayed());
				break;

			case "state":
				Assert.assertTrue(editProfilePage.stateDisplayed());
				break;

			case "zipcode":
				Assert.assertTrue(editProfilePage.zipcodeDisplayed());
				break;

			case "country":
				Assert.assertTrue(editProfilePage.countryDisplayed());
				break;

			case "phone":
				Assert.assertTrue(editProfilePage.shippingPhoneDisplayed());
				break;

			default:
				System.out.println("Field not found");
			}
		}

		Assert.assertEquals(editProfilePage.validateZumiezEmployeeTitle(), "Zumiez Employee");
		profilePage.findElementByText("Employee Sales Code");
		Assert.assertTrue(editProfilePage.employeSalesCodeDisplayed());
		Assert.assertTrue(editProfilePage.saveChangesBtnDisplayed());

	}
}
