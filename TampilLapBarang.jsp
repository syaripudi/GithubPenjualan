<%--<%@page import="control.koneksi"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>--%>

<%--<%@page contentType="application/pdf"%>--%>

<%@page trimDirectiveWhitespaces="true"%>
<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="net.sf.jasperreports.view.JasperViewer.*"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.servlet.ServletResponse"%>
<%@page import="java.io.*, java.util.*, java.sql.*"%>




<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Laporan Barang</title>
    </head>
    <body>
        <%
            Connection conn = null;
            int id = Integer.parseInt(request.getParameter("jumlah"));
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pembelian","root","");
                
                File reportFile = new File(application.getRealPath("/laporan/Laporan_Barang.jasper"));
                Map parameters = new HashMap();
                parameters.put("pstok",id);
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
                
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream outStream = response.getOutputStream();
                outStream.write(bytes, 0,bytes.length);
                outStream.flush();
                outStream.close();
            } catch(Exception ex){
                out.println("Error : " + ex.getMessage());
            }

            
            
       

        %>
    </body>
</html>
