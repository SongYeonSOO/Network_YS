package com.estsoft.network.thread;

public class AlphabetThread extends Thread{

	@Override
	public void run() {
		for (char c = 'a'; c <= 'z'; c++) {

			System.out.print(c);

			// sleep이 없으면 code상에서 알아서 sleep. 이 코드를 통해서 강제 control
			try {
				Thread.sleep(1000);// 얘가 자니깐 cpu를 다른 thread로 보낼것임  -> 직접 흐름을 잡음
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

}
