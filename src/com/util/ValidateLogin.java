package com.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ValidateLogin {
	

	    //获取登录用户所属用户id	    
	    public static String getLoginId(HttpServletRequest request) {
	    	HttpSession session = request.getSession();				
			SessionUser record=(SessionUser)session.getAttribute("user");
			if(record!=null){	
				return record.getUserid();
			}
			return null;		
        }
	    
	    
	    
        //获取登录用户所属用户名字	    
	    public static String getUsername(HttpServletRequest request) {
	    	HttpSession session = request.getSession();				
			SessionUser record=(SessionUser)session.getAttribute("user");
			if(record!=null){	
				return record.getUsername();
			}
			return null;	
			
        }
	    
	    //获取登录用户的角色
	    public static String getUserRole(HttpServletRequest request) {
			HttpSession session = request.getSession();				
			SessionUser record=(SessionUser)session.getAttribute("user");
			if(record!=null){	
				return record.getRole();
			}
			return null;				
        }
	    
}
