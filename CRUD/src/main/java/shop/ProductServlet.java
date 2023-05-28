package shop;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
   private List<Product> productList;
   @Override
   public void init() throws  ServletException{
      super.init();
      productList = new ArrayList<>();
      productList.add(new Product(1,"Apple AirTag",28.90));
      productList.add(new Product(2,"Apple Magic Keyboard",24.95));
      productList.add(new Product(3,"Apple Watch Band",27.96));
      productList.add(new Product(4,"Apple Watch Series 8",25.30));
      productList.add(new Product(5,"Apple 2021 iMac",26.90));
      productList.add(new Product(6,"Apple ",20));
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
       Product newProduct = new Product(id, name, price);
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
      
   }
}
