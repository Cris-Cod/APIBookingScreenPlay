package booking;

import dataUtil.ReadExcel;
import io.restassured.path.json.JsonPath;
import models.booking.BookingDates;
import models.booking.CreateBookingData;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import questions.ResponseCode;
import tasks.CreateBookingTask;
import tasks.UpdateBookingTasks;

import java.io.IOException;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class Test_Request_bookings {
    /*
    private static List<CreateBookingData> createBookings;
    private static List<CreateBookingData> updateBookings;
    private static final String baseUrl = "https://restful-booker.herokuapp.com/";
    private JsonPath js;

    @BeforeClass
    public static void files() throws IOException {
        createBookings = ReadExcel.readCsv("src/test/resources/createBooking.csv");
        updateBookings = ReadExcel.readCsv("src/test/resources/createBooking.csv");
    }

    @Test
    public void test1_createBooking(){

        Actor user = Actor.named("user")
                .whoCan(CallAnApi.at(baseUrl));

        user.attemptsTo(
                CreateBookingTask.data((CreateBookingData) createBookings)
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
        Assert.assertEquals(name, ((CreateBookingData) createBookings).getFirstname());
    }

    @Test
    public void test4_UpdateBooking() {
        int id = Utils.getBookingId();
        String token = "c3d65801ca17109";


        Actor user = Actor.named("user")
                .whoCan(CallAnApi.at(baseUrl));

        user.attemptsTo(
                UpdateBookingTasks.update((CreateBookingData) updateBookings, token, id)
        );

        user.should(
                seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200))
        );
    }

     */

}
