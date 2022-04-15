package com.usman.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usman.crypto.ciphers.VigenereCipher;
import com.usman.crypto.ciphers.VigenereException;

public class VigenereServlet extends HttpServlet {
	
	HttpServletRequest request;
	HttpServletResponse response;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.request=req;
		this.response=resp;
		
		String pt=req.getParameter("pt");
		String kc=req.getParameter("kc");
		String op=req.getParameter("op");
		VigenereCipher cipher=new VigenereCipher();
		
		String res="";
		try {
			res=(op.equalsIgnoreCase("e")?cipher.encrypt(pt, kc):cipher.decrypt(pt, kc));
			String key=cipher.getKey();
			String r=makeResult(pt,kc,res,key);
	     	resp.getWriter().print(r);

		}
		catch (VigenereException ve) {
			
			res=ve.getMessage();
			resp.sendError(500, ve.getMessage());
		}
		
		
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
	private String makeResult(String pt,String kc, String ct, String k) {
		
		StringBuffer buf=new StringBuffer();
		buf.append("<html>");
		buf.append("<head>");
		buf.append("<title>");
		buf.append("Vigenre Cipher");
		buf.append("</title>");
		buf.append("</head>");
		
		buf.append("<body>");
		
		buf.append("<h1>Results</h1><br><br> ");
		
		buf.append("<b>Input Text:</b> ");
		buf.append(pt);
		buf.append("<br>");
		
		buf.append("<b>Input Key Chars:</b> ");
		buf.append(kc);
		buf.append("<br><br><br>");

		buf.append("<b>Output Text:</b> ");
		buf.append(ct);
		buf.append("<br>");
		
		buf.append("<b>Vigenere Key:</b> ");
		buf.append(k);
		buf.append("<br><br>");
		buf.append("<a href=\"/index.jsp\">Try again</a>");
		
		buf.append("</body>");

		buf.append("</html>");
		
		return buf.toString();
		
		
	}
	
	
}
