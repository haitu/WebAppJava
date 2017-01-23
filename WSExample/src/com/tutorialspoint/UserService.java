package com.tutorialspoint;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/UserService")
public class UserService {

   UserDao userDao = new UserDao();

   @GET
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   public List<User> getUsers(){
      return userDao.getAllUsers();
   }
   @GET
   @Path("/users2")
   @Produces(MediaType.APPLICATION_XML)
   public List<User> getUsers1(){
      return userDao.getAllUsers();
   }
   
   @GET
   @Path("/usersbyJson")
   //@Produces(MediaType.APPLICATION_JSON)
   @Produces("application/json")
   public List<User> getUsersByJson(){
      return userDao.getAllUsers();
   }
   
}