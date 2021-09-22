package com.ebanxapi;

import com.ebanxapi.domain.entity.event.Event;
import com.ebanxapi.domain.entity.event.EventType;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EbanxApiApplicationTests {

	@LocalServerPort
	private int port;

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	@Order(1)
	public void whenNotFoundOnGetBalance() {
		get("/balance?account_id=1234").then().statusCode(404).assertThat()
				.body(equalTo(0.0));
	}

	@Test
	@Order(2)
	public void depositWhenRequestedAccountNonExistThenCreated() {
		Event event = new Event();
		event.setType(EventType.DEPOSIT);
		event.setDestination("100");
		event.setAmount(10.0);

		with().body(event)
				.when()
				.request("POST", "/event")
				.then()
				.statusCode(201);
	}

	@Test
	@Order(3)
	public void depositWhenAccountExist() {
		Event event = new Event();
		event.setType(EventType.DEPOSIT);
		event.setDestination("100");
		event.setAmount(10.0);

		with().body(event)
				.when()
				.request("POST", "/event")
				.then()
				.statusCode(201);
	}

	@Test
	@Order(4)
	public void whenSuccessOnGetBalance() {
		get("/balance?account_id=100").then().statusCode(200).assertThat()
				.body(equalTo(20));
	}

	@Test
	@Order(5)
	public void withdrawWhenRequestedAccountNonExist() {
		Event event = new Event();
		event.setType(EventType.WITHDRAW);
		event.setDestination("200");
		event.setAmount(10.0);

		with().body(event)
				.when()
				.request("POST", "/event")
				.then()
				.statusCode(404);
	}

	@Test
	@Order(6)
	public void withdrawWhenRequestedAccountExist() {
		Event event = new Event();
		event.setType(EventType.WITHDRAW);
		event.setDestination("100");
		event.setAmount(5.0);

		with().body(event)
				.when()
				.request("POST", "/event")
				.then()
				.statusCode(201);
	}

	@Test
	@Order(7)
	public void transferWhenRequestedAccountExist() {
		Event event = new Event();
		event.setType(EventType.TRANSFER);
		event.setOrigin("100");
		event.setDestination("300");
		event.setAmount(5.0);

		with().body(event)
				.when()
				.request("POST", "/event")
				.then()
				.statusCode(201);
	}

	@Test
	@Order(8)
	public void transferWhenRequestedAccountNonExist() {
		Event event = new Event();
		event.setType(EventType.TRANSFER);
		event.setOrigin("200");
		event.setDestination("300");
		event.setAmount(15.0);

		with().body(event)
				.when()
				.request("POST", "/event")
				.then()
				.statusCode(404);
	}

}
