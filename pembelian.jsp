<%-- 
    Document   : pembelian
    Created on : Apr 8, 2020, 8:10:40 PM
    Author     : Windows 10
--%>
<%@page import="control.koneksi"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<%@page import="model.pemesanan"%>
<%@page import="model.pembelian"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    java.util.Date tgls = new java.util.Date();
    int tanggal = tgls.getDate();
    int bulan = tgls.getMonth() + 1;
    int tahun = tgls.getYear() + 1900;
    
    String tgl = tahun + "-" + bulan + "-" + tanggal;
    
    String no_pesann = null;
    String no_beli = null;
    String tgl_beli = null;
    String no_faktur = null;
    String total_beli = null;
    pembelian var = new pembelian(no_pesann,no_beli,tgl_beli,no_faktur,total_beli);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Halaman Pembelian</title>
    </head>
    <body>
        <h1>Transaksi Pembelian Barang</h1>
        <form action="PembelianServlet" method="post">
            <table border="0">
                <tr>
                    <td>Nomor Beli</td>
                    <td>:</td>
                    <td>
                        <%
                        koneksi kon = new koneksi();
                        Class.forName("com.mysql.jdbc.Driver");
                        ResultSet nobeli = null;
                        nobeli = kon.stmt.executeQuery("SELECT MAX(right(no_beli,6)) AS no FROM pembelian");
                        while(nobeli.next()){
                            if(nobeli.first() == false){
                                out.println("<input type='text' name='no_beli' value='PB000001' id='no_beli' readonly/>");
                            }else {
                                nobeli.last();
                                int autobels = nobeli.getInt(1) + 1;
                                String nomorpb = String.valueOf(autobels);
                                int noLong = nomorpb.length();
                                
                                for(int a = 1; a < 7 - noLong; a++)
                                {
                                    nomorpb = "0" + nomorpb;
                                }
                                String nomerpb = "PB" + nomorpb;
                                out.println("<input type='text' name='no_beli' value='" + nomerpb + "' id='no_beli' readonly/>");
                            }
                        }
                    %>
                    </td>
                </tr>
                
                <tr>
                    <td>Nomor Faktur</td>
                    <td>:</td>
                    <td>
                        <%
                        Class.forName("com.mysql.jdbc.Driver");
                        ResultSet nofaktur = null;
                        nofaktur = kon.stmt.executeQuery("SELECT MAX(right(no_beli,6)) AS no FROM pembelian");
                        while(nofaktur.next()){
                            if(nofaktur.first() == false){
                                out.println("<input type='text' name='no_faktur' value='FK000001' id='no_faktur' readonly/>");
                            }else {
                                nofaktur.last();
                                int autofaks = nofaktur.getInt(1) + 1;
                                String nomorfk = String.valueOf(autofaks);
                                int noLong = nomorfk.length();
                                
                                for(int a = 1; a < 7 - noLong; a++)
                                {
                                    nomorfk = "0" + nomorfk;
                                }
                                String nomerfk = "FK" + nomorfk;
                                out.println("<input type='text' name='no_faktur' value='" + nomerfk + "' id='no_faktur' readonly/>");
                            }
                        }
                    %>
                    </td>
                </tr>
                
                <tr>
                    <td>Tanggal Beli</td>
                    <td>:</td>
                    <td><input readonly value="<%=tgl%>" name="tgl_beli"></td>
                </tr>
                
                <tr>
                    <td>Nomor Pesan</td>
                    <td>:</td>
                    <td>
                        
                        <select name="no_pesan" id="emp_no" onchange="showNo(this.value);">
                            <option value="-1">Pilih No Pesan</option>
                        <%
                            ResultSet nopes = null;
                            String no_pesan = null;
                            String tgl_pesan = null;
                            String total = null;
                            String kd_supp = null;
                            String kd_brg = null;
                            String qty_pesan = null;
                            String subtotal = null;
                            pemesanan pemesanan = new pemesanan(no_pesan, 
                            tgl_pesan,total,kd_supp,kd_brg,qty_pesan,subtotal);
                            
                            nopes = kon.stmt.executeQuery("SELECT * FROM pemesanan");
                            
                            while(nopes.next()){
                            pemesanan.setNo_pesan(nopes.getString("no_pesan"));    
                        %>
                        <option value="<%=pemesanan.getNo_pesan()%>"><%=pemesanan.getNo_pesan()%></option>
                        <% } %>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td>Sub Total Beli</td>
                    <td>:</td>
                    <td><input type="text" name="sub_beli" id="tot" readonly></td>
                </tr>
                
                <tr>
                    <td colspan="3"><input type="submit" value="Tambah" name="aksi"/></td>
                </tr>
            </table>
                
                <br>
                <br>
                
    <table border="1" width="535" align="center">
        <tr>
            <td colspan="5" ><h3 align="center">Data Barang Untuk Dibeli</h3></td>
        </tr>            
        
        <tr>
            <th align="center">Kode Barang</th>
            <th align="center">Quantity Beli</th>
            <th align="center">Sub Beli</th>
        </tr>
        
        <sql:query var="querysementara2" dataSource="${dataSource}">
        SELECT * FROM sementara2;
        </sql:query>
        
        <c:forEach var='rowbeli' items='${querysementara2.rows}'>
            <tr class='isi'>
                <td valign='top'>${rowbeli.kd_brg}</td>
                <td valign='top'>${rowbeli.qty_beli}</td>
                <td valign='top'>${rowbeli.sub_beli}</td>
            </tr>
        </c:forEach>
                
        <sql:query var="querysementaraaa" dataSource="${dataSource}">
        SELECT SUM(qty_beli) AS totbel , SUM(sub_beli) AS totsubbel FROM sementara2;
        </sql:query>
        
        <c:forEach var="rowbelii" items="${querysementaraaa.rows}">
            <table border="0">
                <tr>
                    <td>Total Barang Dibeli</td>
                    <td>:</td>
                    <td><input type="text" name="totbel" value="${rowbelii.totbel}" readonly/></td>
                </tr>
                
                <tr>
                    <td>Total Harga Barang Dibeli</td>
                    <td>:</td>
                    <td><input type="text" name="totsubbel" value="${rowbelii.totsubbel}" readonly/></td>
                </tr>
                
                <tr>
                <td colspan="3">
                    <input type="submit" value="Simpan" name="aksi"/>
                    <input type="submit" value="Batal" name="aksi"/>
                </td>
                </tr>
            </table>
        </c:forEach>
                
        <%kon.close();%>
    </table>
        </form>
    </body>
</html>
