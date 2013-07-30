package se.hiq.hicollege.database;

import javax.sql.DataSource;

import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

import org.springframework.core.io.DefaultResourceLoader;

/**
 * Helper class for executing liquibase.
 *  
 * @author rikardwi
 **/
public class LiquibaseExecutor {

	private DataSource dataSource;
	private SpringLiquibase liquibase = new SpringLiquibase();
	
	/**
	 * Constructor.
	 * 
	 * @param dataSource Data source to use.
	 **/
	public LiquibaseExecutor(DataSource dataSource) {
		this.dataSource = dataSource;
 	}
	
	/**
	 * Runs liquibase with the suppled changeset.
	 * 
	 * @param changeSetLocation	Change Set to execute.
	 * 
	 * @throws LiquibaseException if an exeception occurred when executing the change set.
	 **/
	public void run(String changeSetLocation) throws LiquibaseException {
		System.setProperty(Liquibase.SHOULD_RUN_SYSTEM_PROPERTY, "true");
	
		liquibase.setDataSource(dataSource);
		liquibase.setResourceLoader(new DefaultResourceLoader());
		liquibase.setChangeLog(changeSetLocation);
		liquibase.afterPropertiesSet();
	}
	
	/**
	 * Runs liquibase with the suppled changeset.
	 * 
	 * @param changeSetLocation	Change Set to execute.
	 * @param contexts selected contexts in string separated by "," 
	 * @throws LiquibaseException if an exeception occurred when executing the change set.
	 **/
	public void run(String changeSetLocation, String contexts) throws LiquibaseException{
		liquibase.setContexts(contexts);
		run(changeSetLocation);
	}
}
