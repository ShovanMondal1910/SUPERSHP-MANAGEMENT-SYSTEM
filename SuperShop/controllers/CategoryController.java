package controllers;
import models.*;

public class CategoryController {
    
    public void insertCategory(Category c) {
        Category categories[] = this.getAllCategory();
        
        for(int i = 0; i < categories.length; i++) {
            if(categories[i] == null) {
                categories[i] = c;
                break;
            }
        }
        
        this.write(categories);
    }
    
    public void updateCategory(Category c) {
        Category categories[] = this.getAllCategory();
        
        for(int i = 0; i < categories.length; i++) {
            if(categories[i] != null) {
                if(categories[i].getCategoryId().equals(c.getCategoryId())) {
                    categories[i] = c;
                    break;
                }
            }
        }
        
        this.write(categories);
    }
    
    public void deleteCategory(String categoryId) {
        Category categories[] = this.getAllCategory();
        
        for(int i = 0; i < categories.length; i++) {
            if(categories[i] != null) {
                if(categories[i].getCategoryId().equals(categoryId)) {
                    categories[i] = null;
                    break;
                }
            }
        }
        
        this.write(categories);
    }
    
    public Category searchCategory(String categoryId) {
        Category categories[] = this.getAllCategory();
        
        for(int i = 0; i < categories.length; i++) {
            if(categories[i] != null) {
                if(categories[i].getCategoryId().equals(categoryId)) {
                    return categories[i];
                }
            }
        }
        
        return null;
    }
    
    public Category[] getAllCategory() {
        String fileName = "controllers/data/Categories.txt";
        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);
        
        Category categories[] = new Category[100];
        
        for(int i = 0; i < values.length; i++) {
            if(values[i] != null && !values[i].trim().isEmpty()) {
                if(categories[i] == null) {
                    Category c = new Category();
                    categories[i] = c.formCategory(values[i]);
                }
            }
        }
        
        return categories;
    }
    
    public void write(Category categories[]) {
        String data[] = new String[100];
        
        for(int i = 0; i < categories.length; i++) {
            if(categories[i] != null) {
                data[i] = categories[i].toStringCategory();
            }
        }
        
        String fileName = "controllers/data/Categories.txt";
        FileIO fio = new FileIO();
        fio.writeFile(fileName, data);
    }
} 