package tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomcat {
	private static int count;

	public static void main(String[] args) {

		ServerSocket ss = null;
		Socket socket = null;
		try {
			ss = new ServerSocket(8083);
			System.out.println("服务器已经开启等待客户端连接");
			while (true) {
				socket = ss.accept();
				count++;
				System.out.println("客户断第" + count + "次访问服务器");
				InputStream is = socket.getInputStream();
				Request request=new Request(is);
				OutputStream out = socket.getOutputStream();
				String responseHtml = "<html><head><title>repose msg</title></head><body><h1>happy midmoon festelval</h1></body><html>";
				out.write(responseHtml.getBytes());
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
