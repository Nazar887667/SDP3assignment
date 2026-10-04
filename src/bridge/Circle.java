package bridge;

public class Circle extends Shape {

    private final int radius;

    public Circle(String id, int radius, Renderer renderer) {
        super(id, renderer);
        this.radius = radius;
    }

    @Override
    public String execute() {
        return renderer().renderCircle(radius);
    }

    @Override
    public String getDimension() {
        return "radius=" + radius;
    }
}
