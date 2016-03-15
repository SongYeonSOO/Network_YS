package com.estsoft.network.thread;

public class DigitThread extends Thread {

	
	//동시에 실행할 코드를 run에 넣어준다 => EX에서 thread로 작동시켜보자!!
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {

			System.out.print(i);
			try {
				Thread.sleep(1000);// 얘가 자니깐 cpu를 다른 thread로 보낼것임
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
	}

}
