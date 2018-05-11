package com.lj.iproduct.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import com.lj.iproduct.domain.Product;
import com.lj.iproduct.utils.MyPage;

@Component
public class ProductDao extends BaseDao<Product> {

	public void save(Product instance) {
		log.debug("saving instance instance");
		try {
			getSession().save(instance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Product instance) {
		log.debug("deleting Product instance");
		try {
			getSession().delete(instance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Product findById(String id) {
		log.debug("getting Course instance with id: " + id);
		try {
			Product instance = getSession().get(Product.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Product> findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		Criteria c = dc.getExecutableCriteria(getSession());
		List list = c.list();
		return list;
	}
	
	public MyPage<Product> PagefindAll(int page,int pagesize){
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		return findPageByCriteria(dc, page, pagesize);
	}

}
