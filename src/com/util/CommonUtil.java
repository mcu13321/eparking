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
 * ��װ���õĲ���
 *
 */
public class CommonUtil {
	
	
	

   /**
    * ��õ�ǰҳ
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
		 * ȡ��ҳ��
		 */
		public static Integer getTotalPage(int totalCount,int pageSize){
	        if (totalCount % pageSize == 0) {
	            return totalCount/pageSize;
	        } else {
	        	return (totalCount/pageSize)+ 1;
	        }
		} 
		
		/**
		 * ���쵱ǰ���������URL
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
		
		//��ȡ���µ�һ��
		public static String getCurMonthFirstDay(String format){
			Calendar cal=Calendar.getInstance();//��ȡ��ǰ���� 
			cal.set(Calendar.DAY_OF_MONTH,1);//����Ϊ1��,��ǰ���ڼ�Ϊ���µ�һ�� 
			return new SimpleDateFormat(format).format(cal.getTime());
			
		}
		
		//��ȡ�������һ��
		public static String getCurMonthLastDay(String format){
			Calendar cal = Calendar.getInstance(); 
		    cal.setTime(new Date()); 
			cal.set(Calendar.DAY_OF_MONTH, 1); 
			cal.add(Calendar.MONTH, 1); 
			cal.add(Calendar.DATE, -1); 
			return new SimpleDateFormat(format).format(cal.getTime());
		}
		
		
		//��ȡ�ļ��ĺ�׺��
		public static String getFileSuffix(String fileName){
			return fileName.substring(fileName.lastIndexOf("."));		
		}
		
		
		//��ȡ������
		public static float getFloatParameter(HttpServletRequest request,String name){
			String temp=StringEscapeUtils.escapeSql(request.getParameter(name));
			if(temp==null || temp.equals("")){
				return 0;
			}else{
				//����ת��
				try {
					float temp2=Float.parseFloat(temp);
					return temp2;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				return 0;
						
			}
		}
		
		
		//��ȡ����
		public static int getIntParameter(HttpServletRequest request,String name){
			String temp=StringEscapeUtils.escapeSql(request.getParameter(name));
			if(temp==null || temp.equals("")){
				return 0;
			}else{
				//����ת��
				try {
					int temp2=Integer.parseInt(temp);
					return temp2;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				return 0;
			}			

		}
		
		
		
		//��ȡ�ַ���
		public static String getStringParameter(HttpServletRequest request,String name){
			String temp=StringEscapeUtils.escapeSql(request.getParameter(name));
			if(temp==null || temp.equals("")){
				return "";
			}else{
				request.setAttribute(name, temp);			
				return temp;
						
			}
		}
		
		//��ȡ�ַ������飬������ַ���
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
		
		
		//��ȡ�ַ������飬������ַ���
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
		
		//��ȡ�����ļ��Ĳ���
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
	     * �ַ���������
	     * 
	     */
		public static String filter(String input,boolean trimBlank) {
			if (input == null) {
				return null;
			}
			if (input.length() == 0) {
				return input;
			}		
			
			//ȥ���հ׷�
			if(trimBlank){
				input = input.replaceAll("\\s*", "");  
			}
			
			return input;
		}
		
		//ȥ�ַ����ı��
		public static String filter2(String input) {
			if (input == null) {
				return null;
			}
			if (input.length() == 0) {
				return input;
			}		
		
			return input.substring(input.lastIndexOf("]")+1);
		}
		
		
		
		
		//��ȡ��ǰ���ڵĺ�һ��
		public static String getNextDay(int days,String format){
			Date dNow = new Date();   //��ǰʱ��
			Date next = new Date();
			Calendar calendar = Calendar.getInstance(); //�õ�����
			calendar.setTime(dNow);//�ѵ�ǰʱ�丳������
			calendar.add(Calendar.DAY_OF_MONTH, days);  //����Ϊ����
			next = calendar.getTime();   //�õ�ǰһ���ʱ��

			SimpleDateFormat sdf=new SimpleDateFormat(format); //����ʱ���ʽ
			return sdf.format(next);    //��ʽ��ǰһ��
			

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
			   System.err.println("��ʽ����ȷ");
			}
			
			int result=c1.compareTo(c2);
			if(result==0){
				System.out.println("s1���s2");
			}else if(result<0){
				System.out.println("s1С��s2");				
			}else{
				System.out.println("s1����s2");
			}
			return result;
	
		}
		
		
		/**
	     * ��ȡcount�������
	     * @param count ���������
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
	    
		//��ȡ������
		public static synchronized long getOrderno() {
			return new Date().getTime();			
		}
		
		
		public static void main(String args[]){
			compareTime("2008-01-25 9:13:0","2008-01-25 9:12:11");
		}
		
		
		
		
		
		
	
}
