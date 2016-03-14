package com.estsoft.network.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {
	public static void main(String[] args) {

		try {

			// InetAddress : internet 주소에 관한 내용
			// localhost : 현재 지금 program이 돌아가는 부분의 host
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();
			String hostname = inetAddress.getHostName();
			System.out.println(hostAddress);
			System.out.println(hostname); // print local hostname

			// tcp/ip에서 주소는 ipadd+port
			// server는 어떤 ip를 쓸 지 모르기 때문에 구현을 따로 해야한다. 내 ip만 쓴다는 보장이 없기 때문

			// byte[] addresses = inetAddress.getAddress(); // ->estsoft.com기준:
			// [54][187][215][124]
			// for(int i=0;i<addresses.length;i++){
			// int address = addresses[i]; // casting에서 error발생@!!! 맨앞의 1이
			// 부호비트역할을 해서 폭망
			// System.out.print(address);
			// if(i<addresses.length-1){
			// System.out.print(".");
			// }

			byte[] addresses = inetAddress.getAddress(); // ->estsoft.com기준:
															// [54][187][215][124]
			for (int i = 0; i < addresses.length; i++) {
				int address = addresses[i] & 0x000000ff; // 부호비트 문제 해결******check필요***
				//System.out.print(addresses[i]); 였을때 err난게
				System.out.print(address);// 해결됨
				if (i < addresses.length - 1) {
					System.out.print(".");
				}
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
