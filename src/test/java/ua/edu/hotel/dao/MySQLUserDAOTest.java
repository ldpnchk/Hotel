package ua.edu.hotel.dao;

import org.junit.*;
import ua.edu.model.dao.AbstractDaoFactory;
import ua.edu.model.dao.UserDao;
import ua.edu.model.dao.connection.DataSource;
import ua.edu.model.entity.User;
import ua.edu.model.entity.UserRole;
import ua.edu.model.util.PasswordGenerator;

import java.util.Optional;

public class MySQLUserDAOTest {

    @BeforeClass
    public static void delete(){
        UserDao userDao = AbstractDaoFactory.getFactory().createUserDao(DataSource.getInstance().getConnection());

        Optional<User> user = userDao.getUserByUsername("petro");

        if (user.isPresent()){
            userDao.delete(user.get().getId());
        }
    }

    @Test
    public void testCreate(){
        UserDao userDao = AbstractDaoFactory.getFactory().createUserDao(DataSource.getInstance().getConnection());

        User user = new User.UserBuilder()
                .setUsername("petro")
                .setPassword(PasswordGenerator.getInstance().generatePassword("petro"))
                .setEmail("petro@hotel.ua")
                .setPhoneNumber("0505050505")
                .setFirstName("Petro")
                .setLastName("Zima")
                .setUserRole(UserRole.CLIENT)
                .build();

        userDao.create(user);

        Assert.assertNotNull(user.getId());

        userDao.delete(userDao.getUserByUsername("petro").get().getId());
    }

    @Test
    public void testUpdate(){
        UserDao userDao = AbstractDaoFactory.getFactory().createUserDao(DataSource.getInstance().getConnection());

        User user = new User.UserBuilder()
                .setUsername("petro")
                .setPassword(PasswordGenerator.getInstance().generatePassword("petro"))
                .setEmail("petro@hotel.ua")
                .setPhoneNumber("0505050505")
                .setFirstName("Petro")
                .setLastName("Zima")
                .setUserRole(UserRole.CLIENT)
                .build();

        userDao.create(user);

        user.setEmail("petro007@hotel.ua");

        userDao.update(user);

        Assert.assertEquals("petro007@hotel.ua", userDao.getUserByUsername("petro").get().getEmail());

        userDao.delete(userDao.getUserByUsername("petro").get().getId());
    }

    @Test
    public void testDelete(){
        UserDao userDao = AbstractDaoFactory.getFactory().createUserDao(DataSource.getInstance().getConnection());

        User user = new User.UserBuilder()
                .setUsername("petro")
                .setPassword(PasswordGenerator.getInstance().generatePassword("petro"))
                .setEmail("petro@hotel.ua")
                .setPhoneNumber("0505050505")
                .setFirstName("Petro")
                .setLastName("Zima")
                .setUserRole(UserRole.CLIENT)
                .build();

        userDao.create(user);

        userDao.delete(user.getId());

        Assert.assertFalse(userDao.getUserByUsername("petro").isPresent());
    }

    @Test
    public void testGetByEmail(){
        UserDao userDao = AbstractDaoFactory.getFactory().createUserDao(DataSource.getInstance().getConnection());

        User user = new User.UserBuilder()
                .setUsername("petro")
                .setPassword(PasswordGenerator.getInstance().generatePassword("petro"))
                .setEmail("petro@hotel.ua")
                .setPhoneNumber("0505050505")
                .setFirstName("Petro")
                .setLastName("Zima")
                .setUserRole(UserRole.CLIENT)
                .build();

        userDao.create(user);

        Assert.assertEquals("petro", userDao.getUserByEmail("petro@hotel.ua").get().getUsername());

        userDao.delete(userDao.getUserByUsername("Petro").get().getId());
    }

    @Test
    public void testGetByUsername(){
        UserDao userDao = AbstractDaoFactory.getFactory().createUserDao(DataSource.getInstance().getConnection());

        User user = new User.UserBuilder()
                .setUsername("petro")
                .setPassword(PasswordGenerator.getInstance().generatePassword("petro"))
                .setEmail("petro@hotel.ua")
                .setPhoneNumber("0505050505")
                .setFirstName("Petro")
                .setLastName("Zima")
                .setUserRole(UserRole.CLIENT)
                .build();

        userDao.create(user);

        Assert.assertEquals("petro@hotel.ua", userDao.getUserByUsername("petro").get().getEmail());

        userDao.delete(userDao.getUserByUsername("Petro").get().getId());
    }

}
