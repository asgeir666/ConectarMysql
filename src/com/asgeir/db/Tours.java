package com.asgeir.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

public class Tours {
	public static void displayData(ResultSet rs, int nRows) throws SQLException {

		if (nRows == 0) {
			System.out.println("No se ha encontrado datos.");
		} else {
			System.out.println("Numero de Viajes: " + nRows);

			while (rs.next()) {

				StringBuffer buffer = new StringBuffer();

				buffer.append("Tour " + rs.getInt("tourId") + ": ");
				buffer.append(rs.getString("tourName"));

				double price = rs.getDouble("Price");
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				String formattedPrice = nf.format(price);
				buffer.append(" (" + formattedPrice + ")");
				System.out.println(buffer.toString());
			}
		}
	}
}
