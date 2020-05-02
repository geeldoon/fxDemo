package home.models;

public class Student {
    private String name;
    private int id;
    private String level;
    private String className;

    public Student(String name, int id, String level, String className) {
        this.name = name;
        this.id = id;
        this.level = level;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
