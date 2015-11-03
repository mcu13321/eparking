package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BaseDAO;
import com.util.CommonUtil;
import com.util.DBUtil;
import com.util.MD5;
import com.util.SearchResult;
import com.util.SessionUser;
import com.util.SessionService;
import com.util.ValidateLogin;


public class Front extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doPost(request,response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
    	
    	request.setCharacterEncoding("utf-8");

    	String target="front";
		//设置页面链接的Servlet
		request.setAttribute("targetServlet", target);			
				
		String action=request.getParameter("action");		
		request.setAttribute("action", action);
		
		if(action.equals("login"))	{    //登录
			login(request, response);
		}else if (action.equals("reg"))	{   //注册
			reg (request, response);
		}else if (action.equals("subscribe")) { //预定
			subscribe (request, response);
		}else if (action.equals("query"))  {//预定
			query (request, response);
		}else if (action.equals("query1"))  //预定2
			query1 (request, response);
    }
		
			// 登录
			public void login(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				
				
				String username = CommonUtil.getStringParameter(request, "username");
				String password = CommonUtil.getStringParameter(request, "password");
					

				String message = "";
				BaseDAO dao = new BaseDAO();
								
				String successurl= CommonUtil.getStringParameter(request, "tourl");
				if(successurl.equals("")){
					successurl="userinfo.jsp";	
				}	
				String failurl="login.jsp";							
				String password2=MD5.md5(password);
			
				Map<String,String> user = dao.getOne("*", "tb_user", " and username='"+username+"' and password='"+password2+"'");		
						
				if (user != null) {
			
					HttpSession session=request.getSession();
					SessionUser su=new SessionUser();
					su.setUserid(user.get("userid"));				
					su.setUsername(username);
					su.setRole("会员");		
					// 把信息放入Session
					session.setAttribute("user", su);										
					message = "登录成功";
					request.setAttribute("url", successurl);
					
				} else {
					message = "用户名或密码错误";
					request.setAttribute("url", failurl);
				}
				// 把信息放入页面
				request.setAttribute("message", message);
				request.getRequestDispatcher("userinfo.jsp").forward(request, response);				

				
			}
		
			// 会员注册
			public void reg(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
					     
					// 1、获取数据
					String username = CommonUtil.getStringParameter(request, "username");
					String password = CommonUtil.getStringParameter(request, "password");
					
					String realname= CommonUtil.getStringParameter(request, "realname");
					String mobile= CommonUtil.getStringParameter(request, "mobile");
					String certificate= CommonUtil.getStringParameter(request, "certificate");
					String carinfo= CommonUtil.getStringParameter(request, "carinfo");
					

				    String message = ""; 
					//成功URL
					String successurl="login.jsp";
					//失败URL
					String failurl="reg.jsp";					
					// 判断该用户名是否已经存在
					 
					BaseDAO dao = new BaseDAO();
					
					Map<String,String> u = dao.getOne("userid", "tb_user", " and username='"+username+"'");
					
					
					// 说明可以注册
					if (u == null) {
							
						  	// 调用DAO
							
							int userid = dao.save2("tb_user", "username='"+username+"',password='"+MD5.md5(password)+"',realname='"+realname+"',mobile='"+mobile+"',certificate='"+certificate+"',carinfo='"+carinfo+"'", 0);
							//System.out.println("After save2");
							if (userid > 0) {
								
								//将当前登录的用户信息写入session
								SessionUser user=new SessionUser();
								user.setRole("会员");
								user.setUserid(String.valueOf(userid));
								user.setUsername(username);

								message = "注册成功";
								HttpSession session = request.getSession();
								session.setAttribute("user", user);
								
								request.setAttribute("url", successurl);
								
								
							} else {
								message = "注册失败";
								request.setAttribute("url", failurl);
							}
			

					}else {
						message = "该用户已经存在";
						request.setAttribute("url", failurl);
					}
					// 把信息放入页面
					request.setAttribute("message", message);
					request.getRequestDispatcher(successurl).forward(request, response);	
	
					} 
			
			//查询车位
			public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				BaseDAO dao=new BaseDAO();			
				
									
				
			
					Map<String,String> record1=dao.getOne("*", "tb_car", " and carid='"+1+"'");
					Map<String,String> record2=dao.getOne("*", "tb_car", " and carid='"+2+"'");
					Map<String,String> record3=dao.getOne("*", "tb_car", " and carid='"+3+"'");
					Map<String,String> record4=dao.getOne("*", "tb_car", " and carid='"+4+"'");
					
					request.setAttribute("r1", record1);
					request.setAttribute("r2", record2);
					request.setAttribute("r3", record3);
					request.setAttribute("r4", record4);
				
				request.getRequestDispatcher("userinfo.jsp").forward(request, response); 	
		}
			
			public void query1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				BaseDAO dao=new BaseDAO();			
				
			
					Map<String,String> record1=dao.getOne("*", "tb_car", " and carid='"+1+"'");
					Map<String,String> record2=dao.getOne("*", "tb_car", " and carid='"+2+"'");
					Map<String,String> record3=dao.getOne("*", "tb_car", " and carid='"+3+"'");
					Map<String,String> record4=dao.getOne("*", "tb_car", " and carid='"+4+"'");
					
					request.setAttribute("r1", record1);
					request.setAttribute("r2", record2);
					request.setAttribute("r3", record3);
					request.setAttribute("r4", record4);
				
				request.getRequestDispatcher("subscribe.jsp").forward(request, response); 	
		}
			//预定车位
			public void subscribe(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				BaseDAO dao=new BaseDAO();	
				String value1 = CommonUtil.getStringParameter(request, "yudinga01");
				String value2 = CommonUtil.getStringParameter(request, "yudinga02");
				String value3 = CommonUtil.getStringParameter(request, "yudingb01");
				
				int id;
				
				if(value1.equals("7")){
					id=1;
				
				dao.save1("tb_car","status='"+value1+"'" ,id);
				}
				else if(value2.equals("8")){
					id=2;
				
				dao.save1("tb_car","status='"+value2+"'" ,id);
				}
				else if(value3.equals("9")){
					id=3;
				
				dao.save1("tb_car","status='"+value3+"'" ,id);
				}
				request.getRequestDispatcher("subscribe.jsp").forward(request, response); 		
		}
}
			

		
		