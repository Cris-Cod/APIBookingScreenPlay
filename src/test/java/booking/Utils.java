package booking;

public class Utils {

    private static int bookingId;
    private static String token;

    public static int getBookingId() {
        return bookingId;
    }

    public static void setBookingId(int bookingId) {
        Utils.bookingId = bookingId;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Utils.token = token;
    }
}
