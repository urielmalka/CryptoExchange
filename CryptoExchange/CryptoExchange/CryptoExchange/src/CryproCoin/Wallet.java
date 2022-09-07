package CryproCoin;

import java.util.ArrayList;

public class Wallet implements Runnable{
	
	/*
	 * All the money set here.
	 * ArrayList<MyCoin> hold -> our coins.
	 */
	
	private ArrayList<MyCoin> hold;
	private double balance;
	private Thread coinThread;
	
	public Wallet() {
		hold = new ArrayList<MyCoin>();
		
		coinThread = new Thread(this);
		coinThread.start();		
		balance = 0;
		
	}
	
	public MyCoin addMyCoin(Coin coin , double amount) {
		MyCoin myCoin = ifExist(coin);
		if(myCoin == null) {
			myCoin = new MyCoin(coin,amount);
			hold.add(myCoin);
		}			
		else
			myCoin.setAmount(amount);
		return myCoin;
	}
	
	public MyCoin ifExist(Coinable coin) {
		for(MyCoin myCoin : hold) {
			if(myCoin.getCoin().getCoinName() == coin.getCoinName())
				return myCoin;
		}
		return null;
	}
	
	public void calculate() {
		double temp = 0;
		for(MyCoin myCoin : hold){
			temp+=myCoin.getDollarValue();
		}
		balance = temp;
	}
	
	public void print() {
		for(MyCoin myCoin : hold) {
			System.out.println(myCoin.getCoin().getCoinName() + " " + myCoin.getAmount());
		}
		System.out.println("BLALANCE : " + balance);
		
	}
	
	public ArrayList<MyCoin> getHold(){
		return hold;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void clearCoinIfZero() {
		for(MyCoin myCoin : hold) {
			if(myCoin.getAmount() <= 0) {
				hold.remove(myCoin);
				break;
			};				
		}
	}

	@Override
	public void run() {
		while(true) {
			calculate();
			try {Thread.sleep(50);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}

	public void buyDollar(double value) {		
		balance += value;		
	}
		
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */

}
