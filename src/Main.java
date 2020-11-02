import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.asgeir.db.DBType;
import com.asgeir.db.DBUtil;
import com.asgeir.db.Tours;

import util.InputHelper;

public class Main {

	private static final String SQL = "{call GetToursWithCountByPrice(? , ?)}";

	public static void main(String[] args) throws Exception {
		
		double maxPrice;
		
		try {
			maxPrice = InputHelper.getDoubleInput("Ingresar Precio Maximo: ");
		} catch (NumberFormatException e) {
			System.err.println("Error: Numero Invalido.");
			return;
		}
		ResultSet rs = null;

		try (Connection conn = DBUtil.getConnection(DBType.MYSQL);
				CallableStatement cstmt = 
						conn.prepareCall(SQL,
								ResultSet.TYPE_SCROLL_INSENSITIVE, 
								ResultSet.CONCUR_READ_ONLY);
				) {
			cstmt.setDouble(1, maxPrice);
			cstmt.registerOutParameter("total", Types.INTEGER);			
			rs = cstmt.executeQuery();
			
			int nRows = cstmt.getInt("total");
			Tours.displayData(rs, nRows);

		} catch (SQLException e) {
			DBUtil.processException(e);			
		}
		finally {
			if(rs != null) rs.close();
		}
	}
}
