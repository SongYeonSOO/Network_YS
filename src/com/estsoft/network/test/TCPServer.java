package com.estsoft.network.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static final int PORT = 5050; // 2.의 포트지정에 사용하기 위한 PORT번호

	public static void main(String[] args) {
		try {
			// 1. server socket 생성
			ServerSocket serverSocket = new ServerSocket();

			// 2. binding ( server의 ip주소+port# +socket binding )
			// ip address 구함
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhostAddress = inetAddress.getHostAddress();

			// port 지정 -> 몇번포트를 열지는 개발자가 지정한다
			// bind에 setting
			serverSocket.bind(new InetSocketAddress(localhostAddress, PORT));
			// **** InetSocetAddress(ip주소,port#): socket에 들어가는 address(ip+port)
			// **** inetAddress; 일반 ipaddress
			System.out.println("[server] binding " + localhostAddress + ":" + PORT);

			// 3.accept ; 연결요청 기다리기
			// 4. 연결성공
			Socket socket = serverSocket.accept(); // accept는 socket을 return함
			InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress(); // remoteAddress에 접근하려고 만듬
			String remoteHostAddress = remoteAddress.getAddress().getHostAddress(); 
			//remoteaddress에서 ipaddress를 다룰건데 그중에서 hostaddress를 받음
			int remoteHostPort = remoteAddress.getPort(); // remoteaddress의 port#
			System.out.println("[서버연결됨]");
			System.out.println("remote address: " + remoteHostAddress + "remote port:" + remoteHostPort);

			// 5. IOStream 받아오기
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();

			// 6. data읽기
			// 기본적으로 byte밖에 사용불가능 -> character로는 나중에 보조스트림을 이용하는 방식으로 진행한다.
			
			byte[] buffer = new byte[256];		
			// [CHECK]
			// echo라는 class를 만들땐 socket이 직접 던져주는 byte를 inputstream reader를 가지고  character로 만들어서 
			// buffered reader를 달아서 readLine()을 이용해 개행될때마다 이용하는 방식으로 진행할 예정이다
			
			
			int readByteCount = inputStream.read(buffer);							
			//result: xshell에서 현재 data input을 위해 socket을 blocking중이다
			// Hello World 입력했더니  BYTE받고 연결은 종료, java console에 Hello World 찍힘
			
			
			// 상대편 socket이 연결을 종료한 경우
			if (readByteCount < -1) {
				System.out.println("[server] closed by client");
			}

			String data = new String(buffer,0,readByteCount,"utf-8"); //utf-8은 생략가능
			System.out.println("[server] received: "+data);
			
			
			//7. data쓰기

			//outputStream.write(buffer); 
			// 문제발생 : 현재 버퍼크기 256에 들어있는 HelloWorld만 쓰고싶은건데, 남은 246개 byte를 쓰기싫은데 안찍히게 하려면?(내가 write한거만 받도록)
			outputStream.write(data.getBytes("UTF-8")); //getBytes : data라는 STRING을 char가 아닌 byte로 꺼낸다
			//result : xshell에 Hello World라 치면 바로 밑에 Hello World라고 똑같이 화면에 나온다
			
			socket.close(); // exception이 일어나면 socket closed가 안될수도 있다 -> finally이용
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			//exception을 만들었을 때 socket을 closed 하기 위해서는 serversocket/socket을 try block 바깥에 만들어야한다.(선생님코드를 참고하자)
			//8.data socket 닫기
			// [TIP] socket을 닫아버리면 input/output stream이 자동으로 닫히니깐 따로 닫아줄 필요없다.
			//try{
//			if(socket != null && socket.isClosed() == false){
//				
//				socket.closed();
//			}
//			if(serverSocket!= null && serverSocket.isClosed() == false){
//				serverSocket.closed();
//			}
//		}catch(IOExceptionex ){ex.printStackTrace();}
}

}
}

//두 코드가 동시에 돌아야 계속 CLIENT를 받을 수 있다(지속적인 ACCEPT) -> ACCEPT하는 THREAD, DATA통신하는 THREAD 등이 필요하다 !!!!!