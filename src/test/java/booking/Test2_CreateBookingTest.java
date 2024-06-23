package booking;


import io.restassured.path.json.JsonPath;
import models.booking.CreateTokenData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import models.booking.BookingDates;
import models.booking.CreateBookingData;
import org.junit.runners.MethodSorters;
import questions.FirtsNameAssertion;
import questions.ResponseCode;
import tasks.*;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "src/test/resources/createBooking.csv")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test2_CreateBookingTest {

    private static final String baseUrl = "https://restful-booker.herokuapp.com/";
    private JsonPath js;
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private String checkin;
    private String checkout;
    private String additionalneeds;

    CreateBookingData bookingData = new CreateBookingData();
    BookingDates dates = new BookingDates();

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
        String token = js.get("token");
        Utils.setToken(token);
        System.out.println(Utils.getToken());
    }


    @Test
    public void test2_createBooking(){

        bookingData.setFirstname(firstname);
        bookingData.setLastname(lastname);
        bookingData.setTotalprice(totalprice);
        bookingData.setDepositpaid(depositpaid);


        dates.setCheckin(checkin);
        dates.setCheckout(checkout);
        bookingData.setBookingdates(dates);

        bookingData.setAdditionalneeds(additionalneeds);

        Actor user = Actor.named("user")
                .whoCan(CallAnApi.at(baseUrl));

        user.attemptsTo(
                CreateBookingTask.data(bookingData)
        );

        user.should(
                seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200))
        );
        String response = SerenityRest.lastResponse().asString();
        js = new JsonPath(response);
        String name = js.get("booking.firstname");
        int id = js.get("bookingid");
        Utils.setBookingId(id);
        System.out.println(id);
        Assert.assertEquals(name, bookingData.getFirstname());

        user.should(
                seeThat(new FirtsNameAssertion(bookingData.getFirstname(),name), is(true))
        );
    }

    @Test
    public void test3_getBooking(){
        Actor user = Actor.named("user")
                .whoCan(CallAnApi.at(baseUrl));

        user.attemptsTo(
                GetBookingTask.idBooking(Utils.getBookingId())
        );

        user.should(
                seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200))
        );

        String responseGet = SerenityRest.lastResponse().asString();
        js = new JsonPath(responseGet);
        String nameGet = js.get("firstname");


        user.should(
                seeThat(new FirtsNameAssertion(bookingData.getFirstname(),nameGet), is(true))
        );
    }


    @Test
    public void test4_UpdateBooking() {
        if (bookingData.getFirstname().equalsIgnoreCase("Pedro")){
            bookingData.setFirstname("Jose");
            dates.setCheckin("2023-05-12");
            dates.setCheckout("2023-06-28");
            bookingData.setBookingdates(dates);

            Actor user = Actor.named("user")
                    .whoCan(CallAnApi.at(baseUrl));

            user.attemptsTo(
                    UpdateBookingTasks.update(bookingData, Utils.getToken(), Utils.getBookingId())
            );

            user.should(
                    seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200))
            );

        }else if(bookingData.getFirstname().equalsIgnoreCase("Javier")){
            bookingData.setLastname("Mora");
            dates.setCheckin("2023-06-20");
            dates.setCheckout("2023-07-20");
            bookingData.setBookingdates(dates);

            Actor user = Actor.named("user")
                    .whoCan(CallAnApi.at(baseUrl));

            user.attemptsTo(
                    UpdateBookingTasks.update(bookingData, Utils.getToken(), Utils.getBookingId())
            );

            user.should(
                    seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200))
            );

            String responseUpdate = SerenityRest.lastResponse().asString();
            js = new JsonPath(responseUpdate);
            String nameUpdate = js.get("firstname");

            user.should(
                    seeThat(new FirtsNameAssertion(bookingData.getFirstname(),nameUpdate), is(true))
            );
        }
    }

    @Test
    public void test5_deleteBooking(){
        Actor user = Actor.named("user")
                .whoCan(CallAnApi.at(baseUrl));

        user.attemptsTo(
                DeleteBookingTasks.deleteBooking(Utils.getBookingId(), Utils.getToken())
        );

        user.should(
                seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(201))
        );

        String reponseDelete = SerenityRest.lastResponse().asString();

        user.should(
                seeThat(new FirtsNameAssertion("Created",reponseDelete), is(true))
        );
    }




}
