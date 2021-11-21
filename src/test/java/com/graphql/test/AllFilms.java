package com.graphql.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class AllFilms {
	
	@Test
	public void getAllFilms() {
		
		RestAssured.baseURI = "https://swapi-graphql.netlify.app";
		String filmQuery = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\\n\",\"variables\":null}";
		
		given().log().all().contentType("application/json")
		.body(filmQuery).post("/.netlify/functions/index").then()
			.assertThat().statusCode(200).and()
				.body("data.allFilms.films[0].title", equalTo("A New Hope"));
		
	}

}
