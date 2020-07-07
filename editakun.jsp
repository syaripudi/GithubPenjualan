<h1>Edit Master Akun</h1>
<form action="AkunServlet" method="post" onsubmit="return validasi_inputAkun(this)">
    <table border="0">
        <tr>
            <td>No Akun</td>
            <td>:</td>
            <td><input readonly="readonly" type="text" name="no_akun" value="${param.no_akun}"/></td>
        </tr>
        
        <tr>
            <td>Nama Akun</td>
            <td>:</td>
            <td><input type="text" name="nm_akun" value="${param.nm_akun}"/></td>
        </tr>
        
        <tr>
            <td colspan="3"><input type="submit" name="aksi" value="Ganti"/></td>
        </tr>
    </table>
</form>
        
        