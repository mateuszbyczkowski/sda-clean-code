package exercises.s11;

class Trailer {
    // przyczepa
}

class Truck {
    Trailer trailer;
    String model;
    String color;
    int speed;
    Size size;
    Engine engine;
    Wheels wheels;
    int seats;

    public Truck(Trailer trailer, String model, String color, int speed, Size size, Engine engine, Wheels wheels, int seats) {
        this.trailer = trailer;
        this.model = model;
        this.color = color;
        this.speed = speed;
        this.size = size;
        this.engine = engine;
        this.wheels = wheels;
        this.seats = seats;
    }
}

class CruiseShip {
    String model;
    String color;
    int speed;
    Size size;
    Engine engine;
    int seats;

    public CruiseShip(Trailer trailer, String model, String color, int speed, Size size, Engine engine, Wheels wheels, int seats) {
        this.model = model;
        this.color = color;
        this.speed = speed;
        this.size = size;
        this.engine = engine;
        this.seats = seats;
    }
}