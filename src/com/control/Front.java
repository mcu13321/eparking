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
		//����ҳ�����ӵ�Servlet
		request.setAttribute("targetServlet", target);			
				
		String action=request.getParameter("action");		
		request.setAttribute("action", action);
		
		if(action.equals("login"))	{    //��¼
			login(request, response);
		}else if (action.equals("reg"))	{   //ע��
			reg (request, response);
		}else if (action.equals("subscribe")) { //Ԥ��
			subscribe (request, response);
		}else if (action.equals("query"))  {//Ԥ��
			query (request, response);
		}else if (action.equals("query1"))  //Ԥ��2
			query1 (request, response);
    }
		
			// ��¼
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
					su.setRole("��Ա");		
					// ����Ϣ����Session
					session.setAttribute("user", su);										
					message = "��¼�ɹ�";
					request.setAttribute("url", successurl);
					
				} else {
					message = "�û������������";
					request.setAttribute("url", failurl);
				}
				// ����Ϣ����ҳ��
				request.setAttribute("message", message);
				request.getRequestDispatcher("userinfo.jsp").forward(request, response);				

				
			}
		
			// ��Աע��
			public void reg(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
					     
					// 1����ȡ����
					String username = CommonUtil.getStringParameter(request, "username");
					String password = CommonUtil.getStringParameter(request, "password");
					
					String realname= CommonUtil.getStringParameter(request, "realname");
					String mobile= CommonUtil.getStringParameter(request, "mobile");
					String certificate= CommonUtil.getStringParameter(request, "certificate");
					String carinfo= CommonUtil.getStringParameter(request, "carinfo");
					

				    String message = ""; 
					//�ɹ�URL
					String successurl="login.jsp";
					//ʧ��URL
					String failurl="reg.jsp";					
					// �жϸ��û����Ƿ��Ѿ�����
					 
					BaseDAO dao = new BaseDAO();
					
					Map<String,String> u = dao.getOne("userid", "tb_user", " and username='"+username+"'");
					
					
					// ˵������ע��
					if (u == null) {
							
						  	// ����DAO
							
							int userid = dao.save2("tb_user", "username='"+username+"',password='"+MD5.md5(password)+"',realname='"+realname+"',mobile='"+mobile+"',certificate='"+certificate+"',carinfo='"+carinfo+"'", 0);
							//System.out.println("After save2");
							if (userid > 0) {
								
								//����ǰ��¼���û���Ϣд��session
								SessionUser user=new SessionUser();
								user.setRole("��Ա");
								user.setUserid(String.valueOf(userid));
								user.setUsername(username);

								message = "ע��ɹ�";
								HttpSession session = request.getSession();
								session.setAttribute("user", user);
								
								request.setAttribute("url", successurl);
								
								
							} else {
								message = "ע��ʧ��";
								request.setAttribute("url", failurl);
							}
			

					}else {
						message = "���û��Ѿ�����";
						request.setAttribute("url", failurl);
					}
					// ����Ϣ����ҳ��
					request.setAttribute("message", message);
					request.getRequestDispatcher(successurl).forward(request, response);	
	
					} 
			
			//��ѯ��λ
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
			//Ԥ����λ
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
			

		
		