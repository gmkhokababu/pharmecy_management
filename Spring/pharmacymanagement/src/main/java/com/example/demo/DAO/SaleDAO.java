package com.example.demo.DAO;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import com.example.demo.Entity.Sale;

public class SaleDAO extends MySqlConnection {

	public void createSale(List<Sale> u) {
		int saveData = 0;
		if (u.get(0).getInvoice_no() == "") {
			String invoice = null;
			try {
				con = DriverManager.getConnection(url, user, pass);
				PreparedStatement psresult = con.prepareStatement("select invoice_no from sale");
				rs = psresult.executeQuery();
				while (rs.next()) {
					invoice = rs.getString(1);

				}
				invoice = String.valueOf(Integer.parseInt(invoice) + 1);
				System.out.println("invoice no = " + invoice);
				for (int i = 0; i < u.size(); i++) {
					PreparedStatement ps = con.prepareStatement("insert into sale values (?,?,?,?,?,?,?,?,?)");
					ps.setInt(1, u.get(i).getSaleId());
					ps.setInt(2, u.get(i).getStockId());
					ps.setInt(3, u.get(i).getQty());
					ps.setDouble(4, u.get(i).getRate());
					ps.setDouble(5, u.get(i).getTotal());
					ps.setDate(6, u.get(i).getSaleDate());
					ps.setInt(7, u.get(i).getCustomerId());
					ps.setString(8, invoice);
					ps.setString(9, u.get(i).getStatus());

					String ststus;
					int check = ps.executeUpdate();
					saveData = saveData + check;
				}
				System.out.println("Save Sale " + saveData);

				con.close();

			} catch (Exception e) {
				System.out.println("Sale Dao " + e);
			}
		} else {
			try {
			for (int i = 0; i < u.size(); i++) {
				PreparedStatement ps = con.prepareStatement("insert into sale values (?,?,?,?,?,?,?,?,?)");
				ps.setInt(1, u.get(i).getSaleId());
				ps.setInt(2, u.get(i).getStockId());
				ps.setInt(3, u.get(i).getQty());
				ps.setDouble(4, u.get(i).getRate());
				ps.setDouble(5, u.get(i).getTotal());
				ps.setDate(6, u.get(i).getSaleDate());
				ps.setInt(7, u.get(i).getCustomerId());
				ps.setString(8, u.get(i).getInvoice_no());
				ps.setString(9, u.get(i).getStatus());

				String ststus;
				int check = ps.executeUpdate();
				saveData = saveData + check;
			}
			}catch(Exception e) {
				System.out.println("Sale Dao " + e);
			}
		}
	}

}
