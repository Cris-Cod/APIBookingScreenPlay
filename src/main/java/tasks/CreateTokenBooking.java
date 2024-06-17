package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import models.booking.CreateTokenData;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateTokenBooking implements Task {

    private final CreateTokenData tokenData;

    public CreateTokenBooking(CreateTokenData tokenData) {
        this.tokenData = tokenData;
    }

    public static Performable data(CreateTokenData tokenData){
        return instrumented(CreateTokenBooking.class, tokenData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("auth").with(requestSpecification -> requestSpecification
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(tokenData))
        );
        SerenityRest.lastResponse().then().log().all();
    }
}
