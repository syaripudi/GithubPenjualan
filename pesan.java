/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.*;
/**
 *
 * @author Windows 10
 */

@WebServlet(name = "pesan" , urlPatterns = {"/pesan"})
public class pesan extends HttpServlet{
    
    //Method untuk menampilkan pesan input barang
    public String proses(String SQL, String URL, String status)
            throws ClassNotFoundException, SQLException {
        koneksi kon = new koneksi();
        PreparedStatement pstmt2 = null;
        kon.stmt.executeUpdate(SQL);
        kon.close();
        String result = "<script>"
                + "alert ('Data Barang berhasil di " + status + "');"
                +"window.location='"+ URL + "';"
                +"</script>";
        return result;
    }
    
    //  Method untuk menampilkan pesan input supplier
    public String proses2(String SQL, String URL, String status)
            throws ClassNotFoundException, SQLException {
        koneksi kon = new koneksi();
        kon.stmt.executeUpdate(SQL);
        kon.close();
        String result = "<script>"
                + "alert ('Data Supplier berhasil di " + status + "');"
                +"window.location='"+ URL + "';"
                +"</script>";
        return result;
    }
    
    //  Method untuk menampilkan pesan input akun
    public String proses3(String SQL, String URL, String status)
            throws ClassNotFoundException, SQLException {
        koneksi kon = new koneksi();
        kon.stmt.executeUpdate(SQL);
        kon.close();
        String result = "<script>"
                + "alert ('Data Akun berhasil di " + status + "');"
                +"window.location='"+ URL + "';"
                +"</script>";
        return result;
    }
    
    //  Method untuk menampilkan pesan input user
    public String proses4(String SQL, String URL, String status)
            throws ClassNotFoundException, SQLException {
        koneksi kon = new koneksi();
        kon.stmt.executeUpdate(SQL);
        kon.close();
        String result = "<script>"
                + "alert ('Data User berhasil di " + status + "');"
                +"window.location='"+ URL + "';"
                +"</script>";
        return result;
    }
    
    //  Method untuk menampilkan pesan input pemesanan
    public String proses5(String SQL, String URL, String status)
            throws ClassNotFoundException, SQLException {
        koneksi kon = new koneksi();
        kon.stmt.executeUpdate(SQL);
        kon.close();
        String result = "<script>"
                + "alert ('Data Pemesanan Barang berhasil di " + status + "');"
                +"window.location='"+ URL + "';"
                +"</script>";
        return result;
    }
    
    //  Method untuk menampilkan pesan input pembelian
    public String proses6(String SQL, String URL, String status)
            throws ClassNotFoundException, SQLException {
       koneksi kon = new koneksi();
       kon.stmt.executeUpdate(SQL);
       kon.close();
       String result = "<script>"
       + "alert ('Data Pembelian Sementara Barang berhasil di " + status + "');"
       +"window.location='"+ URL + "';"
       +"</script>";
       return result;
    }
    
    //  Method untuk menampilkan pesan input retur pembelian
    public String proses7(String SQL, String URL, String status)
            throws ClassNotFoundException, SQLException {
        koneksi kon = new koneksi();
        kon.stmt.executeUpdate(SQL);
        kon.close();
        String result = "<script>"
        + "alert ('Data Retur Sementara Barang berhasil di " + status + "');"
        +"window.location='"+ URL + "';"
        +"</script>";
        return result;
    }
    
}



