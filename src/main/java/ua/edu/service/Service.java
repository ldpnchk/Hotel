package ua.edu.service;

import ua.edu.dao.AbstractDaoFactory;
import ua.edu.dao.connection.DataSource;

public abstract class Service {
	
	protected AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
	protected DataSource dataSource = DataSource.getInstance();

}
