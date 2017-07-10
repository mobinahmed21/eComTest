package com.mobin.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hdu on 3/7/17.
 */
@Getter
@Setter
@Slf4j
public class DataSourcePropertyConfig {

  private String url;
  private String user;
  private String password;
  private String driverClassName;
  private int poolSize = 50;
  private long connectionMaxAgeMinutes = 8 * 60;
  private boolean testOnBorrow = true;
  private String validationQuery = "SELECT 1";
  private long validationIntervalSeconds = 30;
  private Map<String, String> hibernate;
  private String role;
  private Map<String, Object> jsonreadMap = null;
  private Environment env;

  @Autowired
  public DataSourcePropertyConfig(Environment env){
      this.env = env;
      this.user = "root";
      this.password = "1234";
      this.driverClassName="com.mysql.cj.jdbc.Driver";
      this.url = "jdbc:mysql://localhost:3306/mobin";
      log.info("DATABASE DETAILS ADDED");
  }
}
