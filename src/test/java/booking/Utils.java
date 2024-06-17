package booking;

public class Utils {

    private static int bookingId;

    public static int getBookingId() {
        return bookingId;
    }

    public static void setBookingId(int bookingId) {
        Utils.bookingId = bookingId;
    }
}
