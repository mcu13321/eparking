package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import com.util.*;





//基础DAO，用于封装对数据库的常用操作
public class BaseDAO {
		
	 
	
	  //添加
	  public boolean add(String table,String where){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  boolean flag=false;
		  try {				 
			     cn=DBUtil.getConn();
			     String sql="insert into "+table+" set "+where;
			     System.out.println("添加SQL："+sql);
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
	  
	  //修改数据
	  public int save1(String table,String where,int id){
			
			int result=0;
			try {
				Connection cn=DBUtil.getConn();;
				Statement stmt=cn.createStatement();
				
				String sql="";
				ResultSet rs =null;
				
				sql="update "+table+" set "+where+"where "+"carid='"+id+"'";				
				result=stmt.executeUpdate(sql);
				
				
				System.out.println("保存SQL："+sql);
				
		
				DBUtil.close(cn, stmt, rs);
				
				return result;
			} catch (Exception e) {
				System.out.println("保存出错...");
				e.printStackTrace();
			}
			
			return result;
			
		}
	   //保存，如果是添加，则返回生成主键ID，如果是修改，则返回成功影响的记录数
		public int save2(String table,String where,int id){
			
			int result=0;
			try {
				Connection cn=DBUtil.getConn();;
				Statement stmt=cn.createStatement();
				
				String sql="";
				ResultSet rs =null;
				
				//添加
				if(id==0){
					sql="insert into "+table+" set "+where;	
					System.out.println ("this is the sql command: "+sql);
					
					stmt.executeUpdate(sql);
					
	    			rs = stmt.getGeneratedKeys();   
				    if(rs!=null && rs.next())  
				    {  
				         result=rs.getInt(1);//返回主键值  保存
				    } 
					
					
				}else{//修改
					sql="update "+table+" set "+where;				
					result=stmt.executeUpdate(sql);
				}
				
				System.out.println("保存SQL："+sql);//
				
		
				DBUtil.close(cn, stmt, rs);
				
				return result;
			} catch (Exception e) {
				System.out.println("保存出错...");
				e.printStackTrace();
			}
			
			return result;
			
		}
	
	
     //删除
	  public boolean del(String table,String where){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  boolean flag=false;
		  try {				 
			     cn=DBUtil.getConn();
			     String sql="delete from "+table+" where 1=1"+where;
			     System.out.println("删除SQL："+sql);
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
	  
	  //根据条件获取单条记录
	  public Map<String,String> getOne(String field,String table,String where){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		  try {				 
			     cn=DBUtil.getConn();
			     String sql="select "+field+" from "+table+" where 1=1"+where;
			     
				 pstmt=cn.prepareStatement(sql);	
				 System.out.println("查询单条SQL："+sql);
				 
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
	
	  //修改
	  public boolean edit(String table,String where){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  boolean flag=false;
		  try {				 
			     cn=DBUtil.getConn();
			     String sql="update "+table+" set "+where;
			     System.out.println("修改SQL："+sql);
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
	
	
	
	 //分页获取列表
	 public SearchResult<Map<String,String>> getList(String field,String table,String where,String orderby,int currentPage, int pageSize,boolean isNeedTotalCount){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		  try {				 
			     cn=DBUtil.getConn();
			     //得到分页开始索引
		    	 int pageStartIndex=(currentPage - 1) * pageSize;
		    	 String sql1="select "+field+" from "+table+" where 1=1"+where+" order by"+orderby+" limit "+pageStartIndex+","+pageSize;	 
		    	 System.out.println("列表SQL："+sql1);
		    	 pstmt=cn.prepareStatement(sql1);	
				 
				 rs=pstmt.executeQuery();				 
				 List<Map<String,String>> records=DBUtil.getHashMap(rs);
				 				 
				 SearchResult<Map<String,String>> sr = new SearchResult<Map<String,String>>();
		 		 sr.setRecords(records);
		 		 
		 		 //如果需要记录总数
		 		 if(isNeedTotalCount){
			 		 String sql2="select count(*) from "+table+" where 1=1"+where;	 
			 		 
			 		 //获取记录总数
			 		 PreparedStatement pstmt2=cn.prepareStatement(sql2);
			 		 ResultSet rs2=pstmt2.executeQuery();
			 		 int totalCount=0;
			 		 if(rs2.next()){
			 			 totalCount=rs2.getInt(1);
			 		 }
			 		 System.out.println("总记录数为："+totalCount);
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
	 
	 
	 
	 
	//分页获取列表
	 public SearchResult<Map<String,String>> getList2(String field,String table,String where,String groupby,String orderby,int currentPage, int pageSize,boolean isNeedTotalCount){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		  try {				 
			     cn=DBUtil.getConn();
			     //得到分页开始索引
		    	 int pageStartIndex=(currentPage - 1) * pageSize;
		    	 String sql1="select "+field+" from "+table+" where 1=1"+where+groupby+" order by"+orderby+" limit "+pageStartIndex+","+pageSize;	 
		    	 System.out.println("列表SQL："+sql1);
		    	 pstmt=cn.prepareStatement(sql1);	
				 
				 rs=pstmt.executeQuery();				 
				 List<Map<String,String>> records=DBUtil.getHashMap(rs);
				 				 
				 SearchResult<Map<String,String>> sr = new SearchResult<Map<String,String>>();
		 		 sr.setRecords(records);
		 		 
		 		 //如果需要记录总数
		 		 if(isNeedTotalCount){
			 		 String sql2="select count(*) from (select "+field+" from "+table+" where 1=1"+where+groupby+") a";	 
			 		System.out.println("查询记录数SQL："+sql2);
			 		 
			 		 //获取记录总数
			 		 PreparedStatement pstmt2=cn.prepareStatement(sql2);
			 		 ResultSet rs2=pstmt2.executeQuery();
			 		 int totalCount=0;
			 		 if(rs2.next()){
			 			 totalCount=rs2.getInt(1);
			 		 }
			 		 System.out.println("总记录数为："+totalCount);
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
	 
	 
	 
	 //分页获取列表
	 public SearchResult<Map<String,String>> getList3(String sql, int currentPage, int pageSize,boolean isNeedTotalCount){
		  Connection cn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		  try {				 
			     cn=DBUtil.getConn();
			     //得到分页开始索引
		    	 int pageStartIndex=(currentPage - 1) * pageSize;
		    	 String sql1=sql+" limit "+pageStartIndex+","+pageSize;	 
		    	 System.out.println("列表SQL："+sql1);
		    	 pstmt=cn.prepareStatement(sql1);	
				 
				 rs=pstmt.executeQuery();				 
				 List<Map<String,String>> records=DBUtil.getHashMap(rs);
				 				 
				 SearchResult<Map<String,String>> sr = new SearchResult<Map<String,String>>();
		 		 sr.setRecords(records);
		 		 
		 		 //如果需要记录总数
		 		 if(isNeedTotalCount){
			 		 String sql2="select count(*) from ("+sql+") p";	 
			 		 
			 		 //获取记录总数
			 		 PreparedStatement pstmt2=cn.prepareStatement(sql2);
			 		 ResultSet rs2=pstmt2.executeQuery();
			 		 int totalCount=0;
			 		 if(rs2.next()){
			 			 totalCount=rs2.getInt(1);
			 		 }
			 		 System.out.println("总记录数为："+totalCount);
			 		System.out.println("查询记录数SQL："+sql2);
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
