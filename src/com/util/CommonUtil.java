package com.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;


/**
 * 封装常用的操作
 *
 */
public class CommonUtil {
	
	
	

   /**
    * 获得当前页
    */
	public static Integer getPageNo(HttpServletRequest request) {
		Integer pageNo=1;
		if (null!= request.getParameter("pageNo") && !"".equals(request.getParameter("pageNo"))) {
			try {
				pageNo = Integer.valueOf(request.getParameter("pageNo"));
			} catch (NumberFormatException e) {
				pageNo=1;
			}
		}
		return pageNo;
	}
	
		/**
		 * 取总页数
		 */
		public static Integer getTotalPage(int totalCount,int pageSize){
	        if (totalCount % pageSize == 0) {
	            return totalCount/pageSize;
	        } else {
	        	return (totalCount/pageSize)+ 1;
	        }
		} 
		
		/**
		 * 构造当前请求的完整URL
		 */
		public static String getUrl(HttpServletRequest request){
		     
		      String url=request.getScheme()+"://";   
		      url+=request.getHeader("host");   
		      url+=request.getRequestURI();   
		      if(request.getQueryString()!=null)   
		            url+="?"+request.getQueryString(); 
		      
		      return url;
		} 
		
		public static String getNow(String format){
			return new SimpleDateFormat(format).format(new Date());
		}
		
		//获取当月第一天
		public static String getCurMonthFirstDay(String format){
			Calendar cal=Calendar.getInstance();//获取当前日期 
			cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			return new SimpleDateFormat(format).format(cal.getTime());
			
		}
		
		//获取当月最后一天
		public static String getCurMonthLastDay(String format){
			Calendar cal = Calendar.getInstance(); 
		    cal.setTime(new Date()); 
			cal.set(Calendar.DAY_OF_MONTH, 1); 
			cal.add(Calendar.MONTH, 1); 
			cal.add(Calendar.DATE, -1); 
			return new SimpleDateFormat(format).format(cal.getTime());
		}
		
		
		//获取文件的后缀名
		public static String getFileSuffix(String fileName){
			return fileName.substring(fileName.lastIndexOf("."));		
		}
		
		
		//获取浮点数
		public static float getFloatParameter(HttpServletRequest request,String name){
			String temp=StringEscapeUtils.escapeSql(request.getParameter(name));
			if(temp==null || temp.equals("")){
				return 0;
			}else{
				//进行转型
				try {
					float temp2=Float.parseFloat(temp);
					return temp2;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				return 0;
						
			}
		}
		
		
		//获取整数
		public static int getIntParameter(HttpServletRequest request,String name){
			String temp=StringEscapeUtils.escapeSql(request.getParameter(name));
			if(temp==null || temp.equals("")){
				return 0;
			}else{
				//进行转型
				try {
					int temp2=Integer.parseInt(temp);
					return temp2;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				return 0;
			}			

		}
		
		
		
		//获取字符串
		public static String getStringParameter(HttpServletRequest request,String name){
			String temp=StringEscapeUtils.escapeSql(request.getParameter(name));
			if(temp==null || temp.equals("")){
				return "";
			}else{
				request.setAttribute(name, temp);			
				return temp;
						
			}
		}
		
		//获取字符串数组，并组成字符串
		public static String getStringParameters(HttpServletRequest request,String name){
			String temp[]=request.getParameterValues(name);
			
			String result="";
			
			if(temp!=null || !temp.equals("")){
				
				for(int i=0;i<temp.length;i++){
					
					String aa=StringEscapeUtils.escapeSql(temp[i]);
					if(i==temp.length-1){						
						result+=aa;						
					}else
					{
						result+=aa+"|";						
					}	
				}
				
				request.setAttribute(name, result);				
				
			}
			return result;
			
			
			
		}
		
		
		//获取字符串数组，并组成字符串
		public static String getStringParameters2(HttpServletRequest request,String name){
			String temp[]=request.getParameterValues(name);
			
			String result="";
			
			if(temp!=null || !temp.equals("")){
				
				for(int i=0;i<temp.length;i++){
					
					String aa=StringEscapeUtils.escapeSql(temp[i]);
					if(i==temp.length-1){						
						result+=aa;						
					}else
					{
						result+=aa+",";						
					}	
				}
				
				request.setAttribute(name, result);				
				
			}
			return result;
			
			
			
		}
		
		//读取配置文件的参数
		public static String getConfig(String file,String ParaName) {
			Properties prop = new Properties();
			try {
														
				InputStream is = new BufferedInputStream (new FileInputStream(file));
				prop.load(is);
				if (is != null)
					is.close();
			} catch (Exception e) {
				System.out.println("there is error to read config file...");
				e.printStackTrace();
			}
			return prop.getProperty(ParaName);
		}
		

	    /**
	     * 字符串处理函数
	     * 
	     */
		public static String filter(String input,boolean trimBlank) {
			if (input == null) {
				return null;
			}
			if (input.length() == 0) {
				return input;
			}		
			
			//去掉空白符
			if(trimBlank){
				input = input.replaceAll("\\s*", "");  
			}
			
			return input;
		}
		
		//去字符串的编号
		public static String filter2(String input) {
			if (input == null) {
				return null;
			}
			if (input.length() == 0) {
				return input;
			}		
		
			return input.substring(input.lastIndexOf("]")+1);
		}
		
		
		
		
		//获取当前日期的后一天
		public static String getNextDay(int days,String format){
			Date dNow = new Date();   //当前时间
			Date next = new Date();
			Calendar calendar = Calendar.getInstance(); //得到日历
			calendar.setTime(dNow);//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, days);  //设置为后几天
			next = calendar.getTime();   //得到前一天的时间

			SimpleDateFormat sdf=new SimpleDateFormat(format); //设置时间格式
			return sdf.format(next);    //格式化前一天
			

		}
		
		
		
		public static int compareTime(String s1,String s2){
			//String s1="2008-01-25 09:12:09";
			//String s2="2008-01-29 09:12:11";
			java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Calendar c1=java.util.Calendar.getInstance();
			java.util.Calendar c2=java.util.Calendar.getInstance();
			try{
			   c1.setTime(df.parse(s1));
			   c2.setTime(df.parse(s2));
			}catch(java.text.ParseException e){
			   System.err.println("格式不正确");
			}
			
			int result=c1.compareTo(c2);
			if(result==0){
				System.out.println("s1相等s2");
			}else if(result<0){
				System.out.println("s1小于s2");				
			}else{
				System.out.println("s1大于s2");
			}
			return result;
	
		}
		
		
		/**
	     * 获取count个随机数
	     * @param count 随机数个数
	     * @return
	     */
	    public static String randnum(int count){
	        StringBuffer sb = new StringBuffer();
	        //String str = "123456789";
	        String str = "12356789";
	        Random r = new Random();
	        for(int i=0;i<count;i++){
	            int num = r.nextInt(str.length());
	            sb.append(str.charAt(num));
	            str = str.replace((str.charAt(num)+""), "");
	        }
	        return sb.toString();
	    }
	    
		//获取订单号
		public static synchronized long getOrderno() {
			return new Date().getTime();			
		}
		
		
		public static void main(String args[]){
			compareTime("2008-01-25 9:13:0","2008-01-25 9:12:11");
		}
		
		
		
		
		
		
	
}
