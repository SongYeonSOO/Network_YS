package com.estsoft.network.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {
	private static final String SERVER_IP = "192.168.1.186";
	private static final int SERVER_PORT = 5050;

	public static void main(String[] args) {
		Socket socket = null; // 초기화만 위에서 -> finally이용을 위함
		try {
			// 1. Socket 생성
			socket = new Socket();
			// 2. server연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));

			// 3. IOStream 받아오기
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();

			// 4. 읽고쓰기
			String data = "Hello World";
			outputStream.write(data.getBytes("UTF-8"));
			// ENCODED VALUE(UTF-8)는 안써도됨

			byte[] buffer = new byte[256]; // 256: 그냥 적당한 크기임
			int readByteCount = inputStream.read(buffer);

			// socket 연결이 끊어짐
			if (readByteCount <= -1) {
				System.out.println("[Client] closed by server");
				return;
			}
			data = new String(buffer, 0, readByteCount, "UTF-8");
			System.out.println("[Client] recieved :" + data);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// error난 경우 socket 닫는법
			//socket closed에 대한 try-catch
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
}
