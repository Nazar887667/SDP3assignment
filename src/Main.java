import bridge.AsciiRenderer;
import bridge.Circle;
import bridge.RasterRenderer;
import bridge.Renderer;
import bridge.Shape;
import bridge.Square;
import bridge.VectorRenderer;
import java.util.function.Function;

/** Client: runs the demonstration checks against the real Bridge classes. */
public class Main {

    private static final int RADIUS = 2;
    private static final int SIDE = 3;

    private static int total = 0;
    private static int passed = 0;

    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--demo")) {
            runDemo();
        } else {
            System.out.println("Usage: java -cp out Main --demo");
        }
    }

    private static void runDemo() {
        Function<Renderer, Shape> circle = r -> new Circle("C-1", RADIUS, r);
        Function<Renderer, Shape> square = r -> new Square("S-1", SIDE, r);
        checkCombination("T1", circle, new VectorRenderer(), "VECTOR circle radius=2");
        checkCombination("T2", circle, new RasterRenderer(), "RASTER circle radius=2 (pixels)");
        checkCombination("T3", square, new VectorRenderer(), "VECTOR square side=3");
        checkCombination("T4", square, new RasterRenderer(), "RASTER square side=3 (pixels)");
        checkRuntimeSwitch("T5");
        checkCombination("T6", circle, new AsciiRenderer(), "ASCII circle radius=2 (characters)");
        checkCombination("T7", square, new AsciiRenderer(), "ASCII square side=3 (characters)");
        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }

    /** Builds a shape with a renderer, runs it and compares with the expected text. */
    private static void checkCombination(String id, Function<Renderer, Shape> factory,
                                         Renderer renderer, String expected) {
        Shape shape = factory.apply(renderer);
        String actual = shape.execute();
        String classes = shape.getClass().getSimpleName() + " + "
                + renderer.getClass().getSimpleName();
        record(id, actual.equals(expected), classes, "result=" + actual, "result=" + expected);
    }

    /** Swaps the renderer on one Circle and proves identity, data and result changes. */
    private static void checkRuntimeSwitch(String id) {
        Circle original = new Circle("C-5", RADIUS, new VectorRenderer());
        String idBefore = original.getId();
        String dataBefore = original.getDimension();
        String before = original.execute();

        Shape afterSwitch = switchRenderer(original, new RasterRenderer());
        String after = afterSwitch.execute();

        boolean sameObject = original == afterSwitch;
        boolean stateUnchanged = idBefore.equals(afterSwitch.getId())
                && dataBefore.equals(afterSwitch.getDimension());
        boolean resultsOk = before.equals("VECTOR circle radius=2")
                && after.equals("RASTER circle radius=2 (pixels)");

        String actual = "sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged
                + "\n     before=" + before + " | after=" + after;
        String expected = "sameObject=true | stateUnchanged=true"
                + "\n     before=VECTOR circle radius=2 | after=RASTER circle radius=2 (pixels)";
        record(id, sameObject && stateUnchanged && resultsOk, "Circle", actual, expected);
    }

    private static Shape switchRenderer(Shape shape, Renderer newRenderer) {
        shape.setImplementation(newRenderer);
        return shape;
    }

    private static void record(String id, boolean pass, String classes, String actual,
                               String expected) {
        total++;
        if (pass) {
            passed++;
        }
        System.out.println(id + (pass ? " PASS" : " FAIL") + " | " + classes + " | " + actual);
        if (!pass) {
            System.out.println("     expected: " + expected);
        }
    }
}
