package com.mogan.sys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitSys
 */
public final class InitSys extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitSys() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		initSystemParameter();
		Properties p=System.getProperties();
		Iterator it=p.keySet().iterator();
		for (;it.hasNext();){
			String key=it.next().toString();
			System.out.println("[INFO] System.Properties "+key+" : "+p.getProperty(key));
		}
	}

	private void initSystemParameter() {
		File f = new File(this.getServletContext().getRealPath("/")
				+ "WEB-INF/sys.Property");
		FileInputStream fis;
		try {
			if (f.isFile()) {
				if (!f.getParentFile().isDirectory()) {
					f.getParentFile().mkdirs();
				}
			}
			fis = new FileInputStream(f);
			// 指定utf-8編碼
			BufferedReader br;
			br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
			Properties p = new Properties();
			p.load(br);
			Iterator it = p.keySet().iterator();
			for (; it.hasNext();) {
				String key = (String) it.next();
				this.getServletContext().setAttribute(key, p.get(key));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map tempMap = new HashMap();
		tempMap.put("fccc13447039e0ebf289e4227bc8e9e6", Boolean.TRUE);
		tempMap.put("26b782eb04abbd54efba0dcf854b158d", Boolean.TRUE);
		this.getServletContext().setAttribute("APP_ID", tempMap);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[INFO] InitSys called....");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[INFO] InitSys called....");
	}

}