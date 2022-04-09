package exercises.s11;

class Size {
}

class Wheels {
}

class Engine {
}

abstract class Vehicle {
    String color;
    int speed;
    Size size;
    Engine engine;
    Wheels wheels;

    public Vehicle(String color, int speed, Size size, Engine engine, Wheels wheels) {
        this.color = color;
        this.speed = speed;
        this.size = size;
        this.engine = engine;
        this.wheels = wheels;
    }

    public void vehicleAttributes() {
        System.out.println("Color : " + color);
        System.out.println("Speed : " + speed);
        System.out.println("Size : " + size.toString());
        System.out.println("Engine : " + engine.toString());
    }
}
