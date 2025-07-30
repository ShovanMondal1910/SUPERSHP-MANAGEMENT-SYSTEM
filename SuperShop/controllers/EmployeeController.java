package controllers;
import models.*;

public class EmployeeController {

    public void insertEmployee(Employee e) {
        Employee employee[] = this.getAllEmployee();
        
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] == null) {
                employee[i] = e;
                break;
            }
        }
        
        this.write(employee);
        saveUserToUsersFile(e);
    }

    private void saveUserToUsersFile(Employee e) {
        String fileName = "controllers/data/Users.txt";
        FileIO fio = new FileIO();
        String[] users = fio.readFile(fileName);
        int n = users.length;
        int insertIdx = n;
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null || users[i].trim().isEmpty()) {
                insertIdx = i;
                break;
            }
        }
        String photoPath = e.getPhotoPath() != null ? e.getPhotoPath() : "";
        String userLine = e.getUserId() + "," + e.getRole() + "," + e.getSecurityAns() + "," + e.getPassword() + "," + photoPath;
        if (insertIdx < users.length) {
            users[insertIdx] = userLine;
        } else {
            String[] newUsers = new String[users.length + 1];
            System.arraycopy(users, 0, newUsers, 0, users.length);
            newUsers[users.length] = userLine;
            users = newUsers;
        }
        fio.writeFile(fileName, users);
    }
    
    public void updateEmployee(Employee e) {
        Employee employee[] = this.getAllEmployee();
        
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] != null) {
                if (employee[i].getUserId().equals(e.getUserId())) {
                    employee[i] = e;
                }
            }
        }
        
        this.write(employee);
        // Update Users.txt as well
        updateUserInUsersFile(e);
    }

    private void updateUserInUsersFile(Employee e) {
        String fileName = "controllers/data/Users.txt";
        FileIO fio = new FileIO();
        String[] users = fio.readFile(fileName);
        
        // Find and update the existing user entry
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].startsWith(e.getUserId() + ",")) {
                String photoPath = e.getPhotoPath() != null ? e.getPhotoPath() : "";
                String userLine = e.getUserId() + "," + e.getRole() + "," + e.getSecurityAns() + "," + e.getPassword() + "," + photoPath;
                users[i] = userLine;
                break;
            }
        }
        
        fio.writeFile(fileName, users);
    }
    
    public void deleteEmployee(String userId) {
        Employee employee[] = this.getAllEmployee();
        
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] != null) {
                if (employee[i].getUserId().equals(userId)) {
                    employee[i] = null;
                }
            }
        }
        
        this.write(employee);
        // Remove from Users.txt as well
        removeUserFromUsersFile(userId);
    }

    private void removeUserFromUsersFile(String userId) {
        String fileName = "controllers/data/Users.txt";
        FileIO fio = new FileIO();
        String[] users = fio.readFile(fileName);
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].startsWith(userId + ",")) {
                users[i] = null;
            }
        }
        fio.writeFile(fileName, users);
    }
    
    public Employee searchEmployee(String userId) {
        Employee employee[] = this.getAllEmployee();
        
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] != null) {
                if (employee[i].getUserId().equals(userId)) {
                    return employee[i];
                }
            }
        }
        
        return null;
    }
    
    public Employee[] getAllEmployee() {
        String fileName = "controllers/data/Employees.txt";
        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);
        
        Employee employee[] = new Employee[100];
        
        Employee e = new Employee();
        
        for (int i = 0; i < values.length; i++) {
            if(values[i] != null && !values[i].trim().isEmpty()){
                if (employee[i] == null) {
                    employee[i] = e.formEmployee(values[i]);
                }
            }
        }
        
        return employee;
    }
    
    public void write(Employee employee[]) {
        String data[] = new String[100];
        
        for (int i = 0; i < data.length; i++) {
            if (employee[i] != null) {
                data[i] = employee[i].toStringEmployee();
            }
        }
        
        String fileName = "controllers/data/Employees.txt";
        
        FileIO fio = new FileIO();
        fio.writeFile(fileName, data);
    }
}
