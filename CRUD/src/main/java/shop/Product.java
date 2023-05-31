package shop;

public class Product {
   private Integer id;
   private String name;
   private double price;
   private String image;
   public Product(Integer id, String name, double price,String image){
       super();
       this.id=id;
       this.name=name;
       this.price=price;
       this.image=image;
   }
   public Integer getId(){return id;}
   public void setId(Integer id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public double getPrice(){return price;}
    public void setPrice(double price){this.price=price;}
    public String getImage(){return image;}
    public void setImage(String image){
       this.image=image;
    }
}
