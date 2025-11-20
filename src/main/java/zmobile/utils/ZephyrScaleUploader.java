package zmobile.utils;

import java.util.HashMap;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ZephyrScaleUploader {

	private static final String ZEPHYR_BASE_URL = "https://api.zephyrscale.smartbear.com/v2";
	private static final String API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczovL3p1bWllei5hdGxhc3NpYW4ubmV0IiwidXNlciI6eyJhY2NvdW50SWQiOiI1ZmUxY2FmNjkxYmIyZTAxMDg0ZDE0NGMiLCJ0b2tlbklkIjoiY2FiZjg4MjgtN2U3NC00YmNmLTg0M2MtNzIzZWYyZWI4ZTg3In19LCJpc3MiOiJjb20ua2Fub2FoLnRlc3QtbWFuYWdlciIsInN1YiI6IjNiYzJlOGY2LWQ3NTctM2ZhMC1iOTVhLTBiMjVlNDM4OGMwZCIsImV4cCI6MTc4MTM4MDMxOCwiaWF0IjoxNzQ5ODQ0MzE4fQ.BwFrNKglTPocuRDJdQI_bv39DzV5u4zBXzi2I2-m7Ls"; // Store
																																																																																																																								// in
																																																																																																																								// config
	private static final String PROJECT_KEY = "HTH";
	private static final String TEST_CYCLE_KEY = "HTH-R26";

	public static void uploadResult(String testCaseKey, boolean passed) {
		uploadResult(testCaseKey, passed, null);
	}

	public static void uploadResult(String testCaseKey, boolean passed, String comment) {
		try {
			String status = passed ? "Pass" : "Fail";

			Map<String, Object> body = new HashMap<>();
			body.put("projectKey", PROJECT_KEY);
			body.put("testCaseKey", testCaseKey);
			body.put("testCycleKey", TEST_CYCLE_KEY);
			body.put("statusName", status);

			if (comment != null && !comment.isEmpty()) {
				body.put("comment", comment);
			}

			Response response = RestAssured.given().header("Authorization", "Bearer " + API_TOKEN)
					.contentType(ContentType.JSON).body(body).post(ZEPHYR_BASE_URL + "/testexecutions");

			if (response.getStatusCode() == 201) {
				System.out
						.println("✓ Successfully uploaded result to Zephyr for: " + testCaseKey + " [" + status + "]");
			} else {
				System.err.println("✗ Failed to upload to Zephyr. Status: " + response.getStatusCode() + ", Body: "
						+ response.getBody().asString());
			}

		} catch (Exception e) {
			System.err.println("Error uploading to Zephyr Scale: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
