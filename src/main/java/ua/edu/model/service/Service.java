package ua.edu.model.service;

import ua.edu.model.dao.AbstractDaoFactory;
import ua.edu.model.dao.connection.DataSource;

public abstract class Service {
	
	protected AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
	protected DataSource dataSource = DataSource.getInstance();

}
