import java.util.HashMap;
import java.util.Scanner;

/*
 * Class to represent a Rubiks Cube
 */
public class RubiksCube {

    // represent cube as a map where each position is represented by a number
    // and each color is represented by a char
    private HashMap<Integer, Character> cube;

    private char topColor;
    private char frontColor;
    private char leftColor;
    private char rightColor;
    private char bottomColor;
    private char backColor;

    // Create an empty Rubiks Cube
    public RubiksCube() {
        cube = new HashMap<Integer, Character>();
    }

    // Get the color arrangement of the cube from the user
    public void populateColors(String populateWith) {

        String colorInput;

        if (populateWith == null) {
            System.out.println("Enter color scheme of Rubiks Cube (see read me for configurations)");

            Scanner colorGrabber = new Scanner(System.in);

            // get user to input the arrangement of the current cube
            colorInput = colorGrabber.nextLine();

            colorGrabber.close();
        } else {
            colorInput = populateWith;
        }

        // populate each position with the color given by the user
        for (int i = 1; i <= 54; i++) {
            cube.put(i, colorInput.charAt(i - 1)); // parse chars out of the user string
        }

        topColor = colorInput.charAt(4);
        frontColor = colorInput.charAt(22);
        rightColor = colorInput.charAt(25);  
        backColor = colorInput.charAt(28);    
        leftColor = colorInput.charAt(31);
        bottomColor = colorInput.charAt(49);
    }

    public StringBuffer solveCube() {
        StringBuffer solveInstructions = new StringBuffer();

        if (cubeSolved()) {
            solveInstructions.append("The cube was already solved!");
            return solveInstructions;
        }

        solveInstructions.append(solveBottomCross());
        System.out.println("Bottom Cross: " + solveInstructions);
        solveInstructions.append(solveBottomCorners());    
        System.out.println("Bottom Corners: " + solveInstructions);
        solveInstructions.append(solveMiddleEdges());
        System.out.println("Middle Edges: " + solveInstructions);
        solveInstructions.append(solveTopCross());
        System.out.println("Top Cross: " + solveInstructions);
        solveInstructions.append(solveTopCorners());
        System.out.println("Top Courners: " + solveInstructions);
        solveInstructions.append(orientTopCorners());
        System.out.println("Orient Top Corners: " + solveInstructions);
        solveInstructions.append(orientTopEdges());
        System.out.println("Orient Top Edge: " + solveInstructions);
        solveInstructions.append(orientTopLayer());
        System.out.println("Orient Top Layer: " + solveInstructions);
        return solveInstructions;
    }

    private StringBuffer solveBottomCross() {
        StringBuffer algorithm = new StringBuffer();

        algorithm.append(solveFrontBottomEdge(frontColor, bottomColor));
        D();
        algorithm.append("D ");
        algorithm.append(solveFrontBottomEdge(leftColor, bottomColor));
        D();
        algorithm.append("D ");
        algorithm.append(solveFrontBottomEdge(backColor, bottomColor));
        D();
        algorithm.append("D ");
        algorithm.append(solveFrontBottomEdge(rightColor, bottomColor));
        D();
        algorithm.append("D ");        
        
        return algorithm;
    }

    /* 
     *  The procedure here is to navigate through each possible edge
     *  permutation for the desired edge, and then perform the proper
     *  algorithm to insert the edge into the 41, 53 position.
     */
    private StringBuffer solveFrontBottomEdge(char frontColor, char bottomColor) {
        StringBuffer algorithm = new StringBuffer();

        if (cube.get(8) == bottomColor && cube.get(11) == frontColor) {
            F();                // searching top edges
            F();
            algorithm.append("F F ");
        } else if (cube.get(11) == bottomColor && cube.get(8) == frontColor) {
            Up();
            Rp();
            F();
            R();
            algorithm.append("U' R' F R ");
        } else if (cube.get(6) == bottomColor && cube.get(14) == frontColor) {
            U();
            F();
            F();
            algorithm.append("U F F ");
        } else if (cube.get(14) == bottomColor && cube.get(6) == frontColor) {
            Rp();
            F();
            R();
            algorithm.append("R' F R ");
        } else if (cube.get(2) == bottomColor && cube.get(17) == frontColor) {
            U();
            U();
            F();
            F();
            algorithm.append("U U F F ");
        } else if (cube.get(17) == bottomColor && cube.get(2) == frontColor) {
            U();
            Rp();
            F();
            R();
            algorithm.append("U R' F R ");
        } else if (cube.get(4) == bottomColor && cube.get(20) == frontColor) {
            Up();
            F();
            F();
            algorithm.append("U' F F ");
        } else if (cube.get(20) == bottomColor && cube.get(4) == frontColor) { 
            L();
            F();            // last possibility for top edges
            Lp();
            algorithm.append("L F L' ");
        } else if (cube.get(33) == bottomColor && cube.get(22) == frontColor) {
            Fp();           // begin middle edges
            algorithm.append("F' ");
        } else if (cube.get(22) == bottomColor && cube.get(33) == frontColor) {
            Lp();
            Up();
            L();
            F();
            F();
            algorithm.append("L' U' L F F ");
        } else if (cube.get(25) == bottomColor && cube.get(24) == frontColor) {
            F();
            algorithm.append("F ");
        } else if (cube.get(24) == bottomColor && cube.get(25) == frontColor) {
            R();
            U();
            Rp();
            F();
            F();
            algorithm.append("R U R' F F ");
        } else if (cube.get(28) == bottomColor && cube.get(27) == frontColor) {
            D();
            R();
            Dp();
            algorithm.append("D R D' ");
        } else if (cube.get(27) == bottomColor && cube.get(28) == frontColor) {
            R();
            R();
            F();
            R();
            R();
            algorithm.append("R R F R R ");
        } else if (cube.get(30) == bottomColor && cube.get(31) == frontColor) {
            Dp();
            Lp();
            D();
            algorithm.append("D' L' D ");
        } else if (cube.get(31) == bottomColor && cube.get(30) == frontColor) {
            L();
            L();
            Fp();              // last possibility for middle edges
            L();
            L();
            algorithm.append("L L F' L L ");
        } else if (cube.get(47) == bottomColor && cube.get(35) == frontColor) {
            // search bottom edges
        } else if (cube.get(35) == bottomColor && cube.get(47) == frontColor) {
            F();
            F();
            U();
            L();
            Fp();
            Lp();
            algorithm.append("F F U L F' L' ");
        } else if (cube.get(51) == bottomColor && cube.get(38) == frontColor) {
            R();
            D();
            Rp();
            Dp();
            algorithm.append("R D R' D' ");
        } else if (cube.get(38) == bottomColor && cube.get(51) == frontColor) {
            R();
            F();
            Rp();
            algorithm.append("R F R' ");
        } else if (cube.get(53) == bottomColor && cube.get(41) == frontColor) {
            B();
            B();
            U();
            U();
            B();
            B();
            F();
            F();
            algorithm.append("B B U U B B F F ");
        } else if (cube.get(41) == bottomColor && cube.get(53) == frontColor) {
            B();
            B();
            U();
            B();
            B();
            Rp();
            F();
            R();
            algorithm.append("B B U B B R' F R ");
        } else if (cube.get(49) == bottomColor && cube.get(44) == frontColor) {
            Lp();
            Dp();
            L();
            D();
            algorithm.append("L' D' L D ");
        } else if (cube.get(44) == bottomColor && cube.get(49) == frontColor) {
            Lp();
            Fp(); // end of bottom edge search
            L();
            algorithm.append("L' F' L ");
        }

        return algorithm;
    }

    private StringBuffer solveBottomCorners() {
        StringBuffer algorithm = new StringBuffer();

        algorithm.append(solveBottomFrontRightCorner(frontColor, rightColor, bottomColor));
        D();
        algorithm.append("D ");
        algorithm.append(solveBottomFrontRightCorner(leftColor, frontColor, bottomColor));
        D();
        algorithm.append("D ");
        algorithm.append(solveBottomFrontRightCorner(backColor, leftColor, bottomColor));
        D();
        algorithm.append("D ");
        algorithm.append(solveBottomFrontRightCorner(rightColor, backColor, bottomColor));
        D();
        algorithm.append("D ");        
        
        return algorithm;
    }

    /* 
     *  The procedure here is to navigate through each possible corner
     *  permutation for the desired corner, and then perform the proper
     *  algorithm to insert the corner into the 36, 37, 48 position.
     */
    private StringBuffer solveBottomFrontRightCorner(char frontColor, char rightColor, char bottomColor) {
        StringBuffer algorithm = new StringBuffer();

        if (cube.get(9) == bottomColor && cube.get(12) == rightColor && cube.get(13) == frontColor) {
            R(); 
            U();
            U();
            Rp();       // begin with position 9, 12, 13 and move clockwise
            Up();
            R();
            U();
            Rp();
            algorithm.append("R U U R' U' R U R' ");
        } else if (cube.get(13) == bottomColor && cube.get(9) == rightColor && cube.get(12) == frontColor) {
            R();
            U();
            Rp();
            algorithm.append("R U R' ");
        } else if (cube.get(12) == bottomColor && cube.get(13) == rightColor && cube.get(9) == frontColor) {
            Fp();
            Up();
            F();
            algorithm.append("F' U' F ");
        } else if (cube.get(3) == bottomColor && cube.get(15) == rightColor && cube.get(16) == frontColor) {
            U();
            R();     // move to 3, 15, 16 position
            U();
            U();
            Rp();
            Up();
            R();
            U();
            Rp();
            algorithm.append("U R U U R' U' R U R' ");
        } else if (cube.get(16) == bottomColor && cube.get(3) == rightColor && cube.get(15) == frontColor) {
            Fp();
            U();
            F();
            algorithm.append("F' U F ");
        } else if (cube.get(15) == bottomColor && cube.get(16) == rightColor && cube.get(3) == frontColor) {
            U();
            U();
            R();
            Up();
            Rp();
            algorithm.append("U U R U' R' ");
        } else if (cube.get(1) == bottomColor && cube.get(18) == rightColor && cube.get(19) == frontColor) {
            U();
            U();
            R();     // move to 1, 18, 19 position
            U();
            U();
            Rp();
            Up();
            R();
            U();
            Rp();
            algorithm.append("U U R U U R' U' R U R' ");
        } else if (cube.get(19) == bottomColor && cube.get(1) == rightColor && cube.get(18) == frontColor) {
            Fp();
            U();
            U();
            F();
            algorithm.append("F' U U F ");
        } else if (cube.get(18) == bottomColor && cube.get(19) == rightColor && cube.get(1) == frontColor) {
            R();
            U();
            U();
            Rp();
            algorithm.append("R U U R' ");
        } else if (cube.get(7) == bottomColor && cube.get(21) == rightColor && cube.get(10) == frontColor) {
            R();
            U();     // move to 7, 10, 21 position
            U();
            Rp();
            Fp();
            Up();
            F();
            algorithm.append("R U U R' F' U' F ");
        } else if (cube.get(10) == bottomColor && cube.get(7) == rightColor && cube.get(21) == frontColor) {
            Up();
            R();
            U();
            Rp();
            algorithm.append("U' R U R' ");
        } else if (cube.get(21) == bottomColor && cube.get(10) == rightColor && cube.get(7) == frontColor) {
            R();
            Up();
            Rp();
            algorithm.append("R U' R' ");
        } else if (cube.get(36) == bottomColor && cube.get(48) == rightColor && cube.get(37) == frontColor) {
            R();
            Up();     // move to 36, 37, 48 position
            Rp();
            Fp();
            Up();
            F();
            algorithm.append("R U' R' F' U' F ");
        } else if (cube.get(37) == bottomColor && cube.get(36) == rightColor && cube.get(48) == frontColor) {
            R();
            U();
            Rp();
            Up();
            R();
            U();
            Rp();
            algorithm.append("R U R' U' R U R' ");
        } else if (cube.get(48) == bottomColor && cube.get(37) == rightColor && cube.get(36) == frontColor) {
        } else if (cube.get(39) == bottomColor && cube.get(54) == rightColor && cube.get(40) == frontColor) {
            Rp();
            U();     // move to 39, 40, 54 position
            U();
            R();
            R();
            Up();
            Rp();
            algorithm.append("R' U U R R U' R' ");
        } else if (cube.get(40) == bottomColor && cube.get(39) == rightColor && cube.get(54) == frontColor) {
            Rp();
            U();
            R();
            Fp();
            U();
            F();
            algorithm.append("R' U R F' U F ");
        } else if (cube.get(54) == bottomColor && cube.get(40) == rightColor && cube.get(39) == frontColor) {
            B();
            U();
            Bp();
            Fp();
            Up();
            F();
            algorithm.append("B U B' F' U' F ");
        } else if (cube.get(42) == bottomColor && cube.get(52) == rightColor && cube.get(43) == frontColor) {
            L();
            Up();     // move to 42, 43, 52 position
            Lp();
            R();
            U();
            U();
            Rp();
            algorithm.append("L U' L' R U U R' ");
        } else if (cube.get(43) == bottomColor && cube.get(42) == rightColor && cube.get(52) == frontColor) {
            L();
            U();
            U();
            Lp();
            R();
            U();
            Rp();
            algorithm.append("L U U L' R U R' ");
        } else if (cube.get(52) == bottomColor && cube.get(43) == rightColor && cube.get(42) == frontColor) {
            L();
            U();
            U();
            Lp();
            Fp();
            Up();
            F();
            algorithm.append("L U U L' F' U' F ");
        } else if (cube.get(45) == bottomColor && cube.get(46) == rightColor && cube.get(34) == frontColor) {
            Lp();
            Up();     // move to 34, 45, 46 position
            L();
            Fp();
            Up();
            F();
            algorithm.append("L' U' L F' U' F ");
        } else if (cube.get(34) == bottomColor && cube.get(45) == rightColor && cube.get(46) == frontColor) {
            Lp();
            U();
            L();
            Up();
            R();
            U();
            Rp();
            algorithm.append("L' U L U' R U R' ");
        } else if (cube.get(46) == bottomColor && cube.get(34) == rightColor && cube.get(45) == frontColor) {
            Lp();
            Up();
            L();
            R();
            U();
            Rp();
            algorithm.append("L' U' L R U R' ");
        } 

        return algorithm;
    }

    /*
     *  The way this method works is the algorithm for the 24, 25 position is
     *  found, then the cute is rotate 90 degrees clockwise (looking at top).
     *  The same is done until all four corners have been solved. The cool
     *  part of this method is after the cube is rotated and the algorithm is 
     *  found, it is converted to the version of the algorithm for holding
     *  the cube without rotation.
     */
    private StringBuffer solveMiddleEdges() {
        StringBuffer algorithm = new StringBuffer();

        algorithm.append(middleEdgeAlgorithmAdjustment(0, middleEdgeAlgorithm(frontColor, rightColor)));
        X();
        algorithm.append(middleEdgeAlgorithmAdjustment(90, middleEdgeAlgorithm(rightColor, backColor)));
        X();
        algorithm.append(middleEdgeAlgorithmAdjustment(180, middleEdgeAlgorithm(backColor, leftColor)));
        X();
        algorithm.append(middleEdgeAlgorithmAdjustment(270, middleEdgeAlgorithm(leftColor, frontColor)));
        X();

        return algorithm;
        
    }

    /* The procedure here is to check each possible edge permutation for the
     * desired edge, then perform the insertion algorithm. This process will be
     * repeated for each middle edge. A repetive method won't be used here as
     * it was for the bottom edges and corners. This would require either a
     * move to rotate the entire cube, the middle slice, or the middle and
     * bottom layers. These moves go against my vision of only needing to use
     * basic cube manipulations, making the solving process as simple as
     * possible.
     */
    private StringBuffer middleEdgeAlgorithm(char frontColor, char rightColor) {
        StringBuffer algorithm = new StringBuffer();

        /* 
         *  This is a weird convention, but for the sake of the 
         *  middleEdgeAlgorithmAdjustment method, prime rotations will
         *  be denoted as follows, R' : T, U' : I, D' : S, L' : K, B' : N, F' : G.
         */

        // solve 24, 25 position
        if (cube.get(8) == frontColor && cube.get(11) == rightColor) {
            algorithm.append("UUGUFURIT");
        } else if (cube.get(11) == frontColor && cube.get(8) == rightColor) {
            algorithm.append("URITIGUF");
        } else if (cube.get(14) == frontColor && cube.get(6) == rightColor) {
            algorithm.append("UURITIGUF");
        } else if (cube.get(6) == frontColor && cube.get(14) == rightColor) {
            algorithm.append("IGUFURIT");
        } else if (cube.get(2) == frontColor && cube.get(17) == rightColor) {
            algorithm.append("GUFURIT");
        } else if (cube.get(17) == frontColor && cube.get(2) == rightColor) {
            algorithm.append("IRITIGUF");
        } else if (cube.get(4) == frontColor && cube.get(20) == rightColor) {
            algorithm.append("UGUFURIT");
        } else if (cube.get(20) == frontColor && cube.get(4) == rightColor) {
            algorithm.append("RITIGUF");
        } else if (cube.get(24) == frontColor && cube.get(25) == rightColor) {
            algorithm.append("");
        } else if (cube.get(25) == frontColor && cube.get(24) == rightColor) {
            algorithm.append("RITIGUFIRITIGUF");
        } else if (cube.get(27) == frontColor && cube.get(28) == rightColor) {
            algorithm.append("BINITURUGUFURIT");
        } else if (cube.get(28) == frontColor && cube.get(27) == rightColor) {
            algorithm.append("BINITURRITIGUF");
        } else if (cube.get(30) == frontColor && cube.get(31) == rightColor) {
            algorithm.append("LIKINUBUUGUFURIT");
        } else if (cube.get(31) == frontColor && cube.get(30) == rightColor) {
            algorithm.append("LIKINUBURITIGIF");
        } else if (cube.get(33) == frontColor && cube.get(22) == rightColor) {
            algorithm.append("KULUFIGIRITIGUF");
        } else if (cube.get(22) == frontColor && cube.get(33) == rightColor) {
            algorithm.append("KULUFIGGUFURIT");
        }

        return algorithm;
    }

    // see middleEdgeAlgorithm() for information on this notation convention
    private StringBuffer middleEdgeAlgorithmAdjustment(int degreesRotated, StringBuffer algorithm) {
        StringBuffer newAlgorithm = new StringBuffer();

        if (degreesRotated == 0) {
            for (int i = 0; i < algorithm.length(); i++) {
                if (algorithm.charAt(i) == 'U') {
                    U();
                    newAlgorithm.append("U ");
                } else if (algorithm.charAt(i) == 'I') {
                    Up();
                    newAlgorithm.append("U' ");
                } else if (algorithm.charAt(i) == 'F') {
                    F();
                    newAlgorithm.append("F ");
                } else if (algorithm.charAt(i) == 'G') {
                    Fp();
                    newAlgorithm.append("F' ");
                } else if (algorithm.charAt(i) == 'R') {
                    R();
                    newAlgorithm.append("R ");
                } else if (algorithm.charAt(i) == 'T') {
                    Rp();
                    newAlgorithm.append("R' ");
                } else if (algorithm.charAt(i) == 'L') {
                    L();
                    newAlgorithm.append("L ");
                } else if (algorithm.charAt(i) == 'K') {
                    Lp();
                    newAlgorithm.append("L' ");
                } else if (algorithm.charAt(i) == 'D') {
                    D();
                    newAlgorithm.append("D ");
                } else if (algorithm.charAt(i) == 'S') {
                    Dp();
                    newAlgorithm.append("D' ");
                } else if (algorithm.charAt(i) == 'B') {
                    B();
                    newAlgorithm.append("B ");
                } else if (algorithm.charAt(i) == 'N') {
                    Bp();
                    newAlgorithm.append("B' ");
                }
            }
        } else if (degreesRotated == 90) {
             for (int i = 0; i < algorithm.length(); i++) {
                if (algorithm.charAt(i) == 'U') {
                    U();
                    newAlgorithm.append("U ");
                } else if (algorithm.charAt(i) == 'I') {
                    Up();
                    newAlgorithm.append("U' ");
                } else if (algorithm.charAt(i) == 'F') {
                    F();
                    newAlgorithm.append("R ");
                } else if (algorithm.charAt(i) == 'G') {
                    Fp();
                    newAlgorithm.append("R' ");
                } else if (algorithm.charAt(i) == 'R') {
                    R();
                    newAlgorithm.append("B ");
                } else if (algorithm.charAt(i) == 'T') {
                    Rp();
                    newAlgorithm.append("B' ");
                } else if (algorithm.charAt(i) == 'L') {
                    L();
                    newAlgorithm.append("F ");
                } else if (algorithm.charAt(i) == 'K') {
                    Lp();
                    newAlgorithm.append("F' ");
                } else if (algorithm.charAt(i) == 'D') {
                    D();
                    newAlgorithm.append("D ");
                } else if (algorithm.charAt(i) == 'S') {
                    Dp();
                    newAlgorithm.append("D' ");
                } else if (algorithm.charAt(i) == 'B') {
                    B();
                    newAlgorithm.append("L ");
                } else if (algorithm.charAt(i) == 'N') {
                    Bp();
                    newAlgorithm.append("L' ");
                }
            }
        } else if (degreesRotated == 180) {
             for (int i = 0; i < algorithm.length(); i++) {
                if (algorithm.charAt(i) == 'U') {
                    U();
                    newAlgorithm.append("U ");
                } else if (algorithm.charAt(i) == 'I') {
                    Up();
                    newAlgorithm.append("U' ");
                } else if (algorithm.charAt(i) == 'F') {
                    F();
                    newAlgorithm.append("B ");
                } else if (algorithm.charAt(i) == 'G') {
                    Fp();
                    newAlgorithm.append("B' ");
                } else if (algorithm.charAt(i) == 'R') {
                    R();
                    newAlgorithm.append("L ");
                } else if (algorithm.charAt(i) == 'T') {
                    Rp();
                    newAlgorithm.append("L' ");
                } else if (algorithm.charAt(i) == 'L') {
                    L();
                    newAlgorithm.append("R ");
                } else if (algorithm.charAt(i) == 'K') {
                    Lp();
                    newAlgorithm.append("R' ");
                } else if (algorithm.charAt(i) == 'D') {
                    D();
                    newAlgorithm.append("D ");
                } else if (algorithm.charAt(i) == 'S') {
                    Dp();
                    newAlgorithm.append("D' ");
                } else if (algorithm.charAt(i) == 'B') {
                    B();
                    newAlgorithm.append("F ");
                } else if (algorithm.charAt(i) == 'N') {
                    Bp();
                    newAlgorithm.append("F' ");
                }
            }
        } else if (degreesRotated == 270) {
             for (int i = 0; i < algorithm.length(); i++) {
                if (algorithm.charAt(i) == 'U') {
                    U();
                    newAlgorithm.append("U ");
                } else if (algorithm.charAt(i) == 'I') {
                    Up();
                    newAlgorithm.append("U' ");
                } else if (algorithm.charAt(i) == 'F') {
                    F();
                    newAlgorithm.append("L ");
                } else if (algorithm.charAt(i) == 'G') {
                    Fp();
                    newAlgorithm.append("L' ");
                } else if (algorithm.charAt(i) == 'R') {
                    R();
                    newAlgorithm.append("F ");
                } else if (algorithm.charAt(i) == 'T') {
                    Rp();
                    newAlgorithm.append("F' ");
                } else if (algorithm.charAt(i) == 'L') {
                    L();
                    newAlgorithm.append("B ");
                } else if (algorithm.charAt(i) == 'K') {
                    Lp();
                    newAlgorithm.append("B' ");
                } else if (algorithm.charAt(i) == 'D') {
                    D();
                    newAlgorithm.append("D ");
                } else if (algorithm.charAt(i) == 'S') {
                    Dp();
                    newAlgorithm.append("D' ");
                } else if (algorithm.charAt(i) == 'B') {
                    B();
                    newAlgorithm.append("R ");
                } else if (algorithm.charAt(i) == 'N') {
                    Bp();
                    newAlgorithm.append("R' ");
                }
            }
        }

        return newAlgorithm;
    }

    private StringBuffer solveTopCross() {
        StringBuffer algorithm = new StringBuffer();
        int solvedTiles = 0;

        for (int i = 2; i <= 8; i += 2) {
            if (cube.get(i) == topColor) {
                solvedTiles++;
            }
        }

        // if no edges in cross, solved, solve 2 of them
        if (solvedTiles == 0) {
            F();
            R();
            U();
            Rp();
            Up();
            Fp();
            algorithm.append("F R U R' U' F' ");
            solvedTiles = 2;
        }

        // see if a bar is solved, then see if its horizontal, if not
        // make it horizontal then perform algorithm
        if (solvedTiles == 2) {
            if ((cube.get(2) == topColor && cube.get(8) == topColor) ||
                (cube.get(4) == topColor && cube.get(6) == topColor)) {
                if ((cube.get(2) == topColor && cube.get(8) == topColor)) {
                    U();
                    algorithm.append("U ");
                }
                    F();
                    R();
                    U();
                    Rp();
                    Up();
                    Fp();
                    algorithm.append("F R U R' U' F' ");
            // if the cross has 2 tiles solved in an "L" formation, orient
            // the tiles to be in the 6 and 8 positions then perform the
            // algorithm
            } else if ((cube.get(2) == topColor && cube.get(6) == topColor) ||
                       (cube.get(6) == topColor && cube.get(8) == topColor) || 
                       (cube.get(4) == topColor && cube.get(8) == topColor) ||
                       (cube.get(2) == topColor && cube.get(4) == topColor)) {
                if (cube.get(2) == topColor && cube.get(6) == topColor) {
                    Up();
                    algorithm.append("U' ");
                } else if (cube.get(6) == topColor && cube.get(8) == topColor) {
                    U();
                    U();
                    algorithm.append("U U ");
                } else if (cube.get(4) == topColor && cube.get(8) == topColor) {
                    U();
                    algorithm.append("U ");
                }
                F();
                U();
                R();
                Up();
                Rp();
                Fp();
                algorithm.append("F U R U' R' F' ");
            }
        }

        return algorithm;
    }

    /*
     * The procedure here is to perform the needed algorithm until there is
     * one corner solved on top, then perform the needed algorithm until the
     * top is solved. Most of this method is just orienting the top face
     * correctly to perform the needed algorithm.
     */
    private StringBuffer solveTopCorners() {
        StringBuffer algorithm = new StringBuffer();

        // loop until top is solved
        while (!topSideSolved()) {  // loop until top is solved
            while(numTopCornersSolved() != 1) {  // loop until only one corner solved
                while (cube.get(10) != topColor) {  // loop until top color in 10 position
                    U();  
                    algorithm.append("U "); 
                }

                algorithm.append(solveTopSideAlgorithm());
            }
            while (cube.get(7) != topColor) { // loop until one solved corner is in position 7
                U();
                algorithm.append("U ");     
            }

            algorithm.append(solveTopSideAlgorithm());
        }

        return algorithm;
    }

    private int numTopCornersSolved() {
        int numSolved = 0;

        if (cube.get(1) == topColor) {
            numSolved++;
        }

        if (cube.get(3) == topColor) {
            numSolved++;
        }

        if (cube.get(7) == topColor) {
            numSolved++;
        }

        if (cube.get(9) == topColor) {
            numSolved++;
        }

        return numSolved;
    }

    private boolean topSideSolved() {
        return cube.get(1) == topColor && cube.get(3) == topColor && 
               cube.get(7) == topColor && cube.get(9) == topColor;
    }

    private StringBuffer solveTopSideAlgorithm() {
        StringBuffer algorithm = new StringBuffer();

                R();
                U();
                Rp();
                U();
                R();
                U();
                U();
                Rp();
                algorithm.append("R U R' U R U U R' ");

        return algorithm;
    }

    /*
     * Orienting the corners takes one or two steps, depending on whether or
     * not two adjacent corners are already oriented.
     */
    private StringBuffer orientTopCorners() {
        StringBuffer algorithm = new StringBuffer();

        if (!matchingTopCorners()) {
            algorithm.append(orientTopCornersAlgorithm());
        }

        while (cube.get(16) != cube.get(18)) {
            U();
            algorithm.append("U ");
        }

        algorithm.append(orientTopCornersAlgorithm());
        
        return algorithm;
    }

    private boolean matchingTopCorners() {
        return cube.get(10) == cube.get(12) || cube.get(13) == cube.get(15) ||
               cube.get(16) == cube.get(18) || cube.get(19) == cube.get(21);
    }

    private StringBuffer orientTopCornersAlgorithm() {
        StringBuffer algorithm = new StringBuffer();

        Rp();
        F();
        Rp();
        B();
        B();
        R();
        Fp();
        Rp();
        B();
        B();
        R();
        R();
        algorithm.append("R' F R' B B R F' R' B B R R ");

        return algorithm;
    }

    /* 
     * If no edges are oriented, perform the needed algorithm then align
     * the top face correctly and perform it again. If an edge is already
     * oriented correctly, just align the top face correctly and perform
     * the needed algorithm.
     */
    private StringBuffer orientTopEdges() {
        StringBuffer algorithm = new StringBuffer();

        if (numEdgesOriented() == 0) {
            algorithm.append(orientTopEdgesClockwiseAlgorithm());
        } else if (numEdgesOriented() == 1) {
            while(cube.get(16) != cube.get(17)) {
                U();
                algorithm.append("U ");
            }

            if (cube.get(11) == cube.get(13)) {
                orientTopEdgesCounterClockwiseAlgorithm();
            } else {
                orientTopEdgesClockwiseAlgorithm();
            }
        }

        return algorithm;
    }

    private StringBuffer orientTopEdgesClockwiseAlgorithm () {
        StringBuffer algorithm = new StringBuffer();

        F();
        F();
        U();
        L();
        Rp();
        F();
        F();
        Lp();
        R();
        U();
        F();
        F();
        algorithm.append("F F U L R' F F L' R U F F ");

        return algorithm;
    }

    private StringBuffer orientTopEdgesCounterClockwiseAlgorithm () {
        StringBuffer algorithm = new StringBuffer();

        F();
        F();
        Up();
        L();
        Rp();
        F();
        F();
        Lp();
        R();
        Up();
        F();
        F();
        algorithm.append("F F U' L R' F F L' R U' F F ");

        return algorithm;
    }

    private int numEdgesOriented() {
        int numEdges = 0;
        
        if (cube.get(10) == cube.get(11)) {
            numEdges++;
        }

        if (cube.get(13) == cube.get(14)) {
            numEdges++;
        }

        if (cube.get(16) == cube.get(17)) {
            numEdges++;
        }

        if (cube.get(19) == cube.get(20)) {
            numEdges++;
        }

        return numEdges;
    }

    private StringBuffer orientTopLayer() {
        StringBuffer algorithm = new StringBuffer();

        while (cube.get(10) != cube.get(22)) {
            U();
            algorithm.append("U ");
        }

        return algorithm;
    }

    private boolean cubeSolved() {
        StringBuffer solvedPosition = new StringBuffer();

        for (int i = 1; i <= 54; i++) {
            if (i <= 9) {
                solvedPosition.append(topColor);
            } else if (i > 9 && i <= 12) {
                solvedPosition.append(frontColor);
            } else if (i > 12 && i <= 15) {
                solvedPosition.append(rightColor);
            } else if (i > 15 && i <= 18) {
                solvedPosition.append(backColor);
            } else if (i > 18 && i <= 21) {
                solvedPosition.append(leftColor);
            } else if (i > 21 && i <= 24) {
                solvedPosition.append(frontColor);
            } else if (i > 24 && i <= 27) {
                solvedPosition.append(rightColor);
            } else if (i > 27 && i <= 30) {
                solvedPosition.append(backColor);
            } else if (i > 30 && i <= 33) {
                solvedPosition.append(leftColor);
            } else if (i > 33 && i <= 36) {
                solvedPosition.append(frontColor);
            } else if (i > 36 && i <= 39) {
                solvedPosition.append(rightColor);
            } else if (i > 39 && i <= 42) {
                solvedPosition.append(backColor);
            } else if (i > 42 && i <= 45) {
                solvedPosition.append(leftColor);
            } else {
                solvedPosition.append(bottomColor);
            }
        }

        if (currentOrientation().equals(solvedPosition)) {
            return true;
        } else {
            return false;
        }
    }

    // return the current orientation as a string
    public StringBuffer currentOrientation() {
        StringBuffer currentOrientation = new StringBuffer();

        for (int i = 1; i <= 54; i++) {
            currentOrientation.append(cube.get(i));
        }

        return currentOrientation;
    }

    // turn front clock-wise
    public void F() {
        char tempCorner = cube.get(10);
        char tempEdge = cube.get(11);
        char tempRingCorner = cube.get(7);
        char tempRingCorner2 = cube.get(21);
        char tempRingEdge = cube.get(8);

        // rotate front corners
        cube.put(10, cube.get(34));
        cube.put(34, cube.get(36));
        cube.put(36, cube.get(12));
        cube.put(12, tempCorner);
        // rotate front edges
        cube.put(11, cube.get(22));
        cube.put(22, cube.get(35));
        cube.put(35, cube.get(24));
        cube.put(24, tempEdge);
        // rotate corners of side ring closest to front
        cube.put(7, cube.get(45));
        cube.put(21, cube.get(46));
        cube.put(45, cube.get(48));
        cube.put(46, cube.get(37));
        cube.put(48, cube.get(13));
        cube.put(37, cube.get(9));
        cube.put(13, tempRingCorner);
        cube.put(9, tempRingCorner2);
        // rotate edges of side ring closest to front
        cube.put(8, cube.get(33));
        cube.put(33, cube.get(47));
        cube.put(47, cube.get(25));
        cube.put(25, tempRingEdge);
    }

    // turn front counter clock-wise
    public void Fp() {
        char tempCorner = cube.get(10);
        char tempEdge = cube.get(11);
        char tempRingCorner = cube.get(7);
        char tempRingCorner2 = cube.get(9);
        char tempRingEdge = cube.get(8);

        // rotate face corners
        cube.put(10, cube.get(12));
        cube.put(12, cube.get(36));
        cube.put(36, cube.get(34));
        cube.put(34, tempCorner);
        // rotate face edges
        cube.put(11, cube.get(24));
        cube.put(24, cube.get(35));
        cube.put(35, cube.get(22));
        cube.put(22, tempEdge);
        // rotate ring corners
        cube.put(7, cube.get(13));
        cube.put(9, cube.get(37));
        cube.put(13, cube.get(48));
        cube.put(37, cube.get(46));
        cube.put(48, cube.get(45));
        cube.put(46, cube.get(21));
        cube.put(45, tempRingCorner);
        cube.put(21, tempRingCorner2);
        // rotate ring edges
        cube.put(8, cube.get(25));
        cube.put(25, cube.get(47));
        cube.put(47, cube.get(33));
        cube.put(33, tempRingEdge);
    }

    // turn right clock-wise
    public void R() {
        char tempCorner = cube.get(13);
        char tempEdge = cube.get(14);
        char tempRingCorner = cube.get(9);
        char tempRingCorner2 = cube.get(12);
        char tempRingEdge = cube.get(24);

        // rotate face corners
        cube.put(13, cube.get(37));
        cube.put(37, cube.get(39));
        cube.put(39, cube.get(15));
        cube.put(15, tempCorner);
        // rotate face edges
        cube.put(14, cube.get(25));
        cube.put(25, cube.get(38));
        cube.put(38, cube.get(27));
        cube.put(27, tempEdge);
        // rotate ring corners
        cube.put(9, cube.get(36));
        cube.put(12, cube.get(48));
        cube.put(36, cube.get(54));
        cube.put(48, cube.get(40));
        cube.put(54, cube.get(16));
        cube.put(40, cube.get(3));
        cube.put(16, tempRingCorner);
        cube.put(3, tempRingCorner2);
        // rotate ring edges
        cube.put(24, cube.get(51));
        cube.put(51, cube.get(28));
        cube.put(28, cube.get(6));
        cube.put(6, tempRingEdge);
    }

    // turn right counter clock-wise
    public void Rp() {
        char tempCorner = cube.get(13);
        char tempEdge = cube.get(14);
        char tempRingCorner = cube.get(9);
        char tempRingCorner2 = cube.get(3);
        char tempRingEdge = cube.get(6);

        // rotate face corners
        cube.put(13, cube.get(15));
        cube.put(15, cube.get(39));
        cube.put(39, cube.get(37));
        cube.put(37, tempCorner);
        // rotate face edges
        cube.put(14, cube.get(27));
        cube.put(27, cube.get(38));
        cube.put(38, cube.get(25));
        cube.put(25, tempEdge);
        // rotate ring corners
        cube.put(9, cube.get(16));
        cube.put(3, cube.get(40));
        cube.put(16, cube.get(54));
        cube.put(40, cube.get(48));
        cube.put(54, cube.get(36));
        cube.put(48, cube.get(12));
        cube.put(36, tempRingCorner);
        cube.put(12, tempRingCorner2);
        // rotate ring edges
        cube.put(6, cube.get(28));
        cube.put(28, cube.get(51));
        cube.put(51, cube.get(24));
        cube.put(24, tempRingEdge);
    }

    // turn left clock-wise
    public void L() {
        char tempCorner = cube.get(19);
        char tempEdge = cube.get(20);
        char tempRingCorner = cube.get(1);
        char tempRingCorner2 = cube.get(18);
        char tempRingEdge = cube.get(4);

        // rotate face corners
        cube.put(19, cube.get(43));
        cube.put(43, cube.get(45));
        cube.put(45, cube.get(21));
        cube.put(21, tempCorner);
        // rotate face edges
        cube.put(20, cube.get(31));
        cube.put(31, cube.get(44));
        cube.put(44, cube.get(33));
        cube.put(33, tempEdge);
        // rotate ring corners
        cube.put(1, cube.get(42));
        cube.put(18, cube.get(52));
        cube.put(42, cube.get(46));
        cube.put(52, cube.get(34));
        cube.put(46, cube.get(10));
        cube.put(34, cube.get(7));
        cube.put(10, tempRingCorner);
        cube.put(7, tempRingCorner2);
        // rotate ring edges
        cube.put(4, cube.get(30));
        cube.put(30, cube.get(49));
        cube.put(49, cube.get(22));
        cube.put(22, tempRingEdge);
    }

    // turn left counter clock-wise
    public void Lp() {
        char tempCorner = cube.get(19);
        char tempEdge = cube.get(20);
        char tempRingCorner = cube.get(1);
        char tempRingCorner2 = cube.get(7);
        char tempRingEdge = cube.get(4);

        // rotate face corners
        cube.put(19, cube.get(21));
        cube.put(21, cube.get(45));
        cube.put(45, cube.get(43));
        cube.put(43, tempCorner);
        // rotate face edges
        cube.put(20, cube.get(33));
        cube.put(33, cube.get(44));
        cube.put(44, cube.get(31));
        cube.put(31, tempEdge);
        // rotate ring corners
        cube.put(1, cube.get(10));
        cube.put(7, cube.get(34));
        cube.put(10, cube.get(46));
        cube.put(34, cube.get(52));
        cube.put(46, cube.get(42));
        cube.put(52, cube.get(18));
        cube.put(42, tempRingCorner);
        cube.put(18, tempRingCorner2);
        // rotate ring edges
        cube.put(4, cube.get(22));
        cube.put(22, cube.get(49));
        cube.put(49, cube.get(30));
        cube.put(30, tempRingEdge);
    }

    // turn top clock-wise
    public void U() {
        char tempCorner = cube.get(1);
        char tempEdge = cube.get(2);
        char tempRingCorner = cube.get(18);
        char tempRingCorner2 = cube.get(19);
        char tempRingEdge = cube.get(17);

        // rotate face corners
        cube.put(1, cube.get(7));
        cube.put(7, cube.get(9));
        cube.put(9, cube.get(3));
        cube.put(3, tempCorner);
        // rotate face edges
        cube.put(2, cube.get(4));
        cube.put(4, cube.get(8));
        cube.put(8, cube.get(6));
        cube.put(6, tempEdge);
        // rotate ring corners
        cube.put(18, cube.get(21));
        cube.put(19, cube.get(10));
        cube.put(21, cube.get(12));
        cube.put(10, cube.get(13));
        cube.put(12, cube.get(15));
        cube.put(13, cube.get(16));
        cube.put(15, tempRingCorner);
        cube.put(16, tempRingCorner2);
        // rotate ring edges
        cube.put(17, cube.get(20));
        cube.put(20, cube.get(11));
        cube.put(11, cube.get(14));
        cube.put(14, tempRingEdge);
    }

    // turn top counter clock-wise
    public void Up() {
        char tempCorner = cube.get(1);
        char tempEdge = cube.get(2);
        char tempRingCorner = cube.get(18);
        char tempRingCorner2 = cube.get(16);
        char tempRingEdge = cube.get(17);

        // rotate face corners
        cube.put(1, cube.get(3));
        cube.put(3, cube.get(9));
        cube.put(9, cube.get(7));
        cube.put(7, tempCorner);
        // rotate face edges
        cube.put(2, cube.get(6));
        cube.put(6, cube.get(8));
        cube.put(8, cube.get(4));
        cube.put(4, tempEdge);
        // rotate ring corners
        cube.put(18, cube.get(15));
        cube.put(16, cube.get(13));
        cube.put(15, cube.get(12));
        cube.put(13, cube.get(10));
        cube.put(12, cube.get(21));
        cube.put(10, cube.get(19));
        cube.put(21, tempRingCorner);
        cube.put(19, tempRingCorner2);
        // rotate ring edges
        cube.put(17, cube.get(14));
        cube.put(14, cube.get(11));
        cube.put(11, cube.get(20));
        cube.put(20, tempRingEdge);
    }

    // turn back clock-wise
    public void B() {
        char tempCorner = cube.get(16);
        char tempEdge = cube.get(17);
        char tempRingCorner = cube.get(3);
        char tempRingCorner2 = cube.get(15);
        char tempRingEdge = cube.get(2);

        // rotate face corners
        cube.put(16, cube.get(40));
        cube.put(40, cube.get(42));
        cube.put(42, cube.get(18));
        cube.put(18, tempCorner);
        // rotate face edges
        cube.put(17, cube.get(28));
        cube.put(28, cube.get(41));
        cube.put(41, cube.get(30));
        cube.put(30, tempEdge);
        // rotate ring corners
        cube.put(3, cube.get(39));
        cube.put(15, cube.get(54));
        cube.put(39, cube.get(52));
        cube.put(54, cube.get(43));
        cube.put(52, cube.get(19));
        cube.put(43, cube.get(1));
        cube.put(19, tempRingCorner);
        cube.put(1, tempRingCorner2);
        // rotate ring edges
        cube.put(2, cube.get(27));
        cube.put(27, cube.get(53));
        cube.put(53, cube.get(31));
        cube.put(31, tempRingEdge);
    }

    // turn back counter-clock wise
    public void Bp() {
        char tempCorner = cube.get(16);
        char tempEdge = cube.get(17);
        char tempRingCorner = cube.get(3);
        char tempRingCorner2 = cube.get(1);
        char tempRingEdge = cube.get(2);

        // rotate face corners
        cube.put(16, cube.get(18));
        cube.put(18, cube.get(42));
        cube.put(42, cube.get(40));
        cube.put(40, tempCorner);
        // rotate face edges
        cube.put(17, cube.get(30));
        cube.put(30, cube.get(41));
        cube.put(41, cube.get(28));
        cube.put(28, tempEdge);
        // rotate ring corners
        cube.put(3, cube.get(19));
        cube.put(1, cube.get(43));
        cube.put(19, cube.get(52));
        cube.put(43, cube.get(54));
        cube.put(52, cube.get(39));
        cube.put(54, cube.get(15));
        cube.put(39, tempRingCorner);
        cube.put(15, tempRingCorner2);
        // rotate ring edges
        cube.put(2, cube.get(31));
        cube.put(31, cube.get(53));
        cube.put(53, cube.get(27));
        cube.put(27, tempRingEdge);
    }

    // turn bottom clock-wise
    public void D() {
        char tempCorner = cube.get(46);
        char tempEdge = cube.get(47);
        char tempRingCorner = cube.get(34);
        char tempRingCorner2 = cube.get(45);
        char tempRingEdge = cube.get(35);

        // rotate face corners
        cube.put(46, cube.get(52));
        cube.put(52, cube.get(54));
        cube.put(54, cube.get(48));
        cube.put(48, tempCorner);
        // rotate face edges
        cube.put(47, cube.get(49));
        cube.put(49, cube.get(53));
        cube.put(53, cube.get(51));
        cube.put(51, tempEdge);
        // rotate ring corners
        cube.put(34, cube.get(43));
        cube.put(45, cube.get(42));
        cube.put(43, cube.get(40));
        cube.put(42, cube.get(39));
        cube.put(40, cube.get(37));
        cube.put(39, cube.get(36));
        cube.put(37, tempRingCorner);
        cube.put(36, tempRingCorner2);
        // rotate ring edges
        cube.put(35, cube.get(44));
        cube.put(44, cube.get(41));
        cube.put(41, cube.get(38));
        cube.put(38, tempRingEdge);
    }

    // turn bottom counter clock-wise
    public void Dp() {
        char tempCorner = cube.get(46);
        char tempEdge = cube.get(47);
        char tempRingCorner = cube.get(34);
        char tempRingCorner2 = cube.get(36);
        char tempRingEdge = cube.get(35);

        // rotate face corners
        cube.put(46, cube.get(48));
        cube.put(48, cube.get(54));
        cube.put(54, cube.get(52));
        cube.put(52, tempCorner);
        // rotate face edges
        cube.put(47, cube.get(51));
        cube.put(51, cube.get(53));
        cube.put(53, cube.get(49));
        cube.put(49, tempEdge);
        // rotate ring corners
        cube.put(34, cube.get(37));
        cube.put(36, cube.get(39));
        cube.put(37, cube.get(40));
        cube.put(39, cube.get(42));
        cube.put(40, cube.get(43));
        cube.put(42, cube.get(45));
        cube.put(43, tempRingCorner);
        cube.put(45, tempRingCorner2);
        // rotate ring edges
        cube.put(35, cube.get(38));
        cube.put(38, cube.get(41));
        cube.put(41, cube.get(44));
        cube.put(44, tempRingEdge);
    }

    public void X() {
        char tempCenter = cube.get(23);
        char tempEdge1 = cube.get(22);
        char tempEdge2 = cube.get(24);

        // rotate top and bottom
        U();
        Dp();

        // rotate centers
        cube.put(23, cube.get(26));
        cube.put(26, cube.get(29));
        cube.put(29, cube.get(32));
        cube.put(32, tempCenter);

        // rotate edges
        cube.put(22, cube.get(25));
        cube.put(24, cube.get(27));
        cube.put(25, cube.get(28));
        cube.put(27, cube.get(30));
        cube.put(28, cube.get(31));
        cube.put(30, cube.get(33));
        cube.put(31, tempEdge1);
        cube.put(33, tempEdge2);
    }

    
}