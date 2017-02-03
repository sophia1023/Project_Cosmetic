package project.java.cosmetic;

public interface OracleQuary {
	
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER = "scott";
	public static final String PASSWD = "tiger";
	
	public static final String INSERT_COS = "insert into TABLE_COSMETIC values (?, ?, ?, ?)";
	public static final String INSERT_COSIMG = "insert into TABLE_IMAGE values (?, ?, ?)";
	public static final String SELECT_COSMETIC = "select * from TABLE_COSMETIC where COSMETIC like ?";
	public static final String SELECT_COMPANY = "select * from TABLE_COSMETIC where COMPANY like ?";
	public static final String SELECT_CATEGORY = "select * from TABLE_COSMETIC where CATEGORY like ?";
	public static final String SELECT_COSIMG = "select * from TABLE_IMAGE where COSMETIC like ?";
	public static final String SELECT_INGRE = "select * from TABLE_INGREDIENTS where INGREDIENTS = ?  AND INGREDIENTS like ?";
	public static final String COUNT_COSMETIC = "select count(*) from TABLE_COSMETIC";
	public static final String INSERT_TEST = "insert into TABLE_TEST values(?)";
}
