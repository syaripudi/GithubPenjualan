<%@page import="java.sql.*"%>
<%@page import="control.koneksi"%>
<%@page import="model.pembelian"%>

<%
  koneksi konst = new koneksi();
  ResultSet rsst = null;
  String emp_brg = request.getParameter("emp_brg").toString();
  String data = null;
  Statement st;
  
  rsst = konst.stmt.executeQuery("SELECT * FROM barang WHERE kd_brg='" + emp_brg + "'");
  while(rsst.next()){
      data = ":" + rsst.getString(3) + ":" + emp_brg;
  }
  out.println(data);
%>
