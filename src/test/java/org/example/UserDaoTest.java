package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    @BeforeEach //테스트코드를 실행하기 앞서 수행해야할 작업이있다면 여기에다가 코드를 작성하면됨
    void setUp() {

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator,ConnectionManager.getDataSource());
    }

    @Test
    void createTest() throws SQLException {
        UserDao userDao = new UserDao();

        userDao.create(new User("jun","password","name","email"));

        User user = userDao.findByIdUserId("jun");
        assertThat(user).isEqualTo(new User("jun","password","name","email"));
    }
}
