package booking;


import io.restassured.path.json.JsonPath;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import models.booking.BookingDates;
import models.booking.CreateBookingData;
import org.junit.runners.MethodSorters;
import questions.ResponseCode;
import tasks.CreateBookingTask;
import tasks.DeleteBookingTasks;
import tasks.GetBookingTask;
import tasks.UpdateBookingTasks;
import org.junit.jupiter.api.TestMethodOrder;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

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


    @Test
    public void test1_createBooking(){

        bookingData.setFirstname(firstname);
        bookingData.setLastname(lastname);
        bookingData.setTotalprice(totalprice);
        bookingData.setDepositpaid(depositpaid);

        BookingDates dates = new BookingDates();
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
    }

    @Test
    public void test2_getBooking(){
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
        Assert.assertEquals(nameGet, bookingData.getFirstname());
    }

    @Test
    public void test3_deleteBooking(){
        Actor user = Actor.named("user")
                .whoCan(CallAnApi.at(baseUrl));

        user.attemptsTo(
                DeleteBookingTasks.deleteBooking(Utils.getBookingId(), Utils.getToken())
        );

        user.should(
                seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(201))
        );
    }




}
