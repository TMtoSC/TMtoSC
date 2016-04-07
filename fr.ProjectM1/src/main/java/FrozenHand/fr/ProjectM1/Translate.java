import java.util.Collections;
import java.util.List;

public class Translate {

	private static final CompositeState Choice = null;
	private TaskModel taskModel;
	private StateChart stateChart;

	public Translate() {
		taskModel = new TaskModel();
		stateChart = new StateChart();
	}

	public Translate(TaskModel tm, StateChart sc) {
		taskModel = tm;
		stateChart = sc;
	}

	public void run() {

	}

	public StateChart transformENtoSC(List<State> listS) {
		StateChart sc = new StateChart();
		CompositeState cs = new CompositeState();
		State initial = sc.addInitialState();
		cs = sc.addCompositeState();
		sc.addTransition(initial, cs);
		Object prevObj = null;
		for (State s : listS) {
			cs.addState(s);
			if (prevObj != null) {
				cs.addTransition(prevObj, s);
			}
			prevObj = s;
		}
		return sc;
	}

	public StateChart transformCHtoSC(List<State> listS) {
		StateChart sc = new StateChart();
		CompositeState cs = new CompositeState();
		cs = sc.addCompositeState();
		State initial = cs.addInitialState();
		State choice = cs.addState(Operator.Choice);
		sc.addTransition(initial, choice);
		for (State s : listS) {
			cs.addState(s);
			cs.addTransition(choice, s);
		}
		return sc;
	}

	public StateChart transormCONCtoSc(List<State> listS) {
		StateChart sc = new StateChart();
		OrtogonalState temp = sc.addOrtogonalState();
		temp.addLeft(temp.addState(listS.get(0)));
		if (listS.size() > 2) {
			for (int i = 1; i < listS.size(); i++) {
				temp = temp.addRight(OrtogonalState.addOrtogonalState());
				temp.addLeft(temp.addState(listS.get(i)));
				if (i == listS.size() - 1) {
					temp.addRight(temp.addState(listS.get(i)));
				}
			}
		} else
			temp.addRight(temp.addState(listS.get(1)));

		return sc;
	}
	
	public StateChart transformDItoSc(State on , State disable){
		StateChart sc = new StateChart();
		CompositeState cs1 = sc.addCompositeState(on);
		CompositeState cs2 = sc.addCompositeState(disable);
		cs1.addComponentStateTransition(cs2);

	return sc;
	}
	
	public StateChart transformSUREtoSc(State on ,State suspend){
		StateChart sc = new StateChart();
		CompositeState cs1 = sc.addCompositeState();
		cs1.addDeepHistory();
		CompositeState cs2 = sc.addCompositeState();
		cs1.addComponentStateTransition(cs2);
		cs2.addComponentStateTransition(cs1);

	return sc;
	}
	
	public StateChart transformOItoSC(List<State> on){
		StateChart sc = new StateChart();
	State choice = sc.addCompositeState(Operator.Choice);
	choice.addTransition(choice, transformENtoSC(on));
	choice.addTransition(choice,transformENtoSC(reverse(on)));

	return sc;
	}


	private List<State> reverse(List<State> on) {
		// TODO Auto-generated method stub
		List<State> tab = null;
		for ( int i = on.size() ; i >= 0;i--){
			tab.add(on.get(i));
		}
		return tab;
	}




}
