package comm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.StringTokenizer;

import helperMethods.Help;
import model.Student;

public class StudentC {

	private static InetAddress host;
	private static final int PORT = 1234;
	private static Connection con = null; 

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		System.out.println("chando");
		Scanner userChoice = new Scanner(System.in);

		try {
			host = InetAddress.getLocalHost();
			// System.out.println(host.toString());
		} catch (UnknownHostException e) {
			System.out.println("Host id not found!");
			System.exit(12);
		}
		(new Help()).Header();
		// input option

		// student input to make a pick
		System.out.println("\nStudent input Choice (1,2,3,4)?: ");
		int num = userChoice.nextInt();

		switch (num) {
		case 1:
			checkAppointment();
			break;
		case 2:
			makeAppointment();
			break;
		case 3:
			InputInfo();
			break;
		case 4:
			cancelAppointment();
			break;

		default:
			System.out.println("Unexpected value: " + num);

		}

	}

	private static void linkServer() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/projectdbjava?serverTimezone=UTC";
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, "root", "wasec1996");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage().toString());
		} catch (SQLException e) {
			System.out.println(e.getMessage().toString());
		}

	}

	private static void checkAppointment() {

		try {
			linkServer();

			Statement st = con.createStatement();
			// change query to fit database tables
			ResultSet rst = st.executeQuery("select * from users where name like'%a%'");

			rst.beforeFirst();
			ResultSetMetaData rmeta = (ResultSetMetaData) rst.getMetaData();
			int conNum = rmeta.getColumnCount();
			for (int i = 1; i <= conNum; i++) {
				System.out.println("\t" + rmeta.getColumnName(i));
				System.out.println("-------------");
			}

			while (rst.next()) {
				System.out.println(" " + rst.getString("name"));
			}
			System.out.println("Database Connection changdo");

		} catch (SQLException e) {

			System.err.println(e.getMessage().toString());

		}
	}

	public static void InputInfo() throws ClassNotFoundException, SQLException, IOException {
		String firstName, lastName, Address, department, Status;
		int age;

		Scanner sc = new Scanner(System.in);

		PreparedStatement pstmt = null;

		try {
			linkServer();
			con.setAutoCommit(false);

			Statement stmt = ((java.sql.Connection) con).createStatement();
			String psql = "insert into users(name) values(?)";
			pstmt = con.prepareStatement(psql);

			/*
			 * int id, String firstName, String lastName, int age, String address, String
			 * dept, String status
			 */

			System.out.print("\n Input Info (firstName,lastName, age,  Address,department, Status)>> ");
			firstName = sc.next();
			lastName = sc.next();
			age = sc.nextInt();
			Address = sc.next();
			department = sc.next();
			Status = sc.next();
			String stdString = (new Student(1, firstName, lastName, age, Address, department, Status)).toString();

			pstmt.setString(1, stdString);

			pstmt.executeUpdate();
			con.commit();
			System.out.println("Updates rows.");

			con.commit();

			// }
		} catch (Exception e) {

			System.out.println(e.getMessage().toString());

			if (con != null) {

				try {
					con.rollback();

					sc.close();
					con.close();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage().toString());
				}
			}

		}
	}

	public static void cancelAppointment() throws ClassNotFoundException, SQLException, IOException {

		String firstName, lastName, Address, department, Status;
		int age;

		Scanner sc = new Scanner(System.in);

		PreparedStatement pstmt = null;

		try {
			linkServer();
			con.setAutoCommit(false);

			Statement stmt = ((java.sql.Connection) con).createStatement();
			String psql = "DELETE FROM users WHERE name=?";
			pstmt = con.prepareStatement(psql);

			/*
			 * int id, String firstName, String lastName, int age, String address, String
			 * dept, String status
			 */

			System.out.print("\nDelete Info (firstName,lastName, age,  Address,department, Status)>> ");
			firstName = sc.next();
			lastName = sc.next();
			age = sc.nextInt();
			Address = sc.next();
			department = sc.next();
			Status = sc.next();
			String stdString = (new Student(1, firstName, lastName, age, Address, department, Status)).toString();
			System.out.println(stdString);
			pstmt.setString(1, stdString);

			pstmt.executeUpdate();
			System.out.println("Updates rows.");

		} catch (Exception e) {

			System.out.println(e.getMessage().toString());

			if (con != null) {

				try {

					sc.close();
					con.close();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage().toString());
				}
			}

		}
	}

	private static void makeAppointment() {

		Socket link = null;
		try {
			link = new Socket(host, PORT);
			Scanner input = new Scanner(link.getInputStream());
			PrintWriter out = new PrintWriter(link.getOutputStream(), true);

			Scanner userEntry = new Scanner(System.in);

			String message, response;

			do {
				System.out.println("=======debug Client===========");
				System.out.println("Client message: ");
				message = userEntry.nextLine();
				out.println(message + "\n");
				response = input.nextLine();
				System.out.println("\nServer Reply> " + response);

			} while (!message.equalsIgnoreCase("***CLOSE**"));

			// database

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("\nClosing connection...*");
				link.close();

			} catch (Exception e2) {

				System.out.println("Unable to disconnect...*");
				System.exit(23);
			}
		}

	}

}
