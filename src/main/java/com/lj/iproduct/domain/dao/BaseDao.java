package com.lj.iproduct.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.iproduct.utils.MyPage;

public class BaseDao<T> {
	protected final Logger log = LoggerFactory.getLogger( getClass() );
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession(){
		return entityManager.unwrap(Session.class);
	}
	
	public MyPage<T> findPageByCriteria(final DetachedCriteria dc,int page,int pageSize){
		Criteria criteria = dc.getExecutableCriteria(getSession());
		Object object = criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalcount = 0;
		try {
			totalcount = (Long) object;
		} catch (Exception e) {
		}
		MyPage<T> ps = new MyPage<T>((int) totalcount, pageSize, page);
		criteria.setProjection(null);
		List<T> items = criteria.setFirstResult(ps.getStartindex()).setMaxResults(pageSize).list();
		ps.setItems(items);
		return ps;
	}
	
}
