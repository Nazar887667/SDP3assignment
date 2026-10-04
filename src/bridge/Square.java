package bridge;

public class Square extends Shape {

    private final int side;

    public Square(String id, int side, Renderer renderer) {
        super(id, renderer);
        this.side = side;
    }

    @Override
    public String execute() {
        return renderer().renderSquare(side);
    }

    @Override
    public String getDimension() {
        return "side=" + side;
    }
}
