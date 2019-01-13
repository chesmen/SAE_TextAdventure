package common;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Question {
	
	private String question;
	private Antwort[] antworts =new Antwort[4];
	private int questionID;
	ResultSet result;
	private int numAntworts = 0;
	
		public Question(String question, int questionID) throws SQLException 
		{
			
			this.question = question;
			this.questionID = questionID;
			String query = "SELECT antwort, richtig FROM antworts WHERE questionFK="+ this.questionID;
			result = Manager.getStMan().query(query);
			while(result.next())
			{
				String antwort = result.getString("antwort");
				boolean richtig = result.getBoolean("richtig");
				antworts[numAntworts]= new Antwort(antwort, richtig);
				numAntworts++;
			}
		}

		@Override
		public String toString() {
			String display="";
			String letters = "ABCD";
			
			for(int i =0; i < numAntworts; i++)
			{
				display+=antworts[i].toString(letters.charAt(i));
			}
			return question+"\n\n" +display;
		}
		
		public boolean isCorrect(char letter)
		{
			String letters = "ABCD";
			int pos = letters.indexOf(letter+"");
			return antworts[pos].isCorrect();
		}
		
		
		

}
