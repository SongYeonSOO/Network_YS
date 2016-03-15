package com.estsoft.network.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {
	public static void main(String[] args) {
		try {
			// naver같은 곳은 사용자가 많아서 여러 ip를 이용하는데, nameserver에서 여러 ip로 보내게된다
			// 그렇게 분산을 시키는데, 여러 호스트를 알려주기 때문에 inetaddress를 배열 형식으로 (여러개를 알려주니깐)
			// 사용한다.
//			InetAddress[] inetAddresses = InetAddress.getAllByName("www.naver.com");
//			for(int i=0;i<inetAddresses.length;i++){
//				System.out.println(inetAddresses[i]);
//				System.out.println(inetAddresses[i].getHostAddress());
//			}
			
			Scanner scanner = new Scanner(System.in);
			while(true){
			System.out.print(">>");
			String hostname = scanner.nextLine();
			if("exit".equals(hostname)){
				scanner.close();
				break;
			}
			InetAddress[] inetAddresses = InetAddress.getAllByName(hostname);
			for(int i=0;i<inetAddresses.length;i++){
				System.out.println(hostname+":"+inetAddresses[i].getHostAddress());

			}

			}		
			scanner.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

//이상한 주소를 치면 exception이 일어나게 된다. exception일어나면 catch만들어서 UnknownHostException을 구현한다.
//이 exception을 이용하기 위해서는 while문을 try-catch문 앞에놔야 잘 돌아가겠지??ㄷ

//www.estsoft.com을 치면 54.187.215.124가 나오는데, 브라우저에서 54.187.215.124라는 ip주소로도 홈페이지 이동이 가능하다
//Domain name자체가 ip보단 이름이 외우기 쉽다는걸 이용한 거니깐! (DNS!!!!!)
//Nameserver는 계층이 있다. -> main nameserver가 2002년에 해킹을 당했는데, 그러다보니 우리나라 전체가 인터넷 대란이 일어났던 일이 있다.
//