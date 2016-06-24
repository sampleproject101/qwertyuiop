package com.chua.evergrocery.database.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chua.evergrocery.database.dao.CustomerOrderDAO;
import com.chua.evergrocery.database.entity.CustomerOrder;
import com.chua.evergrocery.objects.ObjectList;

@Repository
public class CustomerOrderDAOImpl 
		extends AbstractDAO<CustomerOrder, Long>
		implements CustomerOrderDAO {

	@Override
	public ObjectList<CustomerOrder> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey)
	{
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		
		if(StringUtils.isNotBlank(searchKey))
		{
			conjunction.add(Restrictions.disjunction()
					.add(Restrictions.ilike("name", searchKey, MatchMode.ANYWHERE)));
		}
		
		return findAllByCriterion(pageNumber, resultsPerPage, null, null, null, null, conjunction);
	}
	
	@Override
	public CustomerOrder findByName(String name) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.eq("name", name));
		
		return findUniqueResult(null, null, null, conjunction);
	}
}
