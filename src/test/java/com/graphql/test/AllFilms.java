package com.graphql.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

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
		given().contentType("application/json").header("Authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxOTllNjIyOTNmZWUzMDA2OWQ0ZDk1NCJ9LCJuaWNrbmFtZSI6Im1haWwybmF0YXJhanYiLCJuYW1lIjoibWFpbDJuYXRhcmFqdkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvZTY4YmRkNTMzNDAzYWVjMjk4NzBiM2YzZmI5Zjg5Mzk_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZtYS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMS0xMS0yMVQwNjoyNDozNS42NTFaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjE5OWU2MjI5M2ZlZTMwMDY5ZDRkOTU0IiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2Mzc0NzU4NzcsImV4cCI6MTYzNzUxMTg3NywiYXRfaGFzaCI6IkxCMER3MGlDQXVETV92Yk5iaGdmbHciLCJub25jZSI6IlNzNnQ0dTc2ZUhENGUyTmN0N2g4ZWJCVDgxZVJLYkFJIn0.fCfqiSaj_Pa8ebEGP_Vupbr7K2SThIRR34Fh5lZxA0rAMPWco2tz-UON4GhN7nX1_nb76ZUwR1PlFmOvrr3HP5di79RzTlu_xiFBBpNrisIs6YF73b-gy31d8nBKLUrLsBt7GJzyzk5V5UTQWCLybUdS3c8Y6BBulBy-X6ET-2rIwvzU8IdZGs6y_TwFvLA1UcfEHsXDmm6DpcjWIAfU5yn1Q48_SAJrTIO533CuUTWwoQVoBJLTm4m0ccvb-CK8KLAcBdxwE5NOPIAKAsGas3RMnoslJ6WLRktjkOYt605ZetCH_fRMp0n9rweEEo_FOnQDIfMwGpBYiAcDp8E-pQ") 
			.body(userQuery).post("/learn/graphql").then().assertThat().statusCode(200).and().body("data.users[0].name", equalTo("tui.glen"));
	}

}
