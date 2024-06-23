package dataUtil;

import au.com.bytecode.opencsv.CSVReader;
import models.booking.BookingDates;
import models.booking.CreateBookingData;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RunWith(SerenityRunner.class)
public class ReadExcel {
/*
    public static List<CreateBookingData> readCsv(String filePath) throws IOException {
        List<CreateBookingData> bookings = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values;
            boolean header = true;
            while ((values = csvReader.readNext()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                String firstName = values[0];
                String lastName = values[1];
                int totalPrice = Integer.parseInt(values[2]);
                boolean depositPaid = Boolean.parseBoolean(values[3]);
                String checkin = values[4];
                String checkout = values[5];
                String additionalneeds = values[6];

                BookingDates dates = new BookingDates();
                dates.setCheckin(checkin);
                dates.setCheckout(checkout);

                CreateBookingData booking = new CreateBookingData(firstName, lastName, totalPrice, depositPaid, dates, additionalneeds);
                bookings.add(booking);


            }
        }

        return bookings;
    }

 */
}
