package application;

import java.io.IOException;

public class PythonAPI implements Runnable{
	
	private Thread pythonAPIThread;
	
	public PythonAPI(){
		pythonAPIThread = new Thread(this);
		pythonAPIThread.start();
	}
	
	public void join(){
		try {
			pythonAPIThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		/*
		 * This run() running python file (main.py)
		 * for get Crypto coin values in real time.
		 */
		
		
        try {
    		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "python GetCoinValue/main.py");
    	    builder.redirectErrorStream(true);
			Process p = builder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */

}
