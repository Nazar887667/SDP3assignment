# Assignment 3 | Bridge Pattern (ShP-2216)

- **Name:** Baktybay Nazar
- **Group:** SE-2530
- **Topic:** A (Drawing: Shape / Renderer)
- **Repository:** https://github.com/Nazar887667/SDP3assignment.git
- **Base commit (working I1/I2 version):** `914ec7a5e65d97e3fe7734f4803d2fa2a94ee32a`
- **I3 extension commit:** `775fb4e3abfc556dbbce453c3005970499ca1dcc` (final documentation commit hash is given in the Moodle text)

## Role map

| Role | Class | Source path |
|---|---|---|
| Abstraction | `Shape` | src/bridge/Shape.java |
| A1 | `Circle` (radius 2) | src/bridge/Circle.java |
| A2 | `Square` (side 3) | src/bridge/Square.java |
| Implementor | `Renderer` | src/bridge/Renderer.java |
| I1 | `VectorRenderer` | src/bridge/VectorRenderer.java |
| I2 | `RasterRenderer` | src/bridge/RasterRenderer.java |
| I3 (extension) | `AsciiRenderer` | src/bridge/AsciiRenderer.java |
| Client | `Main` | src/Main.java |

## Where to look

- **Bridge field:** `private Renderer renderer;` in `Shape` (set in the constructor).
- **`execute()`:** abstract in `Shape`; implemented in `Circle` and `Square`, each delegating to `renderer()`.
- **`setImplementation(...)`:** `Shape.setImplementation(Renderer)`.
- **T5 check:** `Main.checkRuntimeSwitch` (compares `original == afterSwitch`).

## Build and run

```
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

## Expected results

| Check | Expected |
|---|---|
| T1 | Circle + VectorRenderer: `VECTOR circle radius=2` |
| T2 | Circle + RasterRenderer: `RASTER circle radius=2 (pixels)` |
| T3 | Square + VectorRenderer: `VECTOR square side=3` |
| T4 | Square + RasterRenderer: `RASTER square side=3 (pixels)` |
| T5 | sameObject=true, stateUnchanged=true, before `VECTOR circle radius=2`, after `RASTER circle radius=2 (pixels)` |
| T6 | Circle + AsciiRenderer: `ASCII circle radius=2 (characters)` |
| T7 | Square + AsciiRenderer: `ASCII square side=3 (characters)` |

Final line: `SUMMARY: 7/7 PASS`. Captured output is in `demo-output.txt`; the I3 change is in `extension.diff` (`git diff 914ec7a5e65d97e3fe7734f4803d2fa2a94ee32a HEAD -- src`).
