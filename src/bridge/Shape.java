package bridge;

import java.util.Objects;

public abstract class Shape {

    private final String id;
    private Renderer renderer;

    protected Shape(String id, Renderer renderer) {
        this.id = Objects.requireNonNull(id, "id");
        this.renderer = Objects.requireNonNull(renderer, "renderer");
    }

    public abstract String execute();

    public void setImplementation(Renderer newRenderer) {
        this.renderer = Objects.requireNonNull(newRenderer, "renderer");
    }

    public String getId() {
        return id;
    }

    public abstract String getDimension();

    protected Renderer renderer() {
        return renderer;
    }
}
