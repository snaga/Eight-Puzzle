import static org.junit.Assert.*;
import org.junit.*;
import junit.textui.TestRunner;

public class TestEightPuzzle {

	@Test
	public void testEightPuzzle1() {
		EightPuzzle p = new EightPuzzle();

		assertNotNull(p);
	}

	@Test
	public void testEightPuzzle2() {
		int[] initState = new int[9];

		initState[0] = 0;
		initState[1] = 1;
		initState[2] = 2;
		initState[3] = 3;
		initState[4] = 4;
		initState[5] = 5;
		initState[6] = 6;
		initState[7] = 7;
		initState[8] = 8;

		EightPuzzle p = new EightPuzzle(initState, 0);

		assertNotNull(p);
	}

	@Test
	public void testToString() {
		EightPuzzle p = new EightPuzzle();

		assertNotNull(p);
		assertEquals(p.toString(), "[ 7 2 4 5 0 6 8 3 1 ]");
	}

	@Test
	public void testGetState() {
		EightPuzzle p = new EightPuzzle();

		assertNotNull(p);

		int[] s = p.getState();

		assertEquals(s[0], 7);
		assertEquals(s[1], 2);
		assertEquals(s[2], 4);
		assertEquals(s[3], 5);
		assertEquals(s[4], 0);
		assertEquals(s[5], 6);
		assertEquals(s[6], 8);
		assertEquals(s[7], 3);
		assertEquals(s[8], 1);
	}

	@Test
	public void testGetPosXY() {
		EightPuzzle p = new EightPuzzle();

		assertNotNull(p);

		assertEquals(p.getPosX(0), 1);
		assertEquals(p.getPosY(0), 1);

		assertEquals(p.getPosX(5), 0);
		assertEquals(p.getPosY(5), 1);

		assertEquals(p.getPosX(8), 0);
		assertEquals(p.getPosY(8), 2);

		assertEquals(p.getPosX(9), -1);
		assertEquals(p.getPosY(9), -1);
	}

	@Test
	public void testGetTile() {
		EightPuzzle p = new EightPuzzle();

		assertNotNull(p);

		assertEquals(p.getTile(0,0), 7);
		assertEquals(p.getTile(1,0), 2);
		assertEquals(p.getTile(2,0), 4);
		assertEquals(p.getTile(0,1), 5);
		assertEquals(p.getTile(1,1), 0);
		assertEquals(p.getTile(2,1), 6);
		assertEquals(p.getTile(0,2), 8);
		assertEquals(p.getTile(1,2), 3);
		assertEquals(p.getTile(2,2), 1);

		assertEquals(p.getTile(0,3), -1);
		assertEquals(p.getTile(3,0), -1);
	}

	@Test
	public void testCompState() {
		int[] state = new int[9];

		state[0] = 7;
		state[1] = 2;
		state[2] = 4;
		state[3] = 5;
		state[4] = 0;
		state[5] = 6;
		state[6] = 8;
		state[7] = 3;
		state[8] = 1;

		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		assertEquals(p.compareState(state), true);

		state[7] = 8;
		state[8] = 7;

		assertEquals(p.compareState(state), false);
	}

	@Test
	public void testIsGoalState() {
		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		assertEquals(p.isGoalState(), false);

		int[] initState = new int[9];

		initState[0] = 0;
		initState[1] = 1;
		initState[2] = 2;
		initState[3] = 3;
		initState[4] = 4;
		initState[5] = 5;
		initState[6] = 6;
		initState[7] = 7;
		initState[8] = 8;

		p = new EightPuzzle(initState, 0);
		assertNotNull(p);

		assertEquals(p.isGoalState(), true);
	}

	@Test
	public void testGetNumberOfMisplacedTiles() {
		/* test 1 */
		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		assertEquals(p.getNumberOfMisplacedTiles(), 8);

		/* test 2 */
		int[] initState = new int[9];

		initState[0] = 0;
		initState[1] = 1;
		initState[2] = 2;
		initState[3] = 3;
		initState[4] = 4;
		initState[5] = 5;
		initState[6] = 6;
		initState[7] = 7;
		initState[8] = 8;

		p = new EightPuzzle(initState, 0);
		assertNotNull(p);

		assertEquals(p.getNumberOfMisplacedTiles(), 0);

		/* test 3 */
		initState[6] = 8;
		initState[7] = 7;
		initState[8] = 6;

		p = new EightPuzzle(initState, 0);
		assertNotNull(p);

		assertEquals(p.getNumberOfMisplacedTiles(), 2);

		/* test 4: 7 1 2 3 0 4 5 6 8 */
		initState[0] = 7;
		initState[1] = 1;
		initState[2] = 2;
		initState[3] = 3;
		initState[4] = 0;
		initState[5] = 4;
		initState[6] = 5;
		initState[7] = 6;
		initState[8] = 8;

		p = new EightPuzzle(initState, 0);
		assertNotNull(p);

		assertEquals(p.getNumberOfMisplacedTiles(), 4);

		/* test 5: 0 1 2 7 3 4 5 6 8 */
		initState[0] = 0;
		initState[1] = 1;
		initState[2] = 2;
		initState[3] = 7;
		initState[4] = 3;
		initState[5] = 4;
		initState[6] = 5;
		initState[7] = 6;
		initState[8] = 8;

		p = new EightPuzzle(initState, 0);
		assertNotNull(p);

		assertEquals(p.getNumberOfMisplacedTiles(), 5);
	}

	@Test
	public void testGetManhattanBlockDistance() {
		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		assertEquals(p.getManhattanBlockDistance(1), 3);
		assertEquals(p.getManhattanBlockDistance(2), 1);
		assertEquals(p.getManhattanBlockDistance(3), 2);
		assertEquals(p.getManhattanBlockDistance(4), 2);
		assertEquals(p.getManhattanBlockDistance(5), 2);
		assertEquals(p.getManhattanBlockDistance(6), 3);
		assertEquals(p.getManhattanBlockDistance(7), 3);
		assertEquals(p.getManhattanBlockDistance(8), 2);
	}

	@Test
	public void testTotalGetManhattanBlockDistance() {
		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		assertEquals(p.getTotalManhattanBlockDistance(), 18);

		/* test 2 */
		int[] initState = new int[9];

		initState[0] = 0;
		initState[1] = 1;
		initState[2] = 2;
		initState[3] = 3;
		initState[4] = 4;
		initState[5] = 5;
		initState[6] = 6;
		initState[7] = 7;
		initState[8] = 8;

		p = new EightPuzzle(initState, 0);
		assertNotNull(p);

		assertEquals(p.getTotalManhattanBlockDistance(), 0);

		/* test 2: 7 1 2 3 0 4 5 6 8 */
		initState[0] = 7;
		initState[1] = 1;
		initState[2] = 2;
		initState[3] = 3;
		initState[4] = 0;
		initState[5] = 4;
		initState[6] = 5;
		initState[7] = 6;
		initState[8] = 8;

		p = new EightPuzzle(initState, 0);
		assertNotNull(p);

		assertEquals(p.getTotalManhattanBlockDistance(), 8);

		/* test 4: 0 1 2 7 3 4 5 6 8 */
		initState[0] = 0;
		initState[1] = 1;
		initState[2] = 2;
		initState[3] = 7;
		initState[4] = 3;
		initState[5] = 4;
		initState[6] = 5;
		initState[7] = 6;
		initState[8] = 8;

		p = new EightPuzzle(initState, 0);
		assertNotNull(p);

		assertEquals(p.getTotalManhattanBlockDistance(), 8);
	}

	@Test
	public void testGetDepth() {
		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		assertEquals(p.getDepth(), 0);
		assertEquals(p.up(), true);
		assertEquals(p.getDepth(), 1);
	}

	@Test
	public void testPrintState() {
		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		p.printState();
	}

	@Test
	public void testUp() {
		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		assertEquals(p.toString(), "[ 7 2 4 5 0 6 8 3 1 ]");

		assertEquals(p.up(), true);
		assertEquals(p.toString(), "[ 7 2 4 5 3 6 8 0 1 ]");
//		System.out.println("moved up");
//		p.printState();

		assertEquals(p.up(), false);
		assertEquals(p.toString(), "[ 7 2 4 5 3 6 8 0 1 ]");
//		System.out.println("can't move up");
//		p.printState();
	}

	@Test
	public void testDown() {
		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		assertEquals(p.toString(), "[ 7 2 4 5 0 6 8 3 1 ]");

		assertEquals(p.down(), true);
		assertEquals(p.toString(), "[ 7 0 4 5 2 6 8 3 1 ]");
//		System.out.println("moved down");
//		p.printState();

		assertEquals(p.down(), false);
		assertEquals(p.toString(), "[ 7 0 4 5 2 6 8 3 1 ]");
//		System.out.println("can't move down");
//		p.printState();
	}

	@Test
	public void testRight() {
		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		assertEquals(p.toString(), "[ 7 2 4 5 0 6 8 3 1 ]");

		assertEquals(p.right(), true);
		assertEquals(p.toString(), "[ 7 2 4 0 5 6 8 3 1 ]");
//		System.out.println("moved right");
//		p.printState();

		assertEquals(p.right(), false);
		assertEquals(p.toString(), "[ 7 2 4 0 5 6 8 3 1 ]");
//		System.out.println("can't move right");
//		p.printState();
	}

	@Test
	public void testLeft() {
		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		assertEquals(p.toString(), "[ 7 2 4 5 0 6 8 3 1 ]");

		assertEquals(p.left(), true);
		assertEquals(p.toString(), "[ 7 2 4 5 6 0 8 3 1 ]");
//		System.out.println("moved right");
//		p.printState();

		assertEquals(p.left(), false);
		assertEquals(p.toString(), "[ 7 2 4 5 6 0 8 3 1 ]");
//		System.out.println("can't move right");
//		p.printState();
	}

	@Test
	public void testClone() {
		EightPuzzle p1 = new EightPuzzle();
		assertNotNull(p1);

		assertEquals(p1.toString(), "[ 7 2 4 5 0 6 8 3 1 ]");

		EightPuzzle p2 = p1.clone();

		assertEquals(p2.left(), true);

		assertEquals(p1.toString(), "[ 7 2 4 5 0 6 8 3 1 ]");
		assertEquals(p2.toString(), "[ 7 2 4 5 6 0 8 3 1 ]");
	}

	@Test
	public void testGetFValue() {
		EightPuzzle p = new EightPuzzle();
		assertNotNull(p);

		// [ 7 2 4 ]
		// [ 5 0 6 ]
		// [ 8 3 1 ]
//		p.printState();
		assertEquals(p.getFValue(), 26);

		assertEquals(p.left(), true);
		// [ 7 2 4 ]
		// [ 5 6 0 ]
		// [ 8 3 1 ]
		assertEquals(p.getFValue(), 26);

		assertEquals(p.right(), true);
		// [ 7 2 4 ]
		// [ 5 0 6 ]
		// [ 8 3 1 ]
		assertEquals(p.getFValue(), 28);

		assertEquals(p.up(), true);
		// [ 7 2 4 ]
		// [ 5 3 6 ]
		// [ 8 0 1 ]
		assertEquals(p.getFValue(), 28);

		assertEquals(p.down(), true);
		// [ 7 2 4 ]
		// [ 5 0 6 ]
		// [ 8 3 1 ]
		assertEquals(p.getFValue(), 30);

		/* test 2: 7 1 2 3 0 4 5 6 8 */
		int[] initState = new int[9];

		initState[0] = 7;
		initState[1] = 1;
		initState[2] = 2;
		initState[3] = 3;
		initState[4] = 0;
		initState[5] = 4;
		initState[6] = 5;
		initState[7] = 6;
		initState[8] = 8;

		p = new EightPuzzle(initState, 0);
		assertNotNull(p);

		assertEquals(p.getFValue(), 12);

		/* test 3: 0 1 2 7 3 4 5 6 8 */
		initState[0] = 0;
		initState[1] = 1;
		initState[2] = 2;
		initState[3] = 7;
		initState[4] = 3;
		initState[5] = 4;
		initState[6] = 5;
		initState[7] = 6;
		initState[8] = 8;

		p = new EightPuzzle(initState, 0);
		assertNotNull(p);

		assertEquals(p.getFValue(), 13);
	}
}

