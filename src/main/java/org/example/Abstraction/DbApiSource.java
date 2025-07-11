package org.example.Abstraction;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbApiSource {

    Connection connect() throws SQLException;
}
