<%@page import="java.sql.*"%>
<%@page import="control.koneksi"%>
<%@page import="model.pemesanan"%>

<%
  koneksi konss = new koneksi();
  ResultSet rsss = null;
  String emp_no = request.getParameter("emp_no").toString();
  String data = null;
  Statement st;
  
  rsss = konss.stmt.executeQuery("SELECT * FROM pemesanan WHERE no_pesan='" + emp_no + "'");
  while(rsss.next()){
      data = ":" + rsss.getString(3) + ":" + emp_no;
  }
  out.println(data);
%>
