<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<sql:setDataSource var="dataSource"
                   driver="com.mysql.jdbc.Driver"
                   url="jdbc:mysql://localhost:3306/pembelian"
                   user="root" password=""/>


<!DCOTYPE html>
<html>
    <head>
        <title>Penjualan Baju</title>
        <link rel="stylesheet" type="text/css" href="config/style.css" media="screen">
        <link href="config/cssTable.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <div id="wrap">
            <div id="header">
                <h1><a>Toko Baju</a></h1><br>
                <h2>Jl. kehidupan cengkareng Jakarta Barat</h2>
                <h4>Telpon 021 89987887</h4>
            </div>
            
            <div id="menu">
                <ul>
                    <li><a href="beranda.jsp">Home</a>
                    <li><a href="">About</a></li>
                </ul>
            </div>
            
            <div id="content">
                <div class="left">
                    <h2>Data</h2>
                    <ul>
                        <li><a href="beranda.jsp?halaman=tampilbrg">Data Baju</a></li>
                        <li><a href="beranda.jsp?halaman=tampilsupp">Supplier</a></li>
                    </ul>
                    
                    <h2>Transaksi</h2>
                    <ul>
                        <li><a href="beranda.jsp?halaman=tampilpsn">Pemesanan</a></li>
                        <li><a href="beranda.jsp?halaman=tampilpem">Pembelian</a></li>
                        <li><a href="beranda.jsp?halaman=tampiljur">Jurnal</a></li>
                    </ul>
                </div>
                <div class="right">
                
                    <table width="100%">
                        <tr> 
                            <td valign="top" width="110%"> 
                    <c:choose>
                        <c:when test="${param.halaman=='tampilbrg'}">
                            <%@include file="barang.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='editbrg'}">
                            <%@include file="editbrg.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='tampilsupp'}">
                            <%@include file="supplier.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='editsupp'}">
                            <%@include file="editsupp.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='tampilakun'}">
                            <%@include file="akun.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='editakun'}">
                            <%@include file="editakun.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='tampiluser'}">
                            <%@include file="user.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='edituser'}">
                            <%@include file="edituser.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='tampilpsn'}">
                            <%@include file="pemesanan.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='tampilpem'}">
                            <%@include file="pembelian.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='tampilret'}">
                            <%@include file="returpembelian.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='tampiljur'}">
                            <%@include file="jurnal.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='cetakbarang'}">
                            <%@include file="CetakLaporanBarang.jsp" %>
                        </c:when>
                        
                        <c:when test="${param.halaman=='cetakpembelian'}">
                            <%@include file="CetakLaporanPembelian.jsp" %>
                        </c:when>
                        
                        <c:otherwise>
                            <%@include file="home.jsp" %>
                        </c:otherwise>
                         
                    </c:choose>
                            </td>
                        </tr>
                    </table>
                    <div class="footer">
                        support
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>