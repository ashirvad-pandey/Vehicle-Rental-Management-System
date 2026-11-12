import java.util.ArrayList;
import java.util.Scanner;

// ================= VEHICLE =================

abstract class Vehicle {

    private int vehicleId;
    private String brand;
    private String model;
    private int rentPerDay;
    private boolean available;

    Vehicle(int vehicleId, String brand, String model,
            int rentPerDay, boolean available) {

        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.available = available;
    }

    void showDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Rent Per Day: " + rentPerDay);
        System.out.println("Available: " + available);
    }

    int getVehicleId() {
        return vehicleId;
    }

    int getRentPerDay() {
        return rentPerDay;
    }

    boolean isAvailable() {
        return available;
    }

    void setAvailable(boolean available) {
        this.available = available;
    }

    abstract void calculateRent(int days);
}


// ================= INTERFACE =================

interface Rentable {

    void rent();

    void returnVehicle();
}


// ================= CAR =================

class Car extends Vehicle implements Rentable {

    Car() {
        super(101, "Honda", "City", 500, true);
    }

    @Override
    void calculateRent(int days) {
        System.out.println("Car Rent: " + (getRentPerDay() * days));
    }

    @Override
    public void rent() {
        if (isAvailable()) {
            setAvailable(false);
            System.out.println("Car rented successfully.");
        } else {
            System.out.println("Car is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        setAvailable(true);
        System.out.println("Car returned successfully.");
    }
}


// ================= BIKE =================

class Bike extends Vehicle implements Rentable {

    Bike() {
        super(102, "Hero", "Splendor", 300, true);
    }

    @Override
    void calculateRent(int days) {
        System.out.println("Bike Rent: " + (getRentPerDay() * days));
    }

    @Override
    public void rent() {
        if (isAvailable()) {
            setAvailable(false);
            System.out.println("Bike rented successfully.");
        } else {
            System.out.println("Bike is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        setAvailable(true);
        System.out.println("Bike returned successfully.");
    }
}


// ================= SUV =================

class SUV extends Vehicle implements Rentable {

    SUV() {
        super(103, "Toyota", "XUV 700", 700, true);
    }

    @Override
    void calculateRent(int days) {
        System.out.println("SUV Rent: " + (getRentPerDay() * days));
    }

    @Override
    public void rent() {
        if (isAvailable()) {
            setAvailable(false);
            System.out.println("SUV rented successfully.");
        } else {
            System.out.println("SUV is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        setAvailable(true);
        System.out.println("SUV returned successfully.");
    }
}


// ================= CUSTOMER =================

class Customer {

    private int customerId;
    private String name;
    private String phone;

    Customer(int customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
    }

    int getCustomerId() {
        return customerId;
    }

    String getName() {
        return name;
    }

    void showCustomerDetails() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
    }
}


// ================= RENTAL =================

class Rental {

    private int rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int days;

    Rental(int rentalId, Customer customer, Vehicle vehicle, int days) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    void showRentalDetails() {

        System.out.println("\n----- Rental Details -----");

        System.out.println("Rental ID: " + rentalId);

        System.out.println("\nCustomer:");
        customer.showCustomerDetails();

        System.out.println("\nVehicle:");
        vehicle.showDetails();

        System.out.println("Rental Days: " + days);

        System.out.println("Total Rent: "
                + (vehicle.getRentPerDay() * days));
    }

    Vehicle getVehicle() {
        return vehicle;
    }
}


// ================= VEHICLE RENTAL SYSTEM =================

class VehicleRentalSystem {

    private ArrayList<Vehicle> vehicles;
    private ArrayList<Rental> rentals;

    VehicleRentalSystem() {
        vehicles = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    // Add Vehicle
    void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    // Show Vehicles
    void showVehicles() {

        System.out.println("\n===== AVAILABLE VEHICLES =====");

        for (Vehicle vehicle : vehicles) {
            vehicle.showDetails();
            System.out.println("----------------------------");
        }
    }

    // Find Vehicle
    Vehicle findVehicle(int vehicleId) {

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleId() == vehicleId) {
                return vehicle;
            }
        }

        return null;
    }

    // Rent Vehicle
    void rentVehicle(int vehicleId, Customer customer, int days) {

        Vehicle vehicle = findVehicle(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is already rented.");
            return;
        }

        vehicle.setAvailable(false);

        int rentalId = rentals.size() + 1;

        Rental rental = new Rental(
                rentalId,
                customer,
                vehicle,
                days
        );

        rentals.add(rental);

        System.out.println("Vehicle rented successfully.");

        System.out.println("Total Rent: "
                + (vehicle.getRentPerDay() * days));
    }

    // Return Vehicle
    void returnVehicle(int vehicleId) {

        Vehicle vehicle = findVehicle(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (vehicle.isAvailable()) {
            System.out.println("Vehicle is already available.");
            return;
        }

        vehicle.setAvailable(true);

        System.out.println("Vehicle returned successfully.");
    }

    // Show Rental Details
    void showRentalDetails() {

        if (rentals.isEmpty()) {
            System.out.println("No rental records found.");
            return;
        }

        for (Rental rental : rentals) {
            rental.showRentalDetails();
        }
    }
}


// ================= MAIN =================

public class Project {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        VehicleRentalSystem system = new VehicleRentalSystem();

        // Add Vehicles
        system.addVehicle(new Car());
        system.addVehicle(new Bike());
        system.addVehicle(new SUV());

        // Customer
        Customer customer = new Customer(
                1,
                "Ashirwad Pandey",
                "9876543210"
        );

        while (true) {

            System.out.println("\n======================================");
            System.out.println("   VEHICLE RENTAL MANAGEMENT SYSTEM");
            System.out.println("======================================");

            System.out.println("1. Show Vehicles");
            System.out.println("2. Rent Vehicle");
            System.out.println("3. Return Vehicle");
            System.out.println("4. Show Rental Details");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    system.showVehicles();

                    break;


                case 2:

                    System.out.print("Enter Vehicle ID: ");
                    int vehicleId = sc.nextInt();

                    System.out.print("Enter Rental Days: ");
                    int days = sc.nextInt();

                    system.rentVehicle(
                            vehicleId,
                            customer,
                            days
                    );

                    break;


                case 3:

                    System.out.print("Enter Vehicle ID: ");
                    int returnId = sc.nextInt();

                    system.returnVehicle(returnId);

                    break;


                case 4:

                    system.showRentalDetails();

                    break;


                case 5:

                    System.out.println(
                            "Thank you for using Vehicle Rental System."
                    );

                    sc.close();

                    return;


                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }
}