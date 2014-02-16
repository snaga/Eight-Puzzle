import java.lang.Math;

class EightPuzzle {
	int[] state;
	int depth;

	public EightPuzzle()
	{
		state = new int[9];

		state[0] = 7;
		state[1] = 2;
		state[2] = 4;
		state[3] = 5;
		state[4] = 0;
		state[5] = 6;
		state[6] = 8;
		state[7] = 3;
		state[8] = 1;

		depth = 0;
	}

	public EightPuzzle(int[] initialState, int dep)
	{
		state = new int[9];

		for (int i=0 ; i<9 ; i++)
		{
			state[i] = initialState[i];
		}

		depth = dep;
	}


	public String toString()
	{
		return "[ " + state[0] +
		       " "  + state[1] +
		       " "  + state[2] +
		       " "  + state[3] +
		       " "  + state[4] +
		       " "  + state[5] +
		       " "  + state[6] +
		       " "  + state[7] +
		       " "  + state[8] +
		       " ]";
	}

	public int[] getState()
	{
		return state;
	}

	public int getPosX(int val)
	{
		for (int i=0 ; i<9 ; i++)
		{
			if (state[i] == val)
			{
				return i % 3;
			}
		}
		return -1;
	}

	public int getPosY(int val)
	{
		for (int i=0 ; i<9 ; i++)
		{
			if (state[i] == val)
			{
				return (int)Math.floor(i / 3);
			}
		}
		return -1;
	}

	public int getTile(int x, int y)
	{
		if (x >= 3 || y >= 3)
			return -1;

		return state[y*3 + x];
	}

	public boolean compareState(int[] compState)
	{
		for (int i=0 ; i<9 ; i++)
		{
			if (state[i] != compState[i])
			{
				return false;
			}
		}
		return true;
	}

	public boolean isGoalState()
	{
		int[] goalState = new int[9];

		goalState[0] = 0;
		goalState[1] = 1;
		goalState[2] = 2;
		goalState[3] = 3;
		goalState[4] = 4;
		goalState[5] = 5;
		goalState[6] = 6;
		goalState[7] = 7;
		goalState[8] = 8;

		return compareState(goalState);
	}

	public int getNumberOfMisplacedTiles()
	{
		int misplaced = 0;

		for (int i=1 ; i<9 ; i++)
		{
			if (state[i] != i)
			{
				misplaced++;
			}
		}
		return misplaced;
	}

	public int getManhattanBlockDistance(int val)
	{
		int distance = 0;
		int curX, curY;
		int x2, y2;

		curX = getPosX(val);
		curY = getPosY(val);

		if (val == 1)
		{
			x2 = 1;
			y2 = 0;
		}
		else if (val == 2)
		{
			x2 = 2;
			y2 = 0;
		}
		else if (val == 3)
		{
			x2 = 0;
			y2 = 1;
		}
		else if (val == 4)
		{
			x2 = 1;
			y2 = 1;
		}
		else if (val == 5)
		{
			x2 = 2;
			y2 = 1;
		}
		else if (val == 6)
		{
			x2 = 0;
			y2 = 2;
		}
		else if (val == 7)
		{
			x2 = 1;
			y2 = 2;
		}
		else if (val == 8)
		{
			x2 = 2;
			y2 = 2;
		}
		else
		{
			return 0;
		}

		distance = Math.abs(curX - x2) + Math.abs(curY - y2);

		return distance;
	}

	public int getTotalManhattanBlockDistance()
	{
		int distance = 0;

		for (int i=1 ; i<9 ; i++)
		{
			distance += getManhattanBlockDistance(i);
		}

		return distance;
	}

	public int getDepth()
	{
		return depth;
	}

	public void printState()
	{
		System.out.println("--------------------------");
		System.out.println("[ " + state[0] + " " + state[1] + " " + state[2] + " ]");
		System.out.println("[ " + state[3] + " " + state[4] + " " + state[5] + " ]");
		System.out.println("[ " + state[6] + " " + state[7] + " " + state[8] + " ]");

		System.out.println("f-value: " + getFValue()
                                      + " (" + getNumberOfMisplacedTiles()
                                      + "+" + getTotalManhattanBlockDistance() + ")");
		System.out.println("--------------------------");
	}

	public boolean up()
	{
		int x = getPosX(0);
		int y = getPosY(0);

		if (y == 2)
			return false;

		int val = getTile(x, y+1);

		state[3*y + x]     = val;
		state[3*(y+1) + x] = 0;

		depth++;

		return true;
	}

	public boolean down()
	{
		int x = getPosX(0);
		int y = getPosY(0);

		if (y == 0)
			return false;

		int val = getTile(x, y-1);

		state[3*y + x]     = val;
		state[3*(y-1) + x] = 0;

		depth++;

		return true;
	}

	public boolean right()
	{
		int x = getPosX(0);
		int y = getPosY(0);

		if (x == 0)
			return false;

		int val = getTile(x-1, y);

		state[3*y + x]   = val;
		state[3*y + x-1] = 0;

		depth++;

		return true;
	}

	public boolean left()
	{
		int x = getPosX(0);
		int y = getPosY(0);

		if (x == 2)
			return false;

		int val = getTile(x+1, y);

		state[3*y + x]   = val;
		state[3*y + x+1] = 0;

		depth++;

		return true;
	}

	public EightPuzzle clone()
	{
		return new EightPuzzle(state, depth);
	}

	public int getFValue()
	{
		return depth + getNumberOfMisplacedTiles()
		          + getTotalManhattanBlockDistance();
	}
}
