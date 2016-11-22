package tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Request {

	private String uri;

	private Map<String, String> paraMap = new HashMap<String, String>();

	public Request(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = inputStream.read(buffer);
		if (len > 0) {
			String msg = new String(buffer, 0, len);
			System.out.println(msg);

			/** 取uri路径 **/
			int start = msg.indexOf("GET") + 4;
			int end = msg.indexOf("HTTP/1.1") - 1;
			if (msg.indexOf("?") != -1 && msg.indexOf("?") < end) {
				uri = msg.substring(start, msg.indexOf("?"));
			} else {
				uri = msg.substring(start, end);
			}
			/** 获取参数 **/
			String parameterStr = "";
			if (msg.indexOf("?") != -1 && msg.indexOf("?") < end) {
				parameterStr = msg.substring(msg.indexOf("?") + 1, end);
			}
			if (msg.indexOf("POST") != -1) {
				start = msg.lastIndexOf("\n") + 1;
				parameterStr = msg.substring(start);
			}
			System.out.println("uri=" + uri);
			System.out.println("parameterStr=" + parameterStr);
			if (!parameterStr.equals("")) {
				String paraKVs[] = parameterStr.split("&");
				for (String kv : paraKVs) {
					String k_v[] = kv.split("=");
					paraMap.put(k_v[0], k_v[1]);
				}
			}

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

	public String getParameter(String parameterName) {
		return paraMap.get(parameterName);
	}

}
