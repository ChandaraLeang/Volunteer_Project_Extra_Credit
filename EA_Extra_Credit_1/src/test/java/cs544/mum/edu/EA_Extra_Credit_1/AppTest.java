package cs544.mum.edu.EA_Extra_Credit_1;

/**
 * Name: CHANDARA LEANG
 * ID: 108619
 * Task: Extra Credit 1
 * Class: AppTest
 */

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs544.mum.edu.model.Administrator;
import cs544.mum.edu.model.Project;
import cs544.mum.edu.model.Resource;
import cs544.mum.edu.model.Status;
import cs544.mum.edu.model.Task;
import cs544.mum.edu.model.User;
import cs544.mum.edu.model.Volunteer;

public class AppTest {
	private SessionFactory sessionFactory;
	private Session session = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Before
	public void init() {
		// setup the session factory
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Project.class).addAnnotatedClass(User.class).addAnnotatedClass(Resource.class)
				.addAnnotatedClass(Task.class).addAnnotatedClass(Volunteer.class)
				.addAnnotatedClass(Administrator.class);

		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/test");
		configuration.setProperty("hibernate.connection.username", "sa");
		configuration.setProperty("hibernate.connection.password", "sa");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create");
		sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	public Integer createProject() throws ParseException {
		Transaction tx = session.beginTransaction();
		Resource resource = new Resource();
		resource.setDescription("Exam 1");
		session.persist(resource);

		Task task1 = new Task();
		task1.setStatus(Status.NEW);
		task1.setResources(Arrays.asList(resource));
		task1.setTimeFrame(10);
		task1.setDescription("Task1");
		session.persist(task1);

		Task task2 = new Task();
		task2.setStatus(Status.NEW);
		task2.setResources(Arrays.asList(resource));
		task2.setTimeFrame(10);
		task2.setDescription("Task2");
		session.persist(task2);

		Project project = new Project();
		project.setDescription("EA Extra Credit 1");
		project.setStatus(Status.INPROGRESS);
		project.setExpectedStartDate(sdf.parse("2017-07-31"));
		project.setExpectedEndDate(sdf.parse("2017-08-01"));
		project.setLocation("FairField");
		project.addTask(task1);
		project.addTask(task2);
		session.persist(project);

		Administrator admin1 = new Administrator();
		admin1.setName("Chandara Leang");
		admin1.addProject(project);
		session.persist(admin1);

		tx.commit();

		System.out.println(project.getId());
		return project.getId();
	}

	@Test
	public void TestCreateProject() throws ParseException {
		Integer projectId = createProject();

		Transaction tx = session.beginTransaction();
		Project pro = session.get(Project.class, projectId);
		assertTrue(pro.getDescription().equals("EA Extra Credit 1"));
		tx.commit();
	}

	@Test
	public void TestAssignTaskToUser() throws ParseException {
		Integer projectId = createProject();

		Transaction tx = session.beginTransaction();
		Project pro = session.get(Project.class, projectId);

		Volunteer v1 = null;
		if (pro.getTasks().size() > 0) { // add first task to volunteer1
			v1 = new Volunteer();
			v1.setName("Channa Leang");
			v1.addTask(pro.getTasks().get(0));
			session.persist(v1);
		}

		assertTrue(session.get(Volunteer.class, v1.getId()).getName().equals("Channa Leang"));
		tx.commit();

	}

	@Test
	public void TestListTasksForProject() throws ParseException {
		Integer projectId = createProject();

		Transaction tx = session.beginTransaction();
		Project pro = session.get(Project.class, projectId);

		assertTrue(pro.getTasks().size() >= 2);
		tx.commit();
	}

	@Test
	public void TestListProjectByStatus() throws ParseException {
		createProject();
		Transaction tx = session.beginTransaction();
		Query q1 = session.createQuery("SELECT p FROM Project p WHERE p.status = :status");
		q1.setParameter("status", Status.INPROGRESS);

		List<Project> projects = q1.list();
		assertTrue(projects.size() > 0);
	}

	@Test
	public void TestSearchProjectByKeywordAndLocation() throws ParseException {
		createProject();
		Transaction tx = session.beginTransaction();
		Query q1 = session
				.createQuery("SELECT p FROM Project p WHERE p.description LIKE :keyword AND p.location = :location");
		q1.setParameter("keyword", "EA%");
		q1.setParameter("location", "FairField");

		List<Project> projects = q1.list();
		assertTrue(projects.size() > 0);
	}

	@After
	public void after() {
		session.close();
		sessionFactory.close();
	}
}