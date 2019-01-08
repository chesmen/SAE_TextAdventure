package adventure;

public class Player {
	int life; //Spielerleben/Anzahl der Versuche, wenn 0 dann GameOver
	String name; //TODO Spielername????
	int level; //Anzahl der aabsolvierten Minispiele
	
	
	public Player() {
		life = 3; //default = 3, für den Fall dass die Schwierigkeit nicht ausgesucht wird
		level = 0;
	}
	
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void addLife(int bonus) {
		this.life += bonus;
	}
	public void lostLife() {
		this.life += -1;
	}
	
	
	
	
}
