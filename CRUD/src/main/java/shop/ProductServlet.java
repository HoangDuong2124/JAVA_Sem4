package shop;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.Part;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/products")
@MultipartConfig
public class ProductServlet extends HttpServlet {
   private List<Product> productList;
   @Override
   public void init() throws  ServletException{
      super.init();
      productList = new ArrayList<>();
      productList.add(new Product(1,"Apple AirTag",28.90,"images/head_phone.png"));
      productList.add(new Product(2,"Apple Magic Keyboard",24.95,"images/headphone2.webp"));
      productList.add(new Product(3,"Apple Watch Band",27.96,"images/head_phone.png"));
      productList.add(new Product(4,"Apple Watch Series 8",25.30,"images/headphone2.webp"));
      productList.add(new Product(5,"Apple 2021 iMac",26.90,"images/head_phone.png"));
      productList.add(new Product(6,"Apple ",20,"images/headphone2.webp"));
   }
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
      String action = request.getParameter("action");
      if (action==null){
         action = "list";
      }
      switch (action){
         case "new":
            showNewForm(request,response);
            break;
         case "create":
            createProduct(request,response);
            break;
         case "edit":
            showEditForm(request,response);
            break;
         case "delete":
            deleteProduct(request,response);
            break;
         case "update":
            updateProduct(request,response);
            break;
         default:
            listProducts(request,response);
            break;
      }
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
      doGet(request,response);
   }
   private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
      request.setAttribute("productList",productList);
      RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
      dispatcher.forward(request,response);
   }
   private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
      RequestDispatcher dispatcher =request.getRequestDispatcher("product-form.jsp");
      dispatcher.forward(request,response);
   }
   private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
      String name = request.getParameter("name");
      double price = Double.parseDouble(request.getParameter("price"));
      int id = productList.size() +1;
      Part filePart = request.getPart("image");
      String fileName = getFileName(filePart);
      String uploadDirectory = getServletContext().getRealPath("/images");
      String filePath = uploadFile(filePart,fileName,uploadDirectory);
      String fileURL = "images/"+ fileName;
       Product newProduct = new Product(id, name, price,fileURL);
       productList.add(newProduct);
       response.sendRedirect("products");
   }
   private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
      int id = Integer.parseInt(request.getParameter("id"));
      Product product = getProductById(id);
      request.setAttribute("product",product);
      RequestDispatcher dispatcher =request.getRequestDispatcher("product-form.jsp");
      dispatcher.forward(request,response);
   }
   private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
      int id = Integer.parseInt(request.getParameter("id"));
      String name = request.getParameter("name");
      double price = Double.parseDouble(request.getParameter("price"));

      Product product = getProductById(id);
      product.setName(name);
      product.setPrice(price);
      response.sendRedirect("products");
   }
   private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
      int id= Integer.parseInt(request.getParameter("id"));
      Product product = getProductById(id);
      productList.remove(product);
      response.sendRedirect("products");
   }
   private Product getProductById(int id){
      for(Product product : productList){
         if (product.getId()==id){
            return product;
         }
      }
      return null;
   }
   private void deleteImage(String image,HttpServletRequest request){
       String uploadDirectory = request.getServletContext().getRealPath("")+File.separator+"images";
       String imagePath =uploadDirectory+File.separator+image;
       File imageFile = new File(imagePath);
       if (imageFile.exists()){
          imageFile.delete();
       }
   }
   private String uploadFile(Part filePart, String fileName, String uploadDirectory) throws IOException{
      String filePath = uploadDirectory + File.separator+ fileName;
      try (
         InputStream inputStream = filePart.getInputStream();
         FileOutputStream outputStream = new FileOutputStream(filePath)){
         byte[] buffer = new byte[8192];
         int bytesRead;
         while ((bytesRead= inputStream.read(buffer)) != -1){
            outputStream.write(buffer,0,bytesRead);
         }
      }
      return filePath;
   }
   private String getFileName(Part part){
      String contentDisposition = part.getHeader("content-disposition");
      String[] elements = contentDisposition.split(";");
      for (String element : elements){
         if (element.trim().startsWith("filename")){
            return element.substring(element.indexOf("=")+1).trim().replace("\"", "");
         }
      }
      return "";
   }
}
