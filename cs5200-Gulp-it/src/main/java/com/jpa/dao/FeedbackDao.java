package com.jpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.jpa.models.Feedback;

/**
 * 
 * @author amanrayat
 *
 */

public class FeedbackDao {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	final String USER = "Mpriyal";
	final String PASS = "Priyaldbms94!";
	static Connection conn = null;
	static PreparedStatement statement = null;
	static ResultSet resultset = null;

	public static FeedbackDao instance = null;

	public static FeedbackDao getInstance() {
		if (instance == null) {
			instance = new FeedbackDao();
		}
		return instance;
	}
	private FeedbackDao() {}

	/**
	 * This function is used to retrieve all the feedback 
	 * for a retaurant given by the customer 
	 * @param RestaurantId
	 * @param CustomerId
	 * @return
	 */

	public List<Feedback> getAllFeedbackForRestaurantByaCustomer(int RestaurantId, int CustomerId) {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String feedbackRestaurantByCustomer = "SELECT * FROM Feedback WHERE Feedback.Restaurant=? and Feedback.Customer= ?";
			statement= conn.prepareStatement(feedbackRestaurantByCustomer);
			statement.setInt(1,RestaurantId);
			statement.setInt(2,CustomerId);
			resultset = statement.executeQuery();
			while(resultset.next()){
				int id= resultset.getInt("id");
				String comment = resultset.getString("comments");
				Boolean favourite = resultset.getBoolean("favourite");
				int Restaurant = resultset.getInt("Restaurant");
				int customer = resultset.getInt("Customer");
				Feedback feedback = new Feedback(id,comment,favourite,Restaurant,customer);
				feedbacks.add(feedback);
			}
			statement.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return feedbacks;
	}

	public List<Feedback> getAllFeedbackForRestaurantId(int RestaurantId) {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String feedbackOfRestaurant = "SELECT * FROM Feedback WHERE Feedback.Restaurant=?";
			statement= conn.prepareStatement(feedbackOfRestaurant);
			statement.setInt(1,RestaurantId);
			resultset = statement.executeQuery();
			while(resultset.next()){
				int id= resultset.getInt("id");
				String comment = resultset.getString("comments");
				Boolean favourite = resultset.getBoolean("favourite");
				int Restaurant = resultset.getInt("Restaurant");
				int customer = resultset.getInt("Customer");
				Feedback feedback = new Feedback(id,comment,favourite,Restaurant,customer);
				feedbacks.add(feedback);
			}
			statement.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return feedbacks;
	}

	public List<Feedback> getAllFeedbackByCustomerId(int CustomerId) {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String feedbackByCustomer = "SELECT * FROM Feedback WHERE Feedback.Customer=?";
			statement= conn.prepareStatement(feedbackByCustomer);
			statement.setInt(1,CustomerId);
			resultset = statement.executeQuery();
			while(resultset.next()){
				int id= resultset.getInt("id");
				String comment = resultset.getString("comments");
				Boolean favourite = resultset.getBoolean("favourite");
				int Restaurant = resultset.getInt("Restaurant");
				int customer = resultset.getInt("Customer");
				Feedback feedback = new Feedback(id,comment,favourite,Restaurant,customer);
				feedbacks.add(feedback);
			}
			statement.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return feedbacks;
	}
	
	public int addFeedbackForRestaurantByCustomer(Feedback feedback, int Restaurant, int Customer) {
		int result = -1;
		try {

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String addFeedBack = "INSERT INTO Feedback "
					+ "(`comments`,`favourite`,`Restaurant`,`Customer`) "
					+ "VALUES (?,?,?,?)";
			statement= conn.prepareStatement(addFeedBack);

			statement.setString(1, feedback.getComment());
			statement.setBoolean(2, feedback.isFavourite());
			statement.setInt(3, Restaurant);
			statement.setInt(4,Customer);

			result = statement.executeUpdate();
			conn.close();
			statement.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteFeedbackById(int FeedbackId) {
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String addFeedBack ="DELETE FROM Feedback WHERE id =?";
			statement= conn.prepareStatement(addFeedBack);
			statement.setInt(1, FeedbackId);
			result = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int deleteFeedbackByIdandCustomerId(int FeedbackId,int CustomerId) {
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String addFeedBack ="DELETE FROM Feedback WHERE id =? and Customer=?";
			statement= conn.prepareStatement(addFeedBack);
			statement.setInt(1, FeedbackId);
			statement.setInt(2, CustomerId);

			result = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int updateFeedback(int CustomerId, Feedback feedback){
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String FeedbackUpdate = "UPDATE Feedback SET comments =?, favourite =? WHERE id =? AND Customer=?";
			statement = conn.prepareStatement(FeedbackUpdate);
			statement.setString(1, feedback.getComment());
			statement.setBoolean(2, feedback.isFavourite());
			statement.setInt(3, feedback.getId());
			statement.setInt(4, CustomerId);
			result = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		}
	
	public List<Feedback> getAllCommentsByCustomerId(int CustomerId) {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String commentsByCustomer = "SELECT id,comments,Customer,Restaurant FROM Feedback WHERE comments IS NOT NULL AND Customer=?";
			statement= conn.prepareStatement(commentsByCustomer);
			statement.setInt(1,CustomerId);
			resultset = statement.executeQuery();
			while(resultset.next()){
				int id= resultset.getInt("id");
				String comments = resultset.getString("comments");
				int Restaurant = resultset.getInt("Restaurant");
				int customer = resultset.getInt("Customer");
				Feedback feedback = new Feedback(id,comments,Restaurant,customer);
				feedbacks.add(feedback);
			}
			statement.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return feedbacks;
	}
	
	public List<Feedback> getAllFavouritesByCustomerId(int CustomerId) {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String favouritesByCustomer = "SELECT id,favourite,Customer,Restaurant FROM Feedback WHERE favourite <>0 AND Customer=?";
			statement= conn.prepareStatement(favouritesByCustomer);
			statement.setInt(1,CustomerId);
			resultset = statement.executeQuery();
			while(resultset.next()){
				int id= resultset.getInt("id");
				Boolean favourite = resultset.getBoolean("favourite");
				int Restaurant = resultset.getInt("Restaurant");
				int customer = resultset.getInt("Customer");
				Feedback feedback = new Feedback(id,favourite,Restaurant,customer);
				feedbacks.add(feedback);
			}
			statement.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return feedbacks;
	}
	
	public List<Feedback> getFeedbackByCustomerId(int id,String comments,Boolean favourite) {
		CustomerDao cDao = CustomerDao.getInstance();
		FeedbackDao feedDao = FeedbackDao.getInstance();
		List<Feedback> feed1 = new ArrayList<>();
		int cust_id = cDao.findCustIdByPersonId(id);
		if(favourite!=null) {
			List<Feedback> feed = feedDao.getAllFavouritesByCustomerId(cust_id);
			return feed;
		}
		else if(comments!=null) {
			List<Feedback> feed2 = feedDao.getAllCommentsByCustomerId(cust_id);
			return feed2;
		}
		else
			feed1 = feedDao.getAllFeedbackByCustomerId(cust_id);
			return feed1;
	}
	
	public static void main(String[] args) {
		FeedbackDao dao = FeedbackDao.getInstance();
//		System.out.println(dao.getAllFeedbackForRestaurantByaCustomer(1, 8));
//		System.out.println(dao.getAllCommentsByCustomerId(8));
		System.out.println(dao.getFeedbackByCustomerId(28, "abc", null));
//		System.out.println(dao.getAllFavouritesByCustomerId(8));
		
	}
}


