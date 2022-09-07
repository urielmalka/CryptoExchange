package Model;

public class BuyCoinModel {
	
	private Model model;
	
	/*
	 *  In this class we buying and selling Crypto coins to our wallet.
	 *  return coin with dollar value
	 *  
	 */
	
	public BuyCoinModel(Model model) {
		this.model = model;
	}
	
	
	public String buyRecive(double input , double value) {
		return String.format("%f", input/value);
	}
	
	public String sellRecive(double input , double value) {
		return String.format("%f", input*value);
	}
	
	public String checkInput(String value) {
		
		boolean point,out;
		StringBuilder build = new StringBuilder(value);
		do{
			out = false;
			point = false;
			
			for(int i=0 ; i < build.length() ; i++) {
				if(build.charAt(i) == '.' && !point)
					point = true;
				else if(build.charAt(i) == '.') {
					build.deleteCharAt(i);
					out = true;
					break;
				}
				
				if(!(build.charAt(i) >= '0' && build.charAt(i) <= '9') && build.charAt(i) != '.') {
					build.deleteCharAt(i);
					out = true;
					break;
				}
			}		
		}while(out);
		
		return build.toString();
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */

}
