package exercises.s11;

class Car extends Vehicle {
    String model;
    int seats;

    public Car(String model, int seats, String color, int speed, Size size, Engine engine, Wheels wheels) {
        super(color, speed, size, engine, wheels);
        this.seats = seats;
        this.model = model;
    }
}

// co jeżeli mamy statek?
class Ship extends Vehicle {
    String model;
    int seats;
    String type;

    public Ship(String model, int seats, String color, int speed, Size size, Engine engine) {
        super(color, speed, size, engine, null); // koła? chyba ze ratunkowe...
        this.seats = seats;
        this.model = model;
        this.type = "yacht";
    }
}
