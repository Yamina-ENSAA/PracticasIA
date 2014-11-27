package aima.core.environment.spainmap;

import aima.core.search.framework.GoalTest;

public class MapGoalTest implements GoalTest{

	Map goal = new Map(new City("Test",0,0));
	
	@Override
	public boolean isGoalState(Object state) {
		Map map = (Map) state;
		return map.equals(goal);
	}

}