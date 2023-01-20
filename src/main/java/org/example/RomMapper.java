package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RomMapper {

    Object map(ResultSet resultSet) throws SQLException;
}
