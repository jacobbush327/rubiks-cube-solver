public class Main {

    private static RubiksCube cube;

    public static void createEmptyCube() {
        cube = new RubiksCube();
    }

    public static void main(String[] args) {
        Main.createEmptyCube();
        
        cube.populateColors(null);

        System.out.println(cube.solveCube());
    }

}