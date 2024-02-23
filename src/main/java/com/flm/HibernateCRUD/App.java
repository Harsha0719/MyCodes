package com.flm.HibernateCRUD;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {

	private static final SessionFactory sessionFactory;

	static {
		sessionFactory = buildSessionFactory();
	}

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
					.buildSessionFactory();

		} catch (Exception e) {
			System.out.println("Initial SessionFactory creation failed." + e);
			e.printStackTrace();
			return null; 
		}
	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			boolean run = true;

			while (run) {
				System.out.println("\nMenu Options:");
				System.out.println("1. To Insert Data");
				System.out.println("2. To Update Data");
				System.out.println("3. To Fetch Data");
				System.out.println("4. To Delete Data");
				System.out.println("5. Exit");

				System.out.print("Enter your choice: ");
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					insertData(scanner);
					break;
				case 2:
					updateData(scanner);
					break;
				case 3:
					fetchData(scanner);
					break;
				case 4:
					deleteData(scanner);
					break;
				case 5:
					System.out.println("Thanks for using our Services");
					run = false;
					break;
				default:
					System.out.println("Invalid choice. Please enter a correct option.");
					break;
				}
			}
		}
	}

	private static void fetchData(Scanner scanner) {
		try (Session session = sessionFactory.openSession()) {
			System.out.println("Enter IdNo to get details:");
			String id = scanner.next();
			Student student = session.get(Student.class, id);
			if (student != null) {
				System.out.println(student);
				System.out.println("Data Read Successfully");
			} else {
				System.out.println("No student found with IDNO: " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void deleteData(Scanner scanner) {
		try (Session session = sessionFactory.openSession()) {
			System.out.println("Enter idNo to delete:");
			String id = scanner.next();
			Student student = session.get(Student.class, id);
			if (student != null) {
				Transaction transaction = session.beginTransaction();
				session.delete(student);
				transaction.commit();
				System.out.println("Data Deleted Successfully");
			} else {
				System.out.println("No student found with ID: " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void updateData(Scanner scanner) {
		try (Session session = sessionFactory.openSession()) {
			System.out.println("Enter id to update:");
			String id = scanner.next();
			Student student = session.get(Student.class, id);
			if (student != null) {
				System.out.println("Enter name:");
				student.setName(scanner.next());
				System.out.println("Enter Age:");
				student.setAge(scanner.nextInt());
				System.out.println("Enter Year of Studying:");
				student.setYear(scanner.nextInt());
				System.out.println("Enter Branch:");
				student.setBranch(scanner.next());
				System.out.println("Enter Section:");
				student.setSection(scanner.next().charAt(0));
				Transaction transaction = session.beginTransaction();
				session.update(student);
				transaction.commit();
				System.out.println("Data Updated Successfully");
			} else {
				System.out.println("No student found with ID: " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertData(Scanner scanner) {
		try (Session session = sessionFactory.openSession()) {
			Student studentObject = new Student();
			System.out.println("Enter ID.No:");
			studentObject.setIdno(scanner.next());
			System.out.println("Enter name:");
			studentObject.setName(scanner.next());
			System.out.println("Enter Age:");
			studentObject.setAge(scanner.nextInt());
			System.out.println("Enter Year of Studying:");
			studentObject.setYear(scanner.nextInt());
			System.out.println("Enter Branch:");
			studentObject.setBranch(scanner.next());
			System.out.println("Enter Section:");
			studentObject.setSection(scanner.next().charAt(0));

			Transaction transaction = session.beginTransaction();
			session.save(studentObject);
			transaction.commit();
			System.out.println("Data inserted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
