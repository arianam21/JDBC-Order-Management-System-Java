package DataAccess;

import Connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract Data Access Object providing generic CRUD operations for entities of type T.
 *
 * @param <T> the entity type
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER=Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;
    /**
     * Constructs a new AbstractDAO instance and initializes the entity class type.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    private String createSelectQuery(String field){
        StringBuilder sb=new StringBuilder();
        sb.append("SELECT ");
        sb.append("*");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + "=?");
        return sb.toString();
    }
    private List<T> createObjects(ResultSet resultSet)
    {
        List<T>list=new ArrayList<T>();
        try{
            while(resultSet.next()){
                T instance=type.newInstance();
                for(Field field : type.getDeclaredFields()){
                    Object value=resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field.getName(),type);
                    Method method=propertyDescriptor.getWriteMethod();
                    method.invoke(instance,value);
                }
                list.add(instance);
            }
        }
        catch(InstantiationException | SQLException  | IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * Finds an entity by its unique ID.
     *
     * @param id the ID of the entity to find
     * @return the entity if found, otherwise null
     */
    public T findById(int id){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query=createSelectQuery("id");
        try{
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            return createObjects(resultSet).get(0);
        }catch(SQLException ex){
            LOGGER.log(Level.WARNING,type.getName()+"DAO:findById"+ ex.getMessage());
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


  private String findAllQuery(){
      StringBuilder sb=new StringBuilder();
      sb.append("SELECT ");
      sb.append("*");
      sb.append(" FROM ");
      String tableName = type.getSimpleName();
      if (tableName.equalsIgnoreCase("Order")) {
          tableName = "`Order`";
      }
      sb.append(tableName);
      return sb.toString();
  }
    /**
     * Retrieves all entities of type T from the database.
     *
     * @return a List of all entities, or null if an error occurs
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query=findAllQuery();
        try {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:findAll"+ e.getMessage());
        }
        finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    private String insertQuery(){
        StringBuilder sb=new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" ( ");
        Field[] fields=type.getDeclaredFields();
        int i=0;
        for(Field f:fields){
            sb.append(f.getName());
            if(i!=fields.length-1)
                sb.append(", ");
            i++;
        }
        sb.append(" ) VALUES (");
        i=0;
        for(Field f:fields){
            sb.append("?");
            if(i!=fields.length-1)
                sb.append(", ");
            i++;
        }
        sb.append(")");
        return sb.toString();
    }
    /**
     * Inserts a new entity into the database.
     *
     * @param t the entity to insert
     * @return the inserted entity, or null if insertion failed
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query=insertQuery();
        try {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            Field[] fields=type.getDeclaredFields();
            int i=0;
            for(Field f:fields){
                PropertyDescriptor propertyDescriptor=new PropertyDescriptor(f.getName(),type);
                Method method=propertyDescriptor.getReadMethod();
                Object value=method.invoke(t);
                statement.setObject(i+1,value);
                i++;
            }
            statement.executeUpdate();
            return t;
        } catch (SQLException | IntrospectionException e) {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:insert"+ e.getMessage());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private String deleteQuery(){
        StringBuilder sb=new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + "id" + "=?");
        return sb.toString();
    }
    /**
     * Deletes an entity from the database by its ID.
     *
     * @param id the ID of the entity to delete
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = deleteQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    private String updateQuery(){
        StringBuilder sb=new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for(int i=1;i<type.getDeclaredFields().length;i++)
        {
            sb.append(type.getDeclaredFields()[i].getName());
            sb.append("=?");
            if(i<type.getDeclaredFields().length-1)
                sb.append(", ");
        }
        sb.append(" WHERE " + "id" + "=?");
        return sb.toString();
    }
    /**
     * Updates an existing entity in the database.
     *
     * @param t the entity with updated data
     * @return the updated entity, or null if update failed
     */
    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = updateQuery();
        try{
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            Field[] fields=type.getDeclaredFields();

            for(int i=1;i<fields.length;i++)
            {
                PropertyDescriptor pd = new PropertyDescriptor(fields[i].getName(), type);
                Object value = pd.getReadMethod().invoke(t);
                statement.setObject(i, value);
            }

            PropertyDescriptor pd = new PropertyDescriptor(fields[0].getName(), type);
            Object idValue = pd.getReadMethod().invoke(t);
            statement.setObject(fields.length, idValue);

            statement.executeUpdate();
            return t;
        }
        catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * Populates a Swing JTable with data from a list of entities.
     *
     * @param objects the list of entities to display
     * @param table   the JTable to populate
     */
    public void populateTable(List<T> objects, JTable table) {
        if (objects == null || objects.isEmpty()) {
            return;
        }
        Field[] fields = type.getDeclaredFields();
        String[] columnNames = new String[fields.length];
        int i=0;
        for(Field f:fields){
            columnNames[i]=f.getName();
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        try {
            for (T obj : objects) {
                Object[] data = new Object[fields.length];
                for (i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    Object value = fields[i].get(obj);
                    data[i] = value;
                }
                model.addRow(data);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        table.setModel(model);
    }


}
