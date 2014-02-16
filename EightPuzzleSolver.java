import java.lang.Math;
import java.util.ArrayList;

class EightPuzzleSolver {
	int generation = 1;

	ArrayList allNodes;

	static int MAX_DEPTH = 10000;

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
		if (isFoundInArrayList(allNodes, node))
			return false;

		return true;
	}

	public boolean isFoundInArrayList(ArrayList<EightPuzzle> list, EightPuzzle fringe)
	{
		boolean found = false;

		for (int i=0 ; i<list.size() ; i++)
		{
			EightPuzzle elem = list.get(i);

			if (elem.toString().equals(fringe.toString()))
				found = true;
		}

		return found;
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
		EightPuzzle node = startNode;

		ArrayList<EightPuzzle> fringes = expandNodes(node);

		startNode.printState();

		try {
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
		}

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

			EightPuzzle successor = null;

			// pick next node with the latest cost from fringe list
			fringes = sortList(fringes);
			node = fringes.remove(0);

			System.out.println("N> " + node.toString() + ", f-value = " + node.getFValue() + "(depth:" + node.getDepth() + ")");

			if (node.isGoalState())
			{
				System.out.println("Goal found.");
				node.printState();
				return true;
			}

			ArrayList<EightPuzzle> successors = expandNodes(node);
			while (successors.size() > 0)
			{
				boolean go_nextdepth = false;

				successor = successors.remove(0);

				if (notInAllNodes(successor))
				{
					if (successor.isGoalState())
					{
						System.out.println("Goal found.");
						successor.printState();
						return true;
					}

					System.out.println("S> " + successor.toString() + ", f-value = " + successor.getFValue() + "(depth:" + successor.getDepth() + ")");

					ArrayList<EightPuzzle> newFringes = expandNodes(successor);

					// add only new frienge elements to the fringe list.
					for (int k=0 ; k<newFringes.size() ; k++)
					{
						EightPuzzle newf = newFringes.get(k);

						if (k == 0)
						{
							if (newf.getFValue() < successor.getFValue())
							{
								go_nextdepth = true;
							}
						}

						if (!isFoundInArrayList(fringes, newf))
						{
							fringes.add(newf);
							System.out.println("F> " + fringes.get(k).toString() + ", f-value = " + fringes.get(k).getFValue() + "(depth:" + fringes.get(k).getDepth() + ")");
						}
						else
						{
							System.out.println("f> " + fringes.get(k).toString() + ", f-value = " + fringes.get(k).getFValue() + "(depth:" + fringes.get(k).getDepth() + ")");
						}

						if (go_nextdepth)
							break;
					}
					System.out.println("fringes = " + fringes.size());

					addAllNodes(successor);

					if (go_nextdepth)
						break;
				}
				else
				{
					System.out.println("s> " + successor.toString() + ", f-value = " + successor.getFValue() + "(depth:" + successor.getDepth() + ")");
				}
			}
		}

		node.printState();

		return false;
	}

	public ArrayList<EightPuzzle> sortList(ArrayList<EightPuzzle> list)
	{
		/* sort next nodes here */
		ArrayList<EightPuzzle> newList = new ArrayList<EightPuzzle>();

		while (list.size() > 0)
		{
			/* determine maximum f-value. */
			int max = 0;
			for (int i=0 ; i<list.size() ; i++)
			{
				EightPuzzle tmp = (EightPuzzle)list.get(i);

				if (tmp.getFValue() > max)
				{
					max = tmp.getFValue();
				}
			}

			/* look for a node which has max f-value. */
			for (int i=0 ; i<list.size() ; i++)
			{
				EightPuzzle tmp = (EightPuzzle)list.get(i);

				if (tmp.getFValue() == max)
				{
					list.remove(i);
					newList.add(0, tmp);
				}
			}
		}

//		for (int i=0 ; i<newList.size() ; i++)
//		{
//			System.out.println("sortList: " + newList.get(i).toString() + ", f-value=" + newList.get(i).getFValue());
//		}

		return newList;
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

		nextArray = sortList(nextArray);

		for (int i=0 ; i<nextArray.size() ; i++)
		{
			System.out.println(" +" + nextArray.get(i).toString() + ", f-value = " + nextArray.get(i).getFValue() + "(depth:" + nextArray.get(i).getDepth() + ")");
		}

		return nextArray;
	}
}
