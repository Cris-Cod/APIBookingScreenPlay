package tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetBookingTask implements Task {

    private final int id;

    public GetBookingTask(int id) {
        this.id = id;
    }

    public static Performable idBooking(int id) {
        return instrumented(GetBookingTask.class, id);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("booking/{id}").with(RequestSpecification -> RequestSpecification
                        .log().all()
                        .header("Accept", "application/json")
                        .pathParam("id", id))
        );

        SerenityRest.lastResponse().then().log().all();
    }
}
