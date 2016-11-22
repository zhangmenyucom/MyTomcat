package tomcat;

import java.io.IOException;

public class Servlet {
	/**
	 * @desc doHandle(处理请求)
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author xiaolu.zhang
	 * @date 2016年11月22日 上午11:52:29
	 */
	public void doHandle(Request request, Response response) throws IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("pass");
		System.out.println("userName=" + userName);
		System.out.println("password=" + password);
		response.writeContent("username:" + userName + ",password:" + password);
	}
}
