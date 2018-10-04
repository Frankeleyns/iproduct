package com.lj.iproduct.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class MyPage<T> implements Serializable{

	private int  pagesize = 5;  //每页数量
	private int page;  //当前页
	private int totalcount;  //总共对象的数量
	private int pagecount;  //总页数
	private int startindex = 0;  //起始数据查询
	private List<T> items = new ArrayList<T>();  //装有查出对象的集合
	private List<Integer> pages = new ArrayList<Integer>();  //相近的5个页码
	private int pre;  //上一页
	private int next;  //下一页

	   
	 //用来计算总页数
	   public int getPageCount(Integer totalcount,Integer pagesize){
		   int pagecount = (int) Math.ceil(totalcount.doubleValue()/pagesize.doubleValue());
		   return pagecount;
	   }
	
	   public MyPage(int totalcount,int pagesize,int page){
		   packagePage(totalcount, pagesize, page);
	   }
	
	/**
	 * 封装（打包）分页对象
	 * @param totalcount
	 * @param pagesize
	 * @param page
	 */
	private void packagePage(int totalcount, int pagesize, int page) {
		this.totalcount = totalcount;
		   if(this.totalcount < 0){
			   this.totalcount = 0;
		   }
		   
		   this.pagesize = pagesize;
		   if(this.pagesize < 0){
			   this.pagesize = 5;
		   }
		   
		   this.page = page;
		   if(this.page < 0){
			   this.page = 1;
		   }

		   this.startindex = (page - 1) * pagesize;
		   
		   this.pagecount = getPageCount(this.totalcount,this.pagesize);

		   
		   this.next = this.page + 1;
		   if(this.next > this.pagecount){
			   this.next = this.pagecount;
		   }
		   
		   this.pre = this.page - 1;
		   if(this.pre < 1){
			   this.pre = 1;
		   }
		   
		    // 显示5个相邻的页码
			int first = 1;
			int end = this.page + 2;
			if (this.page - 2 > 0) {
				first = this.page - 2;
			}
			if (end > this.pagecount) {
				end = this.pagecount;
			}
			if ((end - first) < 4 && (first + 4) <= this.pagecount) {// 后不足五个页码的补齐
				end = first + 4;
			}
			if ((end - first) < 4 && (end - 4) >= 1) {// 前不足五个页码的补齐
				first = end - 4;
			}

			int fornum = end - first + 1;
			for (int i = 0; i < fornum; i++) {
				this.pages.add(first);// 存入页码
				first++;
			}
	}
	
}
