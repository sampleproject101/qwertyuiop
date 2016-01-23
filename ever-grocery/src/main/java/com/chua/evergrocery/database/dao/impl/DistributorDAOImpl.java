package com.chua.evergrocery.database.dao.impl;

import org.springframework.stereotype.Repository;

import com.chua.evergrocery.database.dao.DistributorDAO;
import com.chua.evergrocery.database.entity.Distributor;

@Repository
public class DistributorDAOImpl
		extends AbstractDAO<Distributor, Long>
		implements DistributorDAO {

}
