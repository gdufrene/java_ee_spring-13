package fr.eservice.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormGreet2 extends FormGreet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		
		Writer out = resp.getWriter();
		BufferedReader in = Files.newBufferedReader( Paths.get("www","form.html"), Charset.forName("UTF-8") );
		int len = 0;
		char[] cbuf = new char[4096];
		while ( (len = in.read(cbuf)) > 0 ) out.write(cbuf, 0, len);
		in.close();
	}

}
