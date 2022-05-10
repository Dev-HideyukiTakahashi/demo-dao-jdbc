package application;

import dao.DataSource;

public class Program {
	public static void main(String[] args) {
		DataSource.getConnection();
		DataSource.closeConnection();
		System.out.println("Abri e fecheu");
	}
}
