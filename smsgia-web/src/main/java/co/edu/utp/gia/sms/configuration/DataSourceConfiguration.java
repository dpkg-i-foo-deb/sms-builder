package co.edu.utp.gia.sms.configuration;

import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;

@DataSourceDefinitions({
	
	@DataSourceDefinition(
			name = "java:app/smsgia-web/smsDS",
			className = "org.h2.jdbcx.JdbcDataSource",
			initialPoolSize = 10,
			minPoolSize = 10,
			maxPoolSize = 200,
//			url = "jdbc:h2:mem:test"
			url = "jdbc:h2:file:./sms"
			),
	@DataSourceDefinition(
	name = "java:app/smsgia-web/mysql",
	className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
//	className = "com.mysql.jdbc.Driver",
//	serverName="192.168.211.254",
	serverName="localhost",
	databaseName = "sms",
	portNumber=3306,
	user = "sms",
	password = "sms-12345"
		)


//	@DataSourceDefinition(name = "java:global/MyApp/myDS",
//	className = "org.apache.derby.jdbc.ClientDataSource",
//	portNumber = 1527,
//	serverName = "localhost",
//	databaseName = "testDB21",
//	user = "lance",
//	password = "secret",
//	properties = {"createDatabase=create"}),
//
//	@DataSourceDefinition(name = "java:global/MyApp/myDS2",
//	className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
//	portNumber = 3306,
//	serverName = "localhost",
//	databaseName = "dogDB",
//	user = "luckyDog",
//	password = "shhh",
//	properties = {"pedantic=true"})
	})
public class DataSourceConfiguration {

}
