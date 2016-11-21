package tomcat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

	private OutputStream os = null;

	public Response(OutputStream os) {
		this.os = os;
	}

	public void writeContent(String content) throws IOException {
		if (content != null) {
			os.write(content.getBytes());
			os.flush();
			os.close();
		}
	}

	public void write(String filePath) throws IOException {
		StringBuilder sb = new StringBuilder();
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		try {
			reader = new FileReader(filePath);
			bufferedReader = new BufferedReader(reader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			reader.close();
			bufferedReader.close();
		}
		writeContent(sb.toString());
	}
	
	public void writeFile(String path) throws IOException{
		FileInputStream fis=new FileInputStream(path);
		byte[] buffer=new byte[512];
		int len=0;
		while((len=fis.read(buffer))!=-1){
			os.write(buffer, 0, len);
		}
		fis.close();
		os.flush();
		os.close();
		
	}
}
