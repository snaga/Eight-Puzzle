import static org.junit.Assert.*;
import org.junit.*;
import junit.textui.TestRunner;

import java.util.ArrayList;

public class TestEightPuzzleSolver {

	@Test
	public void testEightPuzzleSolver() {
		EightPuzzleSolver s = new EightPuzzleSolver();

		assertNotNull(s);
	}

	@Test
	public void testExpandNodes() {
		EightPuzzleSolver s = new EightPuzzleSolver();
		assertNotNull(s);

		ArrayList<EightPuzzle> nodes = s.expandNodes(new EightPuzzle());

		assertNotNull(nodes);

		assertNotNull(nodes.get(0));
		assertEquals(nodes.get(0).toString(), "[ 7 2 4 5 6 0 8 3 1 ]");

		assertNotNull(nodes.get(1));
		assertEquals(nodes.get(1).toString(), "[ 7 2 4 0 5 6 8 3 1 ]");

		assertNotNull(nodes.get(2));
		assertEquals(nodes.get(2).toString(), "[ 7 2 4 5 3 6 8 0 1 ]");

		assertNotNull(nodes.get(3));
		assertEquals(nodes.get(3).toString(), "[ 7 0 4 5 2 6 8 3 1 ]");
	}

	@Test
	public void testAddAllNodes() {
		EightPuzzleSolver s = new EightPuzzleSolver();
		assertNotNull(s);

		EightPuzzle node = new EightPuzzle();

		assertEquals(s.addAllNodes(node), true);
		assertEquals(s.addAllNodes(node), false);
	}

	@Test
	public void testNotInAllNodes() {
		EightPuzzleSolver s = new EightPuzzleSolver();
		assertNotNull(s);

		EightPuzzle node = new EightPuzzle();

		assertEquals(s.notInAllNodes(node), true);

		assertEquals(s.addAllNodes(node), true);

		assertEquals(s.notInAllNodes(node), false);

		assertEquals(s.addAllNodes(node), false);
	}

	@Test
	public void testSearch() {
		int[] state = new int[9];

		state[0] = 1;
		state[1] = 2;
		state[2] = 0;
		state[3] = 3;
		state[4] = 4;
		state[5] = 5;
		state[6] = 6;
		state[7] = 7;
		state[8] = 8;

		EightPuzzleSolver s = new EightPuzzleSolver();
		assertNotNull(s);

//		assertEquals(s.search(new EightPuzzle(state, 0)), true);
		assertEquals(s.search(new EightPuzzle()), true);
	}

}

