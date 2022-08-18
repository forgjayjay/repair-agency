package com.servlet_project.dbmanager;

/*
*       A class to handle all the SQL statements and some other constants 
*       to not flood the code with them, as well as providing an easier time
*       with understanding what the code is supposed to do
*/

public class constants {
    private constants() {}
	public static final String PAYMENT = "dbconnection.properties";


	public static final String SETTINGS_FILE = "dbconnection.properties";
	public static final String VALIDATE_USER = "select * from users where login = ? and passwrd = ?";
	public static final String FIND_USER = "select * from users where login = ?";
	public static final String UPDATE_USER = "update users set acc_type = ? where login = ?";
	public static final String UPDATE_ORDER_STATUS = "update orders set order_status = ? where id = ?";


	public static final String INSERT_USER = "insert into users values (0, ? , ?, 'user')";
	public static final String INSERT_MNGR = "insert into users values (0, ? , ?, 'manager')";
	public static final String INSERT_CRFTSMN = "insert into users values (0, ? , ?, 'craftsman')";
	public static final String INSERT_INTO_CRFTSMN = "insert into craftsmen values (?, ?, 0)";
	public static final String INSERT_NEW_ORDER = "insert into orders values (0, ?, 1, ?, ?, 0)";
	
	public static final String SHOW_ALL_ORDERS = "select * from orders where user_id = ?";
	public static final String SHOW_ORDER = "select * from orders where id = ?";

	public static final String SHOW_ORDER_CRFTSMN = "select * from orders where craftsman_id = ?";
	public static final String SHOW_NOT_PAID_ORDER = "select * from orders where user_id = "
	+"? and payment_status = '"	+ constants.UNPAID_STATUS+"'";

	public static final String PAID_STATUS = "payment successful";
	public static final String UNPAID_STATUS = "waiting payment";
	public static final String ORDER_STATUS_ACCEPT = "order accepted";
	public static final String ORDER_STATUS_WORKING = "order in process";
	public static final String ORDER_STATUS_DONE = "order completed";
	

}
