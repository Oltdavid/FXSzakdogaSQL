package sport;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {

//	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//	final String URL = "jdbc:mysql://localhost/telefonkonyv?&characterEncoding=UTF-8";
//	final String USERNAME = "root";
//	final String PASSWORD = "";
    final String JDBC_DRIVER = "org.sqlite.JDBC";
	final String URL = "jdbc:sqlite:contacts.sqlite";
	final String USERNAME = "root";
	final String PASSWORD = "";

	// Létrehozzuk a kapcsolatot (hidat)
	Connection conn = null;
	Statement createStatement = null;
	DatabaseMetaData dbmd = null;

	public DB() {
		// Megpróbáljuk életre kelteni
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("A híd létrejött");
		} catch (SQLException ex) {
			System.out.println("Valami baj van a connection (híd) létrehozásakor.");
			System.out.println("" + ex);
		}

		// Ha életre kelt, csinálunk egy megpakolható teherautót..
		if (conn != null) {
			try {
				createStatement = conn.createStatement();
			} catch (SQLException ex) {
				System.out.println("Valami baj van van a createStatament (teherautó) létrehozásakor.");
				System.out.println("" + ex);
			}
		}

		// Megnézzük, hogy üres-e az adatbázis? Megnézzük, létezik-e az adott adattábla.
		try {
			dbmd = conn.getMetaData();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a DatabaseMetaData (adatbázis leírása) létrehozásakor..");
			System.out.println("" + ex);
		}

		try {
			ResultSet rs = dbmd.getTables(null, null, "%", null);
			if (!rs.next()) {
				String database_sql = "CREATE TABLE `contacts` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `lastname` TEXT NOT NULL, `firstname` TEXT NOT NULL, `email` TEXT NOT NULL, `anyjaneve` TEXT NOT NULL, `lakcim` TEXT NOT NULL, `tajszam` TEXT NOT NULL, `szido` TEXT NOT NULL, `telefon` TEXT NOT NULL )";

				createStatement.execute(database_sql);

			}
		} catch (SQLException ex) {
			System.out.println("Valami baj van az adattáblák létrehozásakor.");
			System.out.println("" + ex);
		}
	}

	public ArrayList<Person> getAllContacts() {
		String sql = "select * from contacts";
		ArrayList<Person> users = null;
		try {
			ResultSet rs = createStatement.executeQuery(sql);
			users = new ArrayList<>();

			while (rs.next()) {
				Person actualPerson = new Person(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"),
						rs.getString("email"), rs.getString("anyjaneve"), rs.getString("lakcim"),
						rs.getString("tajszam"), rs.getString("szido"), rs.getString("telefon"));
				users.add(actualPerson);
			}
		} catch (SQLException ex) {
			System.out.println("Valami baj van a userek kiolvasásakor");
			System.out.println("" + ex);
		}
		return users;
	}

	public void addContact(Person person) {
		try {
			String sql = "insert into contacts (lastname, firstname, email, anyjaneve, lakcim, tajszam, szido, telefon) values (?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, person.getLastName());
			preparedStatement.setString(2, person.getFirstName());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.setString(4, person.getAnyjaNeve());
			preparedStatement.setString(5, person.getLakcim());
			preparedStatement.setString(6, person.getTajszam());
                        preparedStatement.setString(7, person.getSzido());
                        preparedStatement.setString(8, person.getTelefon());
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact hozzáadásakor");
			System.out.println("" + ex);
		}
	}

	public void updateContact(Person person) {
		try {
			String sql = "update contacts set lastname = ?, firstname = ? , email = ?, anyjaneve = ?, lakcim = ? , tajszam = ?, szido = ?, telefon = ? where id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, person.getLastName());
			preparedStatement.setString(2, person.getFirstName());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.setString(4, person.getAnyjaNeve());
			preparedStatement.setString(5, person.getLakcim());
			preparedStatement.setString(6, person.getTajszam());
                        preparedStatement.setString(7, person.getSzido());
                        preparedStatement.setString(8, person.getTelefon());
			preparedStatement.setInt(9, Integer.parseInt(person.getId()));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact hozzáadásakor");
			System.out.println("" + ex);
		}
	}

	public void removeContact(Person person) {
		try {
			String sql = "delete from contacts where id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(person.getId()));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact törlésekor");
			System.out.println("" + ex);
		}
	}

}