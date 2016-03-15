package com.estsoft.network.thread;

public class ThreadEX01 {

	public static void main(String[] args) {
		
		
		Thread thread01 = new DigitThread(); // thread 객체 생성
		Thread thread02 = new AlphabetThread();
		Thread thread03 = new AlphabetThread();
		
		//ehdtldp tlwkrgkrl
		thread01.start(); //thread 돌리자!!!
		thread02.start();
		thread03.start();
		
		
	}

}
