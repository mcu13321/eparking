package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import com.util.*;





//����DAO�����ڷ�װ�����ݿ�ĳ��ò���
public class BaseDAO {
		
	 
	
	  //���
	  public boolean add(String table,String where){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  boolean flag=false;
		  try {				 
			     cn=DBUtil.getConn();
			     String sql="insert into "+table+" set "+where;
			     System.out.println("���SQL��"+sql);
				 pstmt=cn.prepareStatement(sql);
				 
				 int temp=pstmt.executeUpdate();				 
				 
                 if(temp>0){
              	  flag=true;
                 }
		 			 		 
		 		 return flag;
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(cn, pstmt, null);		
			}
			return flag;	
		  
	  }
	  
	  //�޸�����
	  public int save1(String table,String where,int id){
			
			int result=0;
			try {
				Connection cn=DBUtil.getConn();;
				Statement stmt=cn.createStatement();
				
				String sql="";
				ResultSet rs =null;
				
				sql="update "+table+" set "+where+"where "+"carid='"+id+"'";				
				result=stmt.executeUpdate(sql);
				
				
				System.out.println("����SQL��"+sql);
				
		
				DBUtil.close(cn, stmt, rs);
				
				return result;
			} catch (Exception e) {
				System.out.println("�������...");
				e.printStackTrace();
			}
			
			return result;
			
		}
	   //���棬�������ӣ��򷵻���������ID��������޸ģ��򷵻سɹ�Ӱ��ļ�¼��
		public int save2(String table,String where,int id){
			
			int result=0;
			try {
				Connection cn=DBUtil.getConn();;
				Statement stmt=cn.createStatement();
				
				String sql="";
				ResultSet rs =null;
				
				//���
				if(id==0){
					sql="insert into "+table+" set "+where;	
					System.out.println ("this is the sql command: "+sql);
					
					stmt.executeUpdate(sql);
					
	    			rs = stmt.getGeneratedKeys();   
				    if(rs!=null && rs.next())  
				    {  
				         result=rs.getInt(1);//��������ֵ  ����
				    } 
					
					
				}else{//�޸�
					sql="update "+table+" set "+where;				
					result=stmt.executeUpdate(sql);
				}
				
				System.out.println("����SQL��"+sql);//
				
		
				DBUtil.close(cn, stmt, rs);
				
				return result;
			} catch (Exception e) {
				System.out.println("�������...");
				e.printStackTrace();
			}
			
			return result;
			
		}
	
	
     //ɾ��
	  public boolean del(String table,String where){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  boolean flag=false;
		  try {				 
			     cn=DBUtil.getConn();
			     String sql="delete from "+table+" where 1=1"+where;
			     System.out.println("ɾ��SQL��"+sql);
				 pstmt=cn.prepareStatement(sql);
				 
				 int temp=pstmt.executeUpdate();				 
				 
                 if(temp>0){
              	   flag=true;
                 }
		 			 		 
		 		 return flag;
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(cn, pstmt, null);		
			}
			return flag;	
		  
	  }
	  
	  //����������ȡ������¼
	  public Map<String,String> getOne(String field,String table,String where){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		  try {				 
			     cn=DBUtil.getConn();
			     String sql="select "+field+" from "+table+" where 1=1"+where;
			     
				 pstmt=cn.prepareStatement(sql);	
				 System.out.println("��ѯ����SQL��"+sql);
				 
				 rs=pstmt.executeQuery();
				
				 List<Map<String,String>> records=DBUtil.getHashMap(rs);
				 
		 		 if(rs!=null) rs.close();
		 		 if(pstmt!=null) pstmt.close();
		   	     if(cn!=null) cn.close();
		   	     
		   	     if(records.size()>0){
		   	    	return records.get(0);
		   	     }else{
		   	    	 return null;
		   	     }
		   	     
		 		 	
			} catch (Exception e) {
				 System.out.println("eeeee");
				e.printStackTrace();
			}finally{
				 
				DBUtil.close(cn, pstmt, rs);		
			}
			return null;
		  
	  }
	
	  //�޸�
	  public boolean edit(String table,String where){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  boolean flag=false;
		  try {				 
			     cn=DBUtil.getConn();
			     String sql="update "+table+" set "+where;
			     System.out.println("�޸�SQL��"+sql);
				 pstmt=cn.prepareStatement(sql);
				 
				 int temp=pstmt.executeUpdate();				 
				 
                 if(temp>0){
              	  flag=true;
                 }
		 			 		 
		 		 return flag;
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(cn, pstmt, null);		
			}
			return flag;	
		  
	  }
	
	
	
	 //��ҳ��ȡ�б�
	 public SearchResult<Map<String,String>> getList(String field,String table,String where,String orderby,int currentPage, int pageSize,boolean isNeedTotalCount){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		  try {				 
			     cn=DBUtil.getConn();
			     //�õ���ҳ��ʼ����
		    	 int pageStartIndex=(currentPage - 1) * pageSize;
		    	 String sql1="select "+field+" from "+table+" where 1=1"+where+" order by"+orderby+" limit "+pageStartIndex+","+pageSize;	 
		    	 System.out.println("�б�SQL��"+sql1);
		    	 pstmt=cn.prepareStatement(sql1);	
				 
				 rs=pstmt.executeQuery();				 
				 List<Map<String,String>> records=DBUtil.getHashMap(rs);
				 				 
				 SearchResult<Map<String,String>> sr = new SearchResult<Map<String,String>>();
		 		 sr.setRecords(records);
		 		 
		 		 //�����Ҫ��¼����
		 		 if(isNeedTotalCount){
			 		 String sql2="select count(*) from "+table+" where 1=1"+where;	 
			 		 
			 		 //��ȡ��¼����
			 		 PreparedStatement pstmt2=cn.prepareStatement(sql2);
			 		 ResultSet rs2=pstmt2.executeQuery();
			 		 int totalCount=0;
			 		 if(rs2.next()){
			 			 totalCount=rs2.getInt(1);
			 		 }
			 		 System.out.println("�ܼ�¼��Ϊ��"+totalCount);
			 		 PageBean pages=null;
			 		 if(totalCount!=0){
			 			pages=new PageBean(currentPage, pageSize, totalCount, CommonUtil.getTotalPage(totalCount, pageSize));
			 		 }else{
			 			pages=new PageBean(currentPage,pageSize,totalCount,0);
			 		 }
			 		 sr.setPageBean(pages);		
			 		 
			 		 if(rs2!=null) rs2.close();
			 		 if(pstmt2!=null) pstmt2.close();
		 		 }
		 		 

		         
		 		 if(rs!=null) rs.close();	 		
		 		 if(pstmt!=null) pstmt.close();	 		 
		   	     if(cn!=null) cn.close();
		 		 
		 		 return sr;
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(cn, pstmt, rs);		
			}
			return null;		  
	  }
	 
	 
	 
	 
	//��ҳ��ȡ�б�
	 public SearchResult<Map<String,String>> getList2(String field,String table,String where,String groupby,String orderby,int currentPage, int pageSize,boolean isNeedTotalCount){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		  try {				 
			     cn=DBUtil.getConn();
			     //�õ���ҳ��ʼ����
		    	 int pageStartIndex=(currentPage - 1) * pageSize;
		    	 String sql1="select "+field+" from "+table+" where 1=1"+where+groupby+" order by"+orderby+" limit "+pageStartIndex+","+pageSize;	 
		    	 System.out.println("�б�SQL��"+sql1);
		    	 pstmt=cn.prepareStatement(sql1);	
				 
				 rs=pstmt.executeQuery();				 
				 List<Map<String,String>> records=DBUtil.getHashMap(rs);
				 				 
				 SearchResult<Map<String,String>> sr = new SearchResult<Map<String,String>>();
		 		 sr.setRecords(records);
		 		 
		 		 //�����Ҫ��¼����
		 		 if(isNeedTotalCount){
			 		 String sql2="select count(*) from (select "+field+" from "+table+" where 1=1"+where+groupby+") a";	 
			 		System.out.println("��ѯ��¼��SQL��"+sql2);
			 		 
			 		 //��ȡ��¼����
			 		 PreparedStatement pstmt2=cn.prepareStatement(sql2);
			 		 ResultSet rs2=pstmt2.executeQuery();
			 		 int totalCount=0;
			 		 if(rs2.next()){
			 			 totalCount=rs2.getInt(1);
			 		 }
			 		 System.out.println("�ܼ�¼��Ϊ��"+totalCount);
			 		 PageBean pages=null;
			 		 if(totalCount!=0){
			 			pages=new PageBean(currentPage, pageSize, totalCount, CommonUtil.getTotalPage(totalCount, pageSize));
			 		 }else{
			 			pages=new PageBean(currentPage,pageSize,totalCount,0);
			 		 }
			 		 sr.setPageBean(pages);		
			 		 
			 		 if(rs2!=null) rs2.close();
			 		 if(pstmt2!=null) pstmt2.close();
		 		 }
		 		 

		         
		 		 if(rs!=null) rs.close();	 		
		 		 if(pstmt!=null) pstmt.close();	 		 
		   	     if(cn!=null) cn.close();
		 		 
		 		 return sr;
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(cn, pstmt, rs);		
			}
			return null;		  
	  }
	 
	 
	 
	 //��ҳ��ȡ�б�
	 public SearchResult<Map<String,String>> getList3(String sql, int currentPage, int pageSize,boolean isNeedTotalCount){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		  try {				 
			     cn=DBUtil.getConn();
			     //�õ���ҳ��ʼ����
		    	 int pageStartIndex=(currentPage - 1) * pageSize;
		    	 String sql1=sql+" limit "+pageStartIndex+","+pageSize;	 
		    	 System.out.println("�б�SQL��"+sql1);
		    	 pstmt=cn.prepareStatement(sql1);	
				 
				 rs=pstmt.executeQuery();				 
				 List<Map<String,String>> records=DBUtil.getHashMap(rs);
				 				 
				 SearchResult<Map<String,String>> sr = new SearchResult<Map<String,String>>();
		 		 sr.setRecords(records);
		 		 
		 		 //�����Ҫ��¼����
		 		 if(isNeedTotalCount){
			 		 String sql2="select count(*) from ("+sql+") p";	 
			 		 
			 		 //��ȡ��¼����
			 		 PreparedStatement pstmt2=cn.prepareStatement(sql2);
			 		 ResultSet rs2=pstmt2.executeQuery();
			 		 int totalCount=0;
			 		 if(rs2.next()){
			 			 totalCount=rs2.getInt(1);
			 		 }
			 		 System.out.println("�ܼ�¼��Ϊ��"+totalCount);
			 		System.out.println("��ѯ��¼��SQL��"+sql2);
			 		 PageBean pages=null;
			 		 if(totalCount!=0){
			 			pages=new PageBean(currentPage, pageSize, totalCount, CommonUtil.getTotalPage(totalCount, pageSize));
			 		 }else{
			 			pages=new PageBean(currentPage,pageSize,totalCount,0);
			 		 }
			 		 sr.setPageBean(pages);		
			 		 
			 		 if(rs2!=null) rs2.close();
			 		 if(pstmt2!=null) pstmt2.close();
		 		 }
		 		 

		         
		 		 if(rs!=null) rs.close();	 		
		 		 if(pstmt!=null) pstmt.close();	 		 
		   	     if(cn!=null) cn.close();
		 		 
		 		 return sr;
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(cn, pstmt, rs);		
			}
			return null;		  
	  }
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
