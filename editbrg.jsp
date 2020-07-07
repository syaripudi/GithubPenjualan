<h1>Edit Master Barang</h1>
<form action="BarangServlet" method="post" onsubmit="return validasi_inputBarang(this)">
    <table border="0">
        <tr>
            <td>Kode Barang</td>
            <td>:</td>
            <td><input readonly="readonly" type="text" name="kd_brg" value="${param.kd_brg}"/></td>
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
            <td colspan="3"><input type="submit" name="aksi" value="Ganti"/></td>
        </tr>
    </table>
</form>
        
        