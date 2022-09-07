package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import CryproCoin.Coin;
import CryproCoin.Coinable;
import CryproCoin.Market;
import CryproCoin.MyCoin;
import CryproCoin.StableCoin;
import CryproCoin.Trader;

public class CoinSQL {
	
	private ResultSet rs;
	private Connection conn = null;
	private Statement stat;
	private Market market;
	
	private static CoinSQL coinSQL = new CoinSQL();
	
	public static CoinSQL getSQL() {
		return coinSQL;
	}
		
	private CoinSQL() {
		market = Market.getMarket();	
		/*
		* Set your DB deatali for see the project
		*/
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbUrl = "jdbc:mysql://127.0.0.1:3306/coins";
			conn = DriverManager.getConnection(dbUrl,"root","my password");
			stat = conn.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void read() {
		readCoins();
		readFavorite();
		readBalance();
	}
	
	
	
	public void readCoins() {
		ArrayList<String> coinsName = new ArrayList<String>();
		try {
			rs = stat.executeQuery("select CNAME from coins;");
			while(rs.next()) {
				if(rs.getString("CNAME").equals("USD"))
					continue;
				coinsName.add(rs.getString("CNAME"));
			}
			
			//Add & Create new coins
			market.setCoins(coinsName);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public double readValueCoins(Coin coin) {
		try {
			rs = stat.executeQuery("SELECT CNAME,dollar_value\r\n"
									+ "FROM coins\r\n"
									+ "LEFT JOIN coins_value\r\n"
									+ "ON coins.CID = coins_value.CID\r\n"
									+ "WHERE CNAME = '"+ coin.getCoinName() +"';");
			while(rs.next()) {
				return rs.getDouble("dollar_value");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public void readFavorite() {
		try {
			rs = stat.executeQuery("select * from favorite;");
			while(rs.next()) {	
				Trader.addFavorite(market.getCoins().get(rs.getInt("CID")-1));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void readBalance() {
		try {
			Trader.getWallet().getHold().add(new MyCoin(new StableCoin(),0));
			rs = stat.executeQuery("select * from Balance;");
			while(rs.next()) {		
				if(rs.getInt("CID") == 0) {
					Trader.getWallet().getHold().get(0).buy(rs.getDouble("Amount"));
					continue;
				}				
				Trader.getWallet().addMyCoin(market.getCoins().get(rs.getInt("CID")-1),rs.getDouble("Amount"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertFavorite(Coinable coin) {
		try {
			stat.executeUpdate("INSERT INTO Favorite\r\n"
					+ "values(\r\n"
					+ "(SELECT CID FROM COINS\r\n"
					+ "WHERE CNAME = '" +coin.getCoinName()+ "')\r\n"
					+ ")");
		}catch(Exception e) { 
			System.out.println(e);
		}

	}
	
	public void deleteFavorite(Coinable coin) {
		try {
			stat.executeUpdate("DELETE FROM Favorite\r\n"
							+ "WHERE CID = "
							+ "(SELECT CID FROM COINS\r\n"
							+ "WHERE CNAME = '" +coin.getCoinName()+ "')");
		}catch(Exception e) { 
			System.out.println(e);
		}
	}
	
	
	public void insertUpdateBalance(Coinable coin,double amount) {
		
		try {
			if(amount <= 0)
			{
				stat.executeUpdate("DELETE FROM BALANCE WHERE CID = "
										+"(SELECT CID FROM COINS "
										+ "WHERE CNAME = '" +coin.getCoinName()+ "')");
				return;
			}

			rs = stat.executeQuery("SELECT CID FROM BALANCE WHERE CID = "
										+"(SELECT CID FROM COINS "
										+ "WHERE CNAME = '" +coin.getCoinName()+ "')");
			while(rs.next()) {
				
				stat.executeUpdate("UPDATE Balance "
						+ "SET AMOUNT = " + amount +" "
						+ "WHERE CID = " + rs.getInt("CID"));

				return;
			}
			
			stat.executeUpdate("INSERT INTO Balance "
					+ "values("
					+ "(SELECT CID FROM COINS "
					+ "WHERE CNAME = '" +coin.getCoinName()+ "'),"
					+ amount
					+ ")");
			

			
		}catch(Exception e) { 
			System.out.println(e);
		}

	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */

}
