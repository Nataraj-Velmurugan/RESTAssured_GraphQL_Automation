package com.graphql.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.graphql.pojo.PojoQuery;
import com.graphql.pojo.PojoVariable;

import io.restassured.RestAssured;

public class AllFilms {

	@Test
	public void getAllFilms() {
//		without auth header
		RestAssured.baseURI = "https://swapi-graphql.netlify.app";
		String filmQuery = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\\n\",\"variables\":null}";

		given().log().all().contentType("application/json").body(filmQuery).post("/.netlify/functions/index").then()
				.log().all().assertThat().statusCode(200).and()
				.body("data.allFilms.films[0].title", equalTo("A New Hope"));

	}

	@Test
	public void getUserData() {
		// with auth header

		RestAssured.baseURI = "https://hasura.io";
		String userQuery = "{\"query\":\"{\\n  users(limit: 10) {\\n    id\\n    name\\n    todos(limit: 10) {\\n      user {\\n        name\\n      }\\n      title\\n    }\\n  }\\n}\\n\",\"variables\":null}";
		given().contentType("application/json").header("Authorization",
				"Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxOTllNjIyOTNmZWUzMDA2OWQ0ZDk1NCJ9LCJuaWNrbmFtZSI6Im1haWwybmF0YXJhanYiLCJuYW1lIjoibWFpbDJuYXRhcmFqdkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvZTY4YmRkNTMzNDAzYWVjMjk4NzBiM2YzZmI5Zjg5Mzk_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZtYS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMS0xMS0yMVQwNjoyNDozNS42NTFaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjE5OWU2MjI5M2ZlZTMwMDY5ZDRkOTU0IiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2Mzc0NzU4NzcsImV4cCI6MTYzNzUxMTg3NywiYXRfaGFzaCI6IkxCMER3MGlDQXVETV92Yk5iaGdmbHciLCJub25jZSI6IlNzNnQ0dTc2ZUhENGUyTmN0N2g4ZWJCVDgxZVJLYkFJIn0.fCfqiSaj_Pa8ebEGP_Vupbr7K2SThIRR34Fh5lZxA0rAMPWco2tz-UON4GhN7nX1_nb76ZUwR1PlFmOvrr3HP5di79RzTlu_xiFBBpNrisIs6YF73b-gy31d8nBKLUrLsBt7GJzyzk5V5UTQWCLybUdS3c8Y6BBulBy-X6ET-2rIwvzU8IdZGs6y_TwFvLA1UcfEHsXDmm6DpcjWIAfU5yn1Q48_SAJrTIO533CuUTWwoQVoBJLTm4m0ccvb-CK8KLAcBdxwE5NOPIAKAsGas3RMnoslJ6WLRktjkOYt605ZetCH_fRMp0n9rweEEo_FOnQDIfMwGpBYiAcDp8E-pQ")
				.body(userQuery).post("/learn/graphql").then().assertThat().statusCode(200).and()
				.body("data.users[0].name", equalTo("tui.glen"));
	}

	// parameters

	@DataProvider
	public Object[][] getUserQueryData() {
		return new Object[][] { 
			{ "5", "joscool.o2" }, 
			{ "10", "khalilkhalil365" } 
		};
	}
	
	@Test(dataProvider="getUserQueryData")
	public void getUserData(String limit, String name) {
		// with auth header

		RestAssured.baseURI = "https://hasura.io";
		String userQuery = "{\"query\":\"{\\n  users(limit: 10, where: {name: {_eq: \\\""+name+"\\\"}}) {\\n    id\\n    name\\n  }\\n}\\n\",\"variables\":null}";
		given().contentType("application/json").header("Authorization",
				"Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxOTllNjIyOTNmZWUzMDA2OWQ0ZDk1NCJ9LCJuaWNrbmFtZSI6Im1haWwybmF0YXJhanYiLCJuYW1lIjoibWFpbDJuYXRhcmFqdkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvZTY4YmRkNTMzNDAzYWVjMjk4NzBiM2YzZmI5Zjg5Mzk_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZtYS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMS0xMS0yMVQwNjoyNDozNS42NTFaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjE5OWU2MjI5M2ZlZTMwMDY5ZDRkOTU0IiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2Mzc0NzU4NzcsImV4cCI6MTYzNzUxMTg3NywiYXRfaGFzaCI6IkxCMER3MGlDQXVETV92Yk5iaGdmbHciLCJub25jZSI6IlNzNnQ0dTc2ZUhENGUyTmN0N2g4ZWJCVDgxZVJLYkFJIn0.fCfqiSaj_Pa8ebEGP_Vupbr7K2SThIRR34Fh5lZxA0rAMPWco2tz-UON4GhN7nX1_nb76ZUwR1PlFmOvrr3HP5di79RzTlu_xiFBBpNrisIs6YF73b-gy31d8nBKLUrLsBt7GJzyzk5V5UTQWCLybUdS3c8Y6BBulBy-X6ET-2rIwvzU8IdZGs6y_TwFvLA1UcfEHsXDmm6DpcjWIAfU5yn1Q48_SAJrTIO533CuUTWwoQVoBJLTm4m0ccvb-CK8KLAcBdxwE5NOPIAKAsGas3RMnoslJ6WLRktjkOYt605ZetCH_fRMp0n9rweEEo_FOnQDIfMwGpBYiAcDp8E-pQ")
				.body(userQuery).post("/learn/graphql").then().assertThat().statusCode(200).and()
				.body("data.users[0].name", equalTo(name));
	}
	
	@Test
	public void getUserDataWithPOJO() {
		
		RestAssured.baseURI = "https://hasura.io";
		PojoQuery inputQuery = new PojoQuery();
		PojoVariable inputVariable = new PojoVariable();
		
		inputQuery.setQuery("query($limit: Int!)\r\n"
				+ "{\r\n"
				+ "  users(limit: $limit) {\r\n"
				+ "    name\r\n"
				+ "  }\r\n"
				+ "}");
		
		inputVariable.setLimit(10);
		inputQuery.setVariables(inputVariable);
		
		given().contentType("application/json").header("Authorization",
				"Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxOTllNjIyOTNmZWUzMDA2OWQ0ZDk1NCJ9LCJuaWNrbmFtZSI6Im1haWwybmF0YXJhanYiLCJuYW1lIjoibWFpbDJuYXRhcmFqdkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvZTY4YmRkNTMzNDAzYWVjMjk4NzBiM2YzZmI5Zjg5Mzk_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZtYS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMS0xMS0yMVQwNjoyNDozNS42NTFaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjE5OWU2MjI5M2ZlZTMwMDY5ZDRkOTU0IiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2Mzc1NjAxMzIsImV4cCI6MTYzNzU5NjEzMiwiYXRfaGFzaCI6InhPOHdzU2NGY0dod29lT2NLMlpBNnciLCJub25jZSI6InlhSXZTcFRxemVNN29CUG4zfkhsbnNvOXBDb0hzM2V6In0.BGvxzwNS-KIHElk8iNXuPwmUXMfs5CIvzLpJXDnFfLPqI-xr0BgckrKBhpcitjSmcA379rbdxNdDykHoC8fcE6hUgO6hV7gAYzuxatlvqmOc2rX7f1cGz38IW8pMY2Zb1qfmUrGEq25CwZq9kJqZNJNpZTBhJVdPOzMROxPXyDQs9xugpOeLN3jiIGsygWEIewum8frBpT7Bfb8dtnDdoAIgqkBaNxAvplN78HKpJOG3uwABoLjDRxKjL5g5auDeGnd2qSagqiVvS08ysKN__mTOJW0YneztfET_DDk5kzTWgt-Ey158uyxlZ0qeoaH3-CSBdts_efh0XLN4qu6hSA")
				.body(inputQuery).post("/learn/graphql").then().log().all().assertThat().statusCode(200).and()
				.body("data.users[0].name", equalTo("tui.glen"));
		
	}

}
