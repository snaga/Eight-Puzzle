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

		ArrayList<EightPuzzle> nodes = s.expandNodes();

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

}

