<!--2 Import ini ngga butuh , iseng aja-->
<%@page import="control.koneksi"%>
<%@page import="java.sql.*"%>
<h1>Data Master Barang</h1>

<form action="BarangServlet" method="post" onsubmit="return validasi_inputBarang(this)">
    
    <table border="0">
        
        <tr>
            <td>Kode Barang</td>
            <td>:</td>
            <td><input type="text" name="kd_brg" value="${param.kd_brg}"/></td>
        </tr>
        
        <tr>
            <td>Nama Barang</td>
            <td>:</td>
            <td><input type="text" name="nm_brg" value="${param.nm_brg}"/></td>
        </tr>
        
        <tr>
            <td>Harga Barang</td>
            <td>:</td>
            <td><input type="text" name="harga" value="${param.harga}" onkeypress="return hanyaAngka(event)"/></td>
        </tr>
        
        <tr>
            <td>Stok Barang</td>
            <td>:</td>
            <td><input type="text" name="stok" value="${param.stok}" onkeypress="return hanyaAngka(event)"/></td>
        </tr>
        
        <tr>
            <td colspan="3"><input type="submit" name="aksi" value="Simpan"/></td>
        </tr>
        
    </table>
        
</form>
        
<form action="" method="post">  
    
    <table border="0">
        <td>Cari Data Barang</td>
        <td>:</td>
        <td><input type="text" name="cari" value="${param.cari}"/></td>
    </table>
    
</form>
<!--    Cara kerja perintah ini sama seperti Result Set, hanya saja
    mengolah datanya menggunakan sebuah tag -->
    <sql:query var="querybarang" dataSource="${dataSource}">
        SELECT * FROM barang WHERE kd_brg LIKE '%${param.cari}%' OR
        nm_brg LIKE '%${param.cari}%';
    </sql:query>
    
    <br>
    
    <table border='1' width='100%' cellpadding='0' cellspacing='0'>
        
        <tr class='head' align='center'>
            <th>Kode Barang</th>
            <th>Nama Barang</th>
            <th>Harga Barang</th>
            <th>Stok Barang</th>
            <th>Action</th>
        </tr>
        
        <!--Ini adalah perulangan untuk menampilkan data dari query tadi-->
        <c:forEach var='rowbrg' items='${querybarang.rows}'>
            <tr class='isi'>
                <td valign='top'>${rowbrg.kd_brg}</td>
                <td valign='top'>${rowbrg.nm_brg}</td>
                <td valign='top'>${rowbrg.harga}</td>
                <td valign='top'>${rowbrg.stok}</td>
                <td valign='top'>
                    <a href='BarangServlet?aksi=Hapus&kd_brg=${rowbrg.kd_brg}'> Hapus</a> ||
                    <a href="beranda.jsp?halaman=editbrg&kd_brg=${rowbrg.kd_brg}
                       &nm_brg=${rowbrg.nm_brg}
                       &harga=${rowbrg.harga}
                       &stok=${rowbrg.stok}"> Edit</a>
                </td>
            </tr>
        </c:forEach>
                
    </table>
    
    
                
                