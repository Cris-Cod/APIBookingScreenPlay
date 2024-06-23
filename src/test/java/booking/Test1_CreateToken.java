package booking;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Test;
import models.booking.CreateTokenData;
import org.junit.runner.RunWith;
import questions.ResponseCode;
import tasks.CreateTokenBooking;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SerenityRunner.class)
public class Test1_CreateToken {

    private static final String baseUrl = "https://restful-booker.herokuapp.com/";



    @Test
    public void test1_createTokenTest(){

        Actor user = Actor.named("user")
                .whoCan(CallAnApi.at(baseUrl));

        CreateTokenData dataInfo = new CreateTokenData();
        dataInfo.setUsername("admin");
        dataInfo.setPassword("password123");


        user.attemptsTo(
                CreateTokenBooking.data(dataInfo)
        );

        user.should(
                seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200))
        );
        String response = SerenityRest.lastResponse().asString();
        JsonPath js = new JsonPath(response);
        //String token = js.get("token");
        //Utils.setToken(token);
        //System.out.println(Utils.getToken());
    }
}
