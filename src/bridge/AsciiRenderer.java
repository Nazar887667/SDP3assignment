package bridge;

public class AsciiRenderer implements Renderer {

    private static final String SUFFIX = " (characters)";

    @Override
    public String renderCircle(int radius) {
        return "ASCII circle radius=" + radius + SUFFIX;
    }

    @Override
    public String renderSquare(int side) {
        return "ASCII square side=" + side + SUFFIX;
    }
}
