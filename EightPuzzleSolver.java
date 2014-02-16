import java.lang.Math;
import java.util.ArrayList;

class EightPuzzleSolver {
	int generation = 1;

	EightPuzzle curNode;

	public EightPuzzleSolver()
	{
		this(new EightPuzzle());
	}

	public EightPuzzleSolver(EightPuzzle puzzle)
	{
		this.curNode = puzzle;
	}

	public ArrayList<EightPuzzle> expandNodes()
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

//		for (int i=0 ; i<nextArray2.size() ; i++)
//		{
//			nextArray2.get(i).printState();
//		}

		return nextArray2;
	}
}
