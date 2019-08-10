package restassured.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import restassured.common.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//
//   using https://jsonplaceholder.typicode.com
//

public class GetRequestDemo extends BaseTest {

	@Test
	public void statusVerificationOnTodos() {
		given()
			.when().get("/todos")
			.then().statusCode(200);
	}

	@Test
	public void printResponseVerificationOnUser() {
		Response response = given().when().get("/users/1");

		System.out.println("user: " + response.body().prettyPrint());
	}

	@Test
	public void validateResponseVerificationOnEmployee() {
		given().header("Content-Type", ContentType.JSON).header("Accept", ContentType.JSON).param("postId", "1")
			.when().get("/comments")
			.then().statusCode(200).and()
				.body("id[1]", equalTo(2))
				.body("name[1]", equalTo("quo vero reiciendis velit similique earum"))
				.body("email[1]", equalTo("Jayne_Kuhic@sydney.com"));
	}
}
