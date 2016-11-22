package tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomcat {
	private static int count;

	public static void main(String[] args) throws IOException {

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
				Request request = new Request(is);
				OutputStream out = socket.getOutputStream();
				Response response = new Response(out);
				Servlet servlet = new Servlet();
				servlet.doHandle(request, response);
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// ss.close();
		}
	}

	public static boolean isStaticResource(String path) {
		String[] suffixs = { ".html", ".css", ".js", ".jpg" };
		for (int i = 0; i < suffixs.length; i++) {
			if (path.indexOf(suffixs[i]) != -1) {
				return true;
			}
		}
		return false;
	}
}
