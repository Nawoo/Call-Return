import java.util.ArrayList;

public class TokenPool {
	
	private class Token{
		public int token;
		public String sSID;
	
	public Token(String sSID, int token){
			this.sSID = sSID;
			this.token = token;
		}
	}
	private ArrayList<Token> items;
	
	public TokenPool(){
		items = new ArrayList<Token>();
	}
	
	public boolean checkToken(int token){
		for (int i = 0; i < items.size(); i++){
			if (items.get(i).token == token){
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteToken(String sSID){
		for (int i = 0; i < items.size(); i++){
			if (items.get(i).sSID.equals(sSID)){
				items.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void deleteToken(int token){
		for (int i = 0; i < items.size(); i++){
			if (items.get(i).token == token){
				items.remove(i);
			}
		}
	}
	
	public void addToken(String sSID, int token){
		items.add(new Token(sSID, token));
	}
	
	public String getSID(int token){
		for (int i = 0; i < items.size(); i++){
			if (items.get(i).token == token){
				return items.get(i).sSID;
			}
		}
		return "";
	}
}
