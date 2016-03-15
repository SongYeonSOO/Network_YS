package com.estsoft.network.thread;


//thread 상속을 받지 않은 외부의 class를 thread 받도록 해보자!!!!
public class UpperCaseAlphabet {
	public void print() {
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.print(c);
		}
	}
}
