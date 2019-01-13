package common;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager {

	private static StorageManager stMan;
	private Question[] questions = new Question[500];
	private int numQuestions;
	private int level;
	private ResultSet result;
	private Question currentQuestion;
	private int lives;
	
	public static int getRandom(int iMin, int iMax)
	{
		int iRand = 0;
		iRand = (int)Math.round(Math.random()*(iMax-iMin))+iMin;
		return iRand;
	}
	
	public Manager(String databankName) throws ClassNotFoundException, SQLException
	{
		stMan = new StorageManager(databankName);
		level = 1;
		lives = 3;
		populateQuestions();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public static StorageManager getStMan() {
		return stMan;
	}

	public void populateQuestions() throws SQLException
	{
		numQuestions = 0;
		String query = "SELECT questionID, question FROM questions WHERE level=" +level;
		result = getStMan().query(query);//stMan.query(query) could be used insted!
		while(result.next())
		{
			int questionID = result.getInt("questionID");
			String question = result.getString("question");
			addQuestion(question, questionID);
		}
		
		
	}
	
	public void addQuestion(String question, int questionID) throws SQLException
	{
		questions[numQuestions] = new Question(question, questionID);
		numQuestions++;
	}
	
	public void increaselevel() throws SQLException
	{
		level++;
		populateQuestions();
	}
	
	public String getQuestion()
	{
		currentQuestion =questions[getRandom(0,numQuestions-1)];
		return currentQuestion.toString();
	}
	
	public boolean antwor(char antwort)
	{
		if(currentQuestion.isCorrect(antwort))
		{
			level++;
			return true;
		}else {
			lives--;
			return false;
		}
	}

}
