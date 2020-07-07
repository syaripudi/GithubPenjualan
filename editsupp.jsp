<h1>Edit Master Supplier</h1>
<form action="SupplierServlet" method="post" onsubmit="return validasi_inputSupplier(this)">
    <table border="0">
        <tr>
            <td>Kode Supplier</td>
            <td>:</td>
            <td><input readonly="readonly" type="text" name="kd_supp" value="${param.kd_supp}"/></td>
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
            <td>No. Telepon</td>
            <td>:</td>
            <td><input type="text" name="telpon" value="${param.telpon}"/></td>
        </tr>
        
        <tr>
            <td colspan="3"><input type="submit" name="aksi" value="Ganti"/></td>
        </tr>
    </table>
</form>
        
        