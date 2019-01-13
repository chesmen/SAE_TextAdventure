package common;

public class Antwort {
	
	private String antwort;
	private boolean correct;
	
	public Antwort(String antwort, boolean correct) {
		
		this.antwort = antwort;
		this.correct = correct;
	}

	public String getAntwort() {
		return antwort;
	}

	public void setAntwort(String antwort) {
		this.antwort = antwort;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	
	public String toString(char letter) {
		return letter+": "+antwort;
	}
	
	

}
