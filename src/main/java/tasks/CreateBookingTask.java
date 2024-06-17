package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import models.booking.CreateBookingData;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateBookingTask implements Task {

    private final CreateBookingData bookingData;
    public CreateBookingTask(CreateBookingData bookingData) {
        this.bookingData = bookingData;
    }

    public static Performable data(CreateBookingData bookingData){
        return instrumented(CreateBookingTask.class, bookingData);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("booking").with(requestSpecification -> requestSpecification
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(bookingData))
        );
        SerenityRest.lastResponse().then().log().all();
    }
}
