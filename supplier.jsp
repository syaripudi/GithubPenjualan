<!--2 Import ini ngga butuh , iseng aja-->
<%@page import="control.koneksi"%>
<%@page import="java.sql.*"%>
<h1>Data Master Supplier</h1>
<form action="SupplierServlet" method="post" onsubmit="return validasi_inputSupplier(this)">
    <table border="0">
        <tr>
            <td>Kode Supplier</td>
            <td>:</td>
            <td><input type="text" name="kd_supp" value="${param.kd_supp}"/></td>
        </tr>
        
        <tr>
            <td>Nama Supplier</td>
            <td>:</td>
            <td><input type="text" name="nm_supp" value="${param.nm_supp}"/></td>
        </tr>
        
        <tr>
            <td>Alamat</td>
            <td>:</td>
            <td><input type="text" name="alamat" value="${param.alamat}"/></td>
        </tr>
        
        <tr>
            <td>No Telepon</td>
            <td>:</td>
            <td><input type="text" name="telpon" value="${param.telpon}"/></td>
        </tr>
        
        <tr>
            <td colspan="3"><input type="submit" name="aksi" value="Simpan"/></td>
        </tr>
    </table>
</form>
        
<form action="" method="post">  
    <table border="0">
        <td>Cari Data Supplier</td>
        <td>:</td>
        <td><input type="text" name="cari" value="${param.cari}"/></td>
    </table>
</form>
    
    <!--    Cara kerja perintah ini sama seperti Result Set, hanya saja
    mengolah datanya menggunakan sebuah tag -->
    <sql:query var="querysupplier" dataSource="${dataSource}">
        SELECT * FROM supplier WHERE kd_supp LIKE '%${param.cari}%' OR
        nm_supp LIKE '%${param.cari}%';
    </sql:query>
    
    <br>
    
    <table border='1' width='100%' cellpadding='0' cellspacing='0'>
        <tr class='head' align='center'>
            <th>Kode Supplier</th>
            <th>Nama Supplier</th>
            <th>Alamat</th>
            <th>No. Telepon</th>
            <th>Action</th>
        </tr>
        
        <!--Ini adalah perulangan untuk menampilkan data dari query tadi-->
        <c:forEach var='rowsupp' items='${querysupplier.rows}'>
            <tr class='isi'>
                <td valign='top'>${rowsupp.kd_supp}</td>
                <td valign='top'>${rowsupp.nm_supp}</td>
                <td valign='top'>${rowsupp.alamat}</td>
                <td valign='top'>${rowsupp.telpon}</td>
                <td valign='top'>
                    <a href='SupplierServlet?aksi=Hapus&kd_supp=${rowsupp.kd_supp}'> Hapus</a> ||
                    <a href="beranda.jsp?halaman=editsupp&kd_supp=${rowsupp.kd_supp}
                       &nm_supp=${rowsupp.nm_supp}
                       &alamat=${rowsupp.alamat}
                       &telpon=${rowsupp.telpon}"> Edit</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    
    
                