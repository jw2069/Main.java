import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    

    private static final String FILE_NAME = "bookings.csv";

    // ================= ADD BOOKING =================
    public static String addBooking(String surname,
                                    String placementDateTime,
                                    String vehicleType,
                                    int luggageCount,
                                    String bookingDateTime,
                                    double cost) {

        int nextId = getNextBookingNumber();
        String bookingRef = String.format("BR%03d", nextId);

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {

            writer.write(
                bookingRef + "," +
                surname + "," +
                placementDateTime + "," +
                vehicleType + "," +
                luggageCount + "," +
                bookingDateTime + "," +
                cost + ",false\n"
            );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bookingRef;
    }

    // ================= GET BOOKING =================
    public static Booking getBooking(String bookingRef, String surname) {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            reader.readLine(); // header

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String ref = data[0];
                String fileSurname = data[1];

                if (ref.equals(bookingRef) &&
                    fileSurname.equalsIgnoreCase(surname)) {

                    return new Booking(
                        ref,
                        fileSurname,
                        data[2], // placementDateTime
                        data[3], // vehicleType
                        Integer.parseInt(data[4]),
                        data[5], // bookingDateTime
                        Double.parseDouble(data[6]),
                        Boolean.parseBoolean(data[7])
                    );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static boolean hasBookingPassed(String bookingID) {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data[0].equals(bookingID)) {

                    LocalDateTime bookingTime = LocalDateTime.parse(data[5]);

                    return LocalDateTime.now().isAfter(bookingTime);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static double getCancellationFee(String bookingID) {

    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

        String line;
        reader.readLine();

        while ((line = reader.readLine()) != null) {

            String[] data = line.split(",");

            if (data[0].equals(bookingID)) {

                LocalDateTime bookingTime = LocalDateTime.parse(data[5]);

                if (LocalDateTime.now().plusHours(36).isAfter(bookingTime)) {
                    return Double.parseDouble(data[6]) * 0.1;
                } else {
                    return 0.0;
                }
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    return 0.0;
}

    // ================= CANCEL BOOKING =================
    public static String cancelBooking(String bookingID, String surname) {

        List<String> updatedLines = new ArrayList<>();
        boolean found = false;
        boolean alreadyCancelled = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            String header = reader.readLine();
            updatedLines.add(header);

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String ref = data[0];
                String fileSurname = data[1];
                boolean isCancelled = Boolean.parseBoolean(data[7]);

                if (ref.equals(bookingID) &&
                    fileSurname.equalsIgnoreCase(surname)) {

                    found = true;

                    if (isCancelled) {
                        alreadyCancelled = true;
                        updatedLines.add(line);
                    } else {
                        data[7] = "true"; // mark cancelled
                        updatedLines.add(String.join(",", data));
                    }

                } else {
                    updatedLines.add(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        if (!found) return "not found";
        if (alreadyCancelled) return "already cancelled";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (String l : updatedLines) {
                writer.write(l);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        return "success";
    }

    // ================= GET STATUS =================
    public static String getStatus(String bookingID) {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data[0].equals(bookingID)) {
                    return Boolean.parseBoolean(data[7]) ? "CANCELLED" : "ACTIVE";
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "not found";
    }

    // ================= NEXT ID =================
    private static int getNextBookingNumber() {

        int max = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String ref = line.split(",")[0];

                int num = Integer.parseInt(ref.substring(2));

                if (num > max) {
                    max = num;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return max + 1;
    }
}