
import java.util.Scanner;
class TravelPackage {
    String customerName;
    String destination;
    double cost;

    TravelPackage(String customerName, String destination) {
        this.customerName = customerName;
        this.destination = destination;
    }

    void calculateCost() {
        cost = 0;
    }

    void displayDetails() {
        System.out.println("Customer Name : " + customerName);
        System.out.println("Destination   : " + destination);
        System.out.println("Cost          : Rs. " + cost);
    }

    double applyDiscount() {
        if (cost >= 5000) {
            return cost * 0.10; // 10% discount
        }
        return 0;
    }
}
class TravelByLand extends TravelPackage {
    String mode;

    TravelByLand(String name, String destination, String mode) {
        super(name, destination);
        this.mode = mode;
    }

    @Override
    void calculateCost() {
        if (mode=="Train") {
            cost = 2000;
        } else {
            cost = 1500;
        }
    }

    @Override
    void displayDetails() {
        calculateCost();
        double discount = applyDiscount();
        System.out.println("\n--- TRAVEL BY LAND PACKAGE ---");
        super.displayDetails();
        System.out.println("Travel Mode   : " + mode);
        System.out.println("Discount      : Rs. " + discount);
        System.out.println("Final Amount  : Rs. " + (cost - discount));
    }
}

class FlightPackage extends TravelPackage {
    String flightClass;

    FlightPackage(String name, String destination, String flightClass) {
        super(name, destination);
        this.flightClass = flightClass;
    }

    @Override
    void calculateCost() {
        if (flightClass=="Economy")
            cost = 6000;
        else
            cost = 9000;
    }

    @Override
    void displayDetails() {
        calculateCost();
        double discount = applyDiscount();
        System.out.println("\n--- FLIGHT PACKAGE ---");
        super.displayDetails();
        System.out.println("Flight Class  : " + flightClass);
        System.out.println("Discount      : Rs. " + discount);
        System.out.println("Final Amount  : Rs. " + (cost - discount));
    }
}

class HotelPackage extends TravelPackage {
    int nights;

    HotelPackage(String name, String destination, int nights) {
        super(name, destination);
        this.nights = nights;
    }

    @Override
    void calculateCost() {
        cost = nights * 2000;
    }

    @Override
    void displayDetails() {
        calculateCost();
        double discount = applyDiscount();
        System.out.println("\n--- HOTEL PACKAGE ---");
        super.displayDetails();
        System.out.println("Nights        : " + nights);
        System.out.println("Discount      : Rs. " + discount);
        System.out.println("Final Amount  : Rs. " + (cost - discount));
    }
}

class TourPackage extends TravelPackage {
    int days;
    boolean guideIncluded;

    TourPackage(String name, String destination, int days, boolean guideIncluded) {
        super(name, destination);
        this.days = days;
        this.guideIncluded = guideIncluded;
    }

    @Override
    void calculateCost() {
        cost = days * 1500;
        if (guideIncluded) {
            cost += 2000;
        }
    }

    @Override
    void displayDetails() {
        calculateCost();
        double discount = applyDiscount();
        System.out.println("\n--- TOUR PACKAGE ---");
        super.displayDetails();
        System.out.println("Days          : " + days);
        System.out.println("Guide Included: " + guideIncluded);
        System.out.println("Discount      : Rs. " + discount);
        System.out.println("Final Amount  : Rs. " + (cost - discount));
    }
}

class TravelBookingSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TravelPackage[] bookings = new TravelPackage[10];
        int count = 0;

        String[] destinations = {"Delhi", "Manali", "Goa", "Jaipur"};

        while (true) {
            System.out.println("\n-------------------------------");
            System.out.println("   TRAVEL BOOKING SYSTEM");
            System.out.println("-------------------------------");
            System.out.println("1. Book Package");
            System.out.println("2. View Bookings");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 4) {
                System.out.println("Thank you!");
                break;
            }

            switch (choice) {

                case 1:
                    System.out.print("Customer Name: ");
                    String name = sc.nextLine();

                    System.out.println("Select Destination:");
                    for (int i = 0; i < destinations.length; i++) {
                        System.out.println((i + 1) + ". " + destinations[i]);
                    }
                    int d = sc.nextInt();
                    sc.nextLine();
                    String dest = destinations[d - 1];

                    System.out.println("Select Package:");
                    System.out.println("1. Travel (Bus/Train)");
                    System.out.println("2. Flight");
                    System.out.println("3. Hotel");
                    System.out.println("4. Tour");
                    int p = sc.nextInt();
                    sc.nextLine();

                    if (p == 1) {
                        System.out.print("Mode (Bus/Train): ");
                        String mode = sc.nextLine();
                        bookings[count++] = new TravelByLand(name, dest, mode);

                    } else if (p == 2) {
                        System.out.print("Flight Class (Economy/Business): ");
                        String fc = sc.nextLine();
                        bookings[count++] = new FlightPackage(name, dest, fc);

                    } else if (p == 3) {
                        System.out.print("Nights: ");
                        int n = sc.nextInt();
                        bookings[count++] = new HotelPackage(name, dest, n);

                    } else if (p == 4) {
                        System.out.print("Days: ");
                        int days = sc.nextInt();
                        System.out.print("Guide Included (true/false): ");
                        boolean guide = sc.nextBoolean();
                        bookings[count++] = new TourPackage(name, dest, days, guide);
                    }

                    System.out.println("✔ Booking Successful!");
                    break;

                case 2:
                    if (count == 0) {
                        System.out.println("No bookings found!");
                    } else {
                        for (int i = 0; i < count; i++) {
                            bookings[i].displayDetails();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter booking number to cancel: ");
                    int c = sc.nextInt();
                    if (c > 0 && c <= count) {
                        for (int i = c - 1; i < count - 1; i++) {
                            bookings[i] = bookings[i + 1];
                        }
                        count--;
                        System.out.println("Booking Cancelled");
                    } else {
                        System.out.println("Invalid number");
                    }
                    break;
            }
        }
        sc.close();
    }
}

