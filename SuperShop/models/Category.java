package models;

public class Category {
    private String categoryId;
    private String name;

public Category(){

}

public Category(String categoryId, String name)
{
    this.categoryId = categoryId;
    this.name = name;
}

public String getCategoryId()
{
    return categoryId;
}

public void setCategoryId(String categoryId)
{
    this.categoryId = categoryId;
}

public String getName()
{
    return name;
}

public void setName(String name)
{
    this.name = name;
}

public String toStringCategory()
{
    String str=categoryId + "," + name+"\n";
    return str;
}

public Category formCategory(String str)
{
    String[] data = str.split(",");
    Category c = new Category();
    c.setCategoryId(data[0]);
    c.setName(data[1]);
    return c;
}

}