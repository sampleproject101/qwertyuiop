package com.chua.evergrocery.database.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chua.evergrocery.database.dao.CategoryDAO;
import com.chua.evergrocery.database.entity.Category;
import com.chua.evergrocery.objects.ObjectList;

@Repository
public class CategoryDAOImpl
		extends AbstractDAO<Category, Long>
		implements CategoryDAO {
	
	@Override
	public ObjectList<Category> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey)
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
}