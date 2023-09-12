import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Testing {
    // white down, green forward
    private String unscrambledCube = "yyyyyyyyygggooobbbrrrgggooobbbrrrgggooobbbrrrwwwwwwwww";

    @Test
    public void populateWithDefaultColors() {
        RubiksCube cube = new RubiksCube();

        cube.populateColors(unscrambledCube);

        assertTrue(unscrambledCube.contentEquals(cube.currentOrientation()));
    }

    @Test
    public void testB() {
        RubiksCube cube = new RubiksCube();

        cube.populateColors(unscrambledCube);

        cube.B();

        String expected = "oooyyyyyygggoowbbbyrrgggoowbbbyrrgggoowbbbyrrwwwwwwrrr";

        assertTrue(expected.contentEquals(cube.currentOrientation()));
    }

    @Test
    public void testR() {
        RubiksCube cube = new RubiksCube();

        cube.populateColors(unscrambledCube);

        cube.R();

        String expected = "yygyygyygggwoooybbrrrggwoooybbrrrggwoooybbrrrwwbwwbwwb";

        assertTrue(expected.contentEquals(cube.currentOrientation()));
    }

    @Test
    public void testRUR() {
        RubiksCube cube = new RubiksCube();

        cube.populateColors(unscrambledCube);

        cube.R();
        cube.U();
        cube.R();

        String expected = "yyoyywggwoobooygrrggwggboobybbrrrggboobybbrrrwwywwywwr";

        assertTrue(expected.contentEquals(cube.currentOrientation()));
    }

    @Test
    public void testLFL() {
        RubiksCube cube = new RubiksCube();

        cube.populateColors(unscrambledCube);

        cube.L();
        cube.F();
        cube.L();

        String expected = "wyywyywrrbyyboobbgrrrbggyoobbgrrrrggyoobbowwgyoogwwgww";

        assertTrue(expected.contentEquals(cube.currentOrientation()));
    }

    @Test
    public void allTurnInstructions() {
        RubiksCube cube = new RubiksCube();

        cube.populateColors(unscrambledCube);

        // scramble cube
        cube.U();
        cube.R();
        cube.D();
        cube.F();
        cube.L();
        cube.B();
        cube.Up();
        cube.Rp();
        cube.Dp();
        cube.Fp();
        cube.Lp();
        cube.Bp();

        // Rubiks Cube after U R D F L B Up Rp Dp Fp Lp Bp (white down, green forward)
        String newOrientation = "oyyryoogoyrgwbgobwbbbogyoogobwrrwryygwgwwbyybwgrrwbrgr";

        StringBuffer actualOrientation = cube.currentOrientation();

        assertTrue(newOrientation.contentEquals(actualOrientation));
    }

}
