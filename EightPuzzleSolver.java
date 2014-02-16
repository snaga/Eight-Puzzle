import java.lang.Math;
import java.util.ArrayList;

class EightPuzzleSolver {
	int generation = 1;

	ArrayList allNodes;

	static int MAX_DEPTH = 1000;

	public EightPuzzleSolver()
	{
		this.allNodes = new ArrayList<EightPuzzle>();
	}

	public boolean addAllNodes(EightPuzzle node)
	{
		if (notInAllNodes(node))
		{
			allNodes.add(node);
			return true;
		}

		return false;
	}

	public boolean notInAllNodes(EightPuzzle node)
	{
		for (int i=0 ; i<allNodes.size() ; i++)
		{
			EightPuzzle n = (EightPuzzle)allNodes.get(i);

			if (n.toString().equals(node.toString()))
			{
				return false;
			}
		}
		return true;
	}

	public void printAllNodes()
	{
		System.out.println("printAllNodes -------------------------");
		for (int i=0 ; i<allNodes.size() ; i++)
		{
			System.out.println(allNodes.get(i).toString());
		}
	}

	public boolean search(EightPuzzle startNode)
	{
		ArrayList<EightPuzzle> fringes = expandNodes(startNode);
		int depth = 1;
		EightPuzzle node = null;

		startNode.printState();

		for (int i=0 ; i<fringes.size() ; i++)
		{
			addAllNodes(fringes.get(i));
		}
		printAllNodes();

		for (int i=0 ; i<EightPuzzleSolver.MAX_DEPTH ; i++)
		{
			if (fringes.size() == 0)
			{
				System.out.println("Empty fringe. Failed.");
				return false;
			}

			boolean trysecond = false;

			// next node with the lowest cost (first try)
			EightPuzzle successor = null;

			node = fringes.remove(0);
			System.out.println("1> " + node.toString() + ", f-value = " + node.getFValue(depth));

			if (node.isGoalState())
			{
				System.out.println("Goal found.");
				return true;
			}

			ArrayList<EightPuzzle> first_successors = expandNodes(node);
			EightPuzzle first = null;
			while (first_successors.size() > 0)
			{
				first = (EightPuzzle)first_successors.remove(0);
				if (notInAllNodes(first))
					break;
				else
					first = null;
			}

			// second
			node = fringes.remove(0);
			System.out.println("2> " + node.toString() + ", f-value = " + node.getFValue(depth));

			if (node.isGoalState())
			{
				System.out.println("Goal found.");
				return true;
			}

			ArrayList<EightPuzzle> second_successors = expandNodes(node);
			EightPuzzle second = null;
			while (second_successors.size() > 0)
			{
				second = (EightPuzzle)second_successors.remove(0);
				if (notInAllNodes(second))
					break;
				else
					second = null;
			}

			if (first != null)
				System.out.println("First:  " + first.toString() + ", f-value = " + first.getFValue());
			if (second != null)
				System.out.println("Second: " + second.toString() + ", f-value = " + second.getFValue());

			if (first == null)
			{
				successor = second;
			}
			else if (second == null)
			{
				successor = first;
			}
			else
			{
				if ( first.getFValue(depth+1) <= second.getFValue(depth+1) )
					successor = first;
				else
					successor = second;
			}

			if (successor != null)
			{
				System.out.println("S> " + successor.toString() + ", f-value = " + successor.getFValue(depth+1));
				fringes = expandNodes(successor);
				depth++;
				addAllNodes(successor);
//				printAllNodes();
				continue;
			}
		}

		node.printState();

		return false;
	}

	public ArrayList<EightPuzzle> expandNodes(EightPuzzle curNode)
	{
		ArrayList<EightPuzzle> nextArray = new ArrayList<EightPuzzle>();
		EightPuzzle tmpNew;

		/* up */
		tmpNew = curNode.clone();
		if (tmpNew.up())
		{
			nextArray.add(tmpNew);
		}

		/* down */
		tmpNew = curNode.clone();
		if (tmpNew.down())
		{
			nextArray.add(tmpNew);
		}

		/* left */
		tmpNew = curNode.clone();
		if (tmpNew.left())
		{
			nextArray.add(tmpNew);
		}

		/* right */
		tmpNew = curNode.clone();
		if (tmpNew.right())
		{
			nextArray.add(tmpNew);
		}

		/* sort next nodes here */
		ArrayList<EightPuzzle> nextArray2 = new ArrayList<EightPuzzle>();

		while (nextArray.size() > 0)
		{
			/* determine maximum f-value. */
			int max = 0;
			for (int i=0 ; i<nextArray.size() ; i++)
			{
				EightPuzzle tmp = (EightPuzzle)nextArray.get(i);

				if (tmp.getFValue() > max)
				{
					max = tmp.getFValue();
				}
			}

			/* look for a node which has max f-value. */
			for (int i=0 ; i<nextArray.size() ; i++)
			{
				EightPuzzle tmp = (EightPuzzle)nextArray.get(i);

				if (tmp.getFValue() == max)
				{
					nextArray.remove(i);
					nextArray2.add(0, tmp);
				}
			}
		}

		for (int i=0 ; i<nextArray2.size() ; i++)
		{
			System.out.println(" +" + nextArray2.get(i).toString() + ", (f-value = " + nextArray2.get(i).getFValue() + ")");
		}

		return nextArray2;
	}
}
