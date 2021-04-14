package com.comp3026.profryanclements.mysqlexample;

import static org.junit.jupiter.api.Assertions.*;

class MySQLExampleTest {

    @org.junit.jupiter.api.Test
    void readDataBase() throws Exception {
        MySQLExample sql = new MySQLExample();
        sql.readDataBase();
    }
}