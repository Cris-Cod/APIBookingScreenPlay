package booking;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import models.booking.BookingDates;
import models.booking.CreateBookingData;
import questions.ResponseCode;
import tasks.UpdateBookingTasks;


import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "src/test/resources/updateBooking.csv")
public class Test3_UpdateBookingTest {

    private static final String baseUrl = "https://restful-booker.herokuapp.com/";
    private static int id;

    private static String token;
    private JsonPath js;
    private String firstname;
    private String lastaname;
    private int totalprice;
    private boolean depositpaid;
    private String checkin;
    private String checkout;
    private String additionalneeds;

    CreateBookingData updateData = new CreateBookingData();
    Test2_CreateBookingTest test2CreateBookingTest = new Test2_CreateBookingTest();
    Test1_CreateToken test1CreateToken = new Test1_CreateToken();



    @Test
    public void test3_updateBooking() {



        id = Utils.getBookingId();
        token = test1CreateToken.token;
        updateData.setFirstname(firstname);
        updateData.setLastname(lastaname);
        updateData.setTotalprice(totalprice);
        updateData.setDepositpaid(depositpaid);

        BookingDates dates = new BookingDates();
        dates.setCheckin(checkin);
        dates.setCheckout(checkout);
        updateData.setBookingdates(dates);

        updateData.setAdditionalneeds(additionalneeds);

        Actor user = Actor.named("user")
                .whoCan(CallAnApi.at(baseUrl));

        user.attemptsTo(
                UpdateBookingTasks.update(updateData, "6d5989b2248c3f3", id)
        );

        user.should(
                seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200))
        );
    }

}
