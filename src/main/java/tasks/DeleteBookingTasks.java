package tasks;


import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteBookingTasks implements Task {

    private final int idBooking;
    private final String idToken;

    public DeleteBookingTasks(int idBooking, String idToken) {
        this.idBooking = idBooking;
        this.idToken = idToken;
    }

    public static Performable deleteBooking(int idBooking, String idToken){
        return instrumented(DeleteBookingTasks.class, idBooking, idToken);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("booking/"+idBooking+"")
                        .with(RequestSpecification ->  RequestSpecification
                                .log().all()
                                .header("Content-Type","application/json")
                                .header("Cookie", "token="+idToken))
        );
        SerenityRest.lastResponse().then().log().all();
    }
}
