//package com.jpa.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import com.jpa.models.Drinks;
//import com.jpa.models.Food;
//
//public class DrinksDao {
//	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//	final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
//	final String USER = "Mpriyal";
//	final String PASS = "Priyaldbms94!";
//	static Connection conn = null;
//	static PreparedStatement statement = null;
//	static ResultSet resultset = null;
//
//	public static DrinksDao instance = null;
//
//	public static DrinksDao getInstance() {
//		if (instance == null) {
//			instance = new DrinksDao();
//		}
//		return instance;
//	}
//	private DrinksDao() {}
//
//	public int addDrinksForRestaurant(Drinks Drinks,int restId) {
//		int result = -1;
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String CreateMenu = "INSERT INTO Menu (name,price,description,Restaurant) VALUES (?,?,?,?)";
//			statement=conn.prepareStatement(CreateMenu);
//			statement.setString(1, Drinks.getName());
//			statement.setFloat(2, Drinks.getPrice());
//			statement.setString(3, Drinks.getDescription());
//			statement.setInt(4,restId);
//			result=statement.executeUpdate();
//
//			String createDrinks = "INSERT INTO Drinks (Liquor,Menu) VALUES (?,LAST_INSERT_ID())";
//			statement=conn.prepareStatement(createDrinks);
//			statement.setBoolean(1, Drinks.getLiquor());
//			result=statement.executeUpdate();
//
//			conn.close();
//
//		} catch (SQLException | ClassNotFoundException e) {
//
//			e.printStackTrace();
//		}finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	public List <Drinks> findDrinksByNameForRestaurant(String Name,int RestaurantId) {
//		List <Drinks> Drinkss =new ArrayList<>();
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String DrinksById = "SELECT * FROM \n" + 
//					"Drinks,Menu WHERE Drinks.Menu =Menu.id and Menu.name= ? and Menu.restaurant = ?";
//			statement= conn.prepareStatement(DrinksById);
//
//			statement.setString(1,Name);
//			statement.setInt(2,RestaurantId);
//
//			resultset = statement.executeQuery();
//			while(resultset.next()){
//				String name = resultset.getString("name");
//				Boolean Liquor = resultset.getBoolean("Liquor");
//				int price = resultset.getInt("price");
//				String description = resultset.getString("description");
//
//				Drinks Drinks = new Drinks(Liquor,name,price,description);
//				Drinkss.add(Drinks);
//			}
//			statement.close();
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return Drinkss;
//	}
//
//	public List <Drinks> findDrinksByTypeForRestaurant(Boolean Type,int RestaurantId) {
//		List <Drinks> Drinkss =new ArrayList<>();
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String DrinksById = "SELECT * FROM Drinks,Menu WHERE Drinks.Menu =Menu.id and Drinks.Liquor= ? and Menu.restaurant = ?";
//
//			statement= conn.prepareStatement(DrinksById);
//			statement.setBoolean(1,Type);
//			statement.setInt(2,RestaurantId);
//
//			resultset = statement.executeQuery();
//			while(resultset.next()){
//				String name = resultset.getString("name");
//				Boolean Liquor = resultset.getBoolean("Liquor");
//				int price = resultset.getInt("price");
//				String description = resultset.getString("description");
//				Drinks Drinks = new Drinks(Liquor,name,price,description);
//				Drinkss.add(Drinks);
//			}
//			statement.close();
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return Drinkss;
//	}
//
//	public List <Drinks> findAllDrinksByRestaurant(int RestaurantId) {
//		List <Drinks> Drinks =new ArrayList<>();
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String DrinksById = "SELECT * FROM \n" + 
//					"Drinks,Menu WHERE Drinks.Menu =Menu.id and Menu.restaurant = ?";
//			statement= conn.prepareStatement(DrinksById);
//			statement.setInt(1,RestaurantId);
//
//			resultset = statement.executeQuery();
//			while(resultset.next()){
//				String name = resultset.getString("name");
//				Boolean Liquor = resultset.getBoolean("Liquor");
//				int price = resultset.getInt("price");
//				String description = resultset.getString("description");
//
//				Drinks Drink = new Drinks(Liquor,name,price,description);
//				Drinks.add(Drink);
//			}
//			statement.close();
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return Drinks;
//	}
//
//	public int deleteDrinksForRestaurant(int id) {
//		int result = -1;
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String deleteMenu = "DELETE FROM Menu where id = ?";
//			statement=conn.prepareStatement(deleteMenu);
//			statement.setInt(1,id);
//			result=statement.executeUpdate();
//			conn.close();
//
//		} catch (SQLException | ClassNotFoundException e) {
//
//			e.printStackTrace();
//		}finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//	
//	public int updateDrink(int RestaurantId, Drinks drink){
//		int result = 0;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String DrinkUpdate = "UPDATE Menu,Drinks SET Menu.name =?, Menu.price =?, Menu.description =? ,\n" + 
//					"Drinks.Vegetarian =?  WHERE \n" + 
//					"Menu.id=Drinks.Menu\n" + 
//					"and Menu.Restaurant=?";
//			statement = conn.prepareStatement(DrinkUpdate);
//			statement.setString(1, drink.getName());
//			statement.setInt(2, drink.getPrice());
//			statement.setString(3, drink.getDescription());
//			statement.setBoolean(4, drink.getLiquor());
//			statement.setInt(5, RestaurantId);
//			result = statement.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//		}
//}
