import java.util.List;

public class State {
	
	private List<State> listOfEtatSuivants;
	private String nom ; 
	
	public List<State> getSuivants(){
		return listOfEtatSuivants;
	}
	
	public void setSuivants(List<State> list){
		listOfEtatSuivants = list;
	}
	
	public void addSuivant(State s){
		listOfEtatSuivants.add(s);
	}
	
	public String getNom(){
		return nom;
		}

	public static List<State> reverse() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addTransition(State choice, StateChart transformENtoSC) {
		// TODO Auto-generated method stub
		
	}
	
}
