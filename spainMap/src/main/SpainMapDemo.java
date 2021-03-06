package main;

import java.util.ArrayList;

import aima.core.environment.map.AdaptableHeuristicFunction;
import aima.core.environment.map.ExtendableMap;
import aima.core.environment.map.MapAgent;
import aima.core.environment.map.MapEnvironment;
import aima.core.environment.map.Scenario;
import aima.core.util.datastructure.Point2D;
import aima.gui.applications.search.map.AbstractMapAgentController;
import aima.gui.applications.search.map.ExtendedMapAgentView;
import aima.gui.applications.search.map.MapAgentFrame;
import aima.gui.applications.search.map.SearchFactory;
import aima.gui.framework.AgentAppController;
import aima.gui.framework.AgentAppEnvironmentView;
import aima.gui.framework.AgentAppFrame;
import aima.gui.framework.MessageLogger;
import aima.gui.framework.SimpleAgentApp;

public class SpainMapDemo extends SimpleAgentApp {

	/** Creates a <code>MapAgentView</code>. */
	public AgentAppEnvironmentView createEnvironmentView() {
		return new ExtendedMapAgentView();
	}
	
	/** Creates and configures a <code>RouteFindingAgentFrame</code>. */
	@Override
	public AgentAppFrame createFrame() {
		return new RouteFindingAgentFrame();
	}

	/** Creates a <code>RouteFindingAgentController</code>. */
	@Override
	public AgentAppController createController() {
		return new RouteFindingAgentController();
	}

	// //////////////////////////////////////////////////////////
	// local classes

	/** Frame for a graphical route finding agent application. */
	protected static class RouteFindingAgentFrame extends MapAgentFrame {
		private static final long serialVersionUID = 1L;

		public static enum MapType {
			SPAIN
		};

		private MapType usedMap = null;
		private static String[] SPAIN_DESTS = new String[] {
				"to Le�n", "to Madrid", "to Salamanca", "to Guadalajara" , "To Albacete", "To Ciudad Real",
				"to Random" };

		/** Creates a new frame. */
		public RouteFindingAgentFrame() {
			setTitle("Spain Map");
			setSelectorItems(SCENARIO_SEL, new String[] {
					"Spain, from Madrid",
					"Spain, from Random"}, 0);
			setSelectorItems(SEARCH_MODE_SEL, SearchFactory.getInstance()
					.getSearchModeNames(), 1); // change the default!
			setSelectorItems(HEURISTIC_SEL, new String[] { "=0", "SLD" }, 1);
		}

		/**
		 * Changes the destination selector items depending on the scenario
		 * selection if necessary, and calls the super class implementation
		 * afterwards.
		 */
		@Override
		protected void selectionChanged(String changedSelector) {
			RouteFindingAgentFrame.MapType mtype = MapType.SPAIN;
			if (mtype != usedMap) {
				usedMap = mtype;
				setSelectorItems(DESTINATION_SEL, SPAIN_DESTS, 0);
			}
			super.selectionChanged(changedSelector);
		}
	}

	/** Controller for a graphical route finding agent application. */
	protected static class RouteFindingAgentController extends
			AbstractMapAgentController {
		/**
		 * Configures a scenario and a list of destinations. Note that for route
		 * finding problems, the size of the list needs to be 1.
		 */
		@Override
		protected void selectScenarioAndDest(int scenarioIdx, int destIdx) {
			ExtendableMap map = new ExtendableMap();
			MapEnvironment env = new MapEnvironment(map);
			String agentLoc = null;
			switch (scenarioIdx) {
			case 0:
				SimplifiedRoadOfSpain.initMap(map);
				agentLoc = SimplifiedRoadOfSpain.MADRID;
				break;
			case 1:
				SimplifiedRoadOfSpain.initMap(map);
				agentLoc = map.randomlyGenerateDestination();
				break;
			}
			scenario = new Scenario(env, map, agentLoc);

			destinations = new ArrayList<String>();
				switch (destIdx) {
				case 0:
					destinations
							.add(SimplifiedRoadOfSpain.LEON);
					break;
				case 1:
					destinations.add(SimplifiedRoadOfSpain.MADRID);
					break;
				case 2:
					destinations.add(SimplifiedRoadOfSpain.SALAMANCA);
					break;
				case 3:
					destinations.add(SimplifiedRoadOfSpain.GUADALAJARA);
					break;
				case 4:
					destinations.add(SimplifiedRoadOfSpain.ALBACETE);
					break;
				case 5:
					destinations.add(SimplifiedRoadOfSpain.CIUDADREAL);
					break;
				case 6:
					destinations.add(map.randomlyGenerateDestination());
					break;
				}
		}

		/**
		 * Prepares the view for the previously specified scenario and
		 * destinations.
		 */
		@Override
		protected void prepareView() {
			ExtendedMapAgentView mEnv = (ExtendedMapAgentView) frame.getEnvView();
			mEnv.setData(scenario, destinations, null);
			mEnv.setEnvironment(scenario.getEnv());
		}

		/**
		 * Returns the trivial zero function or a simple heuristic which is
		 * based on straight-line distance computation.
		 */
		@Override
		protected AdaptableHeuristicFunction createHeuristic(int heuIdx) {
			AdaptableHeuristicFunction ahf = null;
			switch (heuIdx) {
			case 0:
				ahf = new H1();
				break;
			default:
				ahf = new H2();
			}
			return ahf.adaptToGoal(destinations.get(0), scenario
					.getAgentMap());
		}

		/**
		 * Creates a new agent and adds it to the scenario's environment.
		 */
		@Override
		public void initAgents(MessageLogger logger) {
			if (destinations.size() != 1) {
				logger.log("Error: This agent requires exact one destination.");
				return;
			}
			MapEnvironment env = scenario.getEnv();
			String goal = destinations.get(0);
			MapAgent agent = new MapAgent(env.getMap(), env, search, new String[] { goal });
			env.addAgent(agent, scenario.getInitAgentLocation());
		}
	}

	/**
	 * Returns always the heuristic value 0.
	 */
	static class H1 extends AdaptableHeuristicFunction {

		public double h(Object state) {
			return 0.0;
		}
	}

	/**
	 * A simple heuristic which interprets <code>state</code> and {@link #goal}
	 * as location names and uses the straight-line distance between them as
	 * heuristic value.
	 */
	static class H2 extends AdaptableHeuristicFunction {

		public double h(Object state) {
			double result = 0.0;
			Point2D pt1 = map.getPosition((String) state);
			Point2D pt2 = map.getPosition((String) goal);
			if (pt1 != null && pt2 != null)
				result = pt1.distance(pt2);
			return result;
		}
	}

	// //////////////////////////////////////////////////////////
	// starter method

	/** Application starter. */
	public static void main(String args[]) {
		new SpainMapDemo().startApplication();
	}
}

