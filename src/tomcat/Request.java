package tomcat;

import java.io.IOException;
import java.io.InputStream;

public class Request {

	private String uri;

	public Request(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = inputStream.read(buffer);
		if (len > 0) {
			String msg = new String(buffer, 0, len);
			uri = msg.substring(msg.indexOf("/"), msg.indexOf("HTTP/1.1") - 1);
            System.out.println("+++++"+uri+"+++++");
		} else {
			System.out.println("invalid request");
		}
	}
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
