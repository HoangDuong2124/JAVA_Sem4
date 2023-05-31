package Student;

public class Student {
   private Integer id ;
   private String name;
   private String maHS ;
   private String classHS;
   private double point;
   public Student(Integer id,String name,String maHS, String classHS,double point){
       super();
       this.id=id;
       this.name=name;
       this.maHS=maHS;
       this.classHS=classHS;
       this.point=point;
   }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMaHS() {
        return maHS;
    }
    public void setMaHS(String maHS) {
        this.maHS = maHS;
    }
    public String getClassHS() {
        return classHS;
    }
    public void setClassHS(String classHS) {
        this.classHS = classHS;
    }
    public double getPoint() {
        return point;
    }
    public void setPoint(double point) {
        this.point = point;
    }
}
