package se.hiq.hicollege.changelog;

import liquibase.exception.LiquibaseException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import se.hiq.hicollege.database.LiquibaseExecutor;

public class LiquibaseChangelogIT {
	private LiquibaseExecutor liquibaseExecutor;
	private static final String CHANGESETLOCATION = "classpath:se/hiq/hicollege/changelog/db.changelog-master.xml";
	private static final String LIQUIBASE_SEPARATOR = ",";
	private static final String LIQUIBASE_MODEL = "model";
	private static final String LIQUIBASE_DATA = "data";
	
	@Before
	public void setUp() throws LiquibaseException{
		liquibaseExecutor = new LiquibaseExecutor(new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.HSQL)
        .build());
		}
	
	@Test
	public void LoadLiquibaseContextModel() throws LiquibaseException{
		liquibaseExecutor.run(CHANGESETLOCATION,LIQUIBASE_MODEL);
	}
	
	@Test
	public void LoadLiquibaseContextModelAndData() throws LiquibaseException{
		liquibaseExecutor.run(CHANGESETLOCATION,LIQUIBASE_MODEL+LIQUIBASE_SEPARATOR+LIQUIBASE_DATA);
	}
	
}

