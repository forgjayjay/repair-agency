package com.servlet_project.dbmanager;

/*
*       A class to handle all the SQL statements to not flood
*       the code with them, as well as providing an easier time
*       with understanding what the code is supposed to do
*/

public class constants {
    private constants() {}
	public static final String SETTINGS_FILE = "dbconnection.properties";
	public static final String VALIDATE_USER = "select * from users where login = ? and passwrd = ?";
	public static final String FIND_USER = "select * from users where login = ?";
	public static final String UPDATE_USER = "update users set ? where login = ?";

	public static final String INSERT_USER = "insert into users values (0, ? , ?, 'user')";
	public static final String INSERT_MNGR = "insert into users values (0, ? , ?, 'manager')";
}
