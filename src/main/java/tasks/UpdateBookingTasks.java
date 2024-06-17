package tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import models.booking.CreateBookingData;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateBookingTasks implements Task {


    private final CreateBookingData updateData;
    private final String idToken;
    private final int idBooking;

    public UpdateBookingTasks(CreateBookingData updateData, String idToken, int idBooking) {
        this.updateData = updateData;
        this.idToken = idToken;
        this.idBooking = idBooking;
    }

    public static Performable update(CreateBookingData updateData, String idToken, int idBooking){
        return instrumented(UpdateBookingTasks.class, updateData, idToken, idBooking);
    }



    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("booking/{id}").with(RequestSpecification -> RequestSpecification
                        .log().all()
                        .header("Content-Type","application/json")
                        .header("Accept", "application/json")
                        .header("Cookie", "token="+idToken)
                        .header("Authorization", "YWRtaW46cGFzc3dvcmQxMjM=")
                        .pathParam("id", idBooking)
                        .body(updateData)
                )
        );
        SerenityRest.lastResponse().then().log().all();
    }
}
