package bridge;

public class RasterRenderer implements Renderer {

    private static final String SUFFIX = " (pixels)";

    @Override
    public String renderCircle(int radius) {
        return "RASTER circle radius=" + radius + SUFFIX;
    }

    @Override
    public String renderSquare(int side) {
        return "RASTER square side=" + side + SUFFIX;
    }
}
