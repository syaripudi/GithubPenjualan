/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
//Library yang dibutuhkan untuk menjalankan SQL dan Servlet
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
//Library yang dibutuhkan untuk instansiasi pemesanan
import model.pemesanan;
/**
 *
 * @author Windows 10
 */

public class PemesananServlet extends pesan {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
//          Instansiasi koneksi baru
            koneksi kon = new koneksi();
//          aksi akan ada jika tombol simpan / tambah di klik
            String aksi = request.getParameter("aksi");
//          seluruh data yang ditangkap dari form input pemesanan.jsp
            String no_pesan = request.getParameter("no_pesan");
            String kd_brg = request.getParameter("kd_brg");
            String qty_pesan = request.getParameter("qty_pesan");
            String subtotal = request.getParameter("subtotal");
            String tgl_pesan = request.getParameter("tgl_pesan");
            String total = request.getParameter("totsub");
            String kd_supp = request.getParameter("kd_supp");
            String harga = request.getParameter("harga");
            
//          Instansiasi objek pemesanan
            pemesanan var = new pemesanan(no_pesan,kd_brg,qty_pesan,subtotal,tgl_pesan,total,kd_supp);
//          URL untuk redirect kembali ke halaman pemesanan.jsp bila berhasil melakukan
//          satu atau beberapa aksi yang disajikan
            String URL = "beranda.jsp?halaman=tampilpsn";
            
//          Jika aksi ada maka akan masuk ke tahap ini
            if(aksi != null)
            {
//              Biasa ini biar rapi aja
                switch(aksi)
                {
//                  Beberapa value dari data aksi yang akan memiliki fungsi berbeda-beda
                    case "Tambah":
//                      Kondisi jika harga dan kode barang kosong maka akan masuk ke if
                        if(kd_brg.equalsIgnoreCase("-1") || harga.equalsIgnoreCase("null")){
//                          Menampilkan pesan dan mengirim kembali ke halaman pemesanan.jsp
                            out.print("<script>"
                            + "alert('Mohon Isi Barang Dan Harga Barang');"
                            + "window.location='beranda.jsp?halaman=tampilpsn';"
                            + "</script>");
//                      Kondisi jika harga dan kode barang ada maka akan masuk ke else
                        }else {
//                          Fungsi untuk menambahkan data yang ditambah ke table sementara
                            out.print(super.proses5(var.toInsertSementara(),URL,"Tambah"));
                        }

                    break;
                    
                    case "Hapus":
//                      Fungsi untuk menghapus data barang yang ada di table sementara
                        out.print(super.proses5(var.toDeleteSementaraKondisi(),URL,"Hapus"));
                    break;
                    
                    case "Batal":
//                      Fungsi untuk menmbatalkan ( menghapus ) seluruh data di table sementara
                        out.print(super.proses5(var.toDeleteSementara(),URL,"Batal"));
                    break;
                    
                    case "Simpan":
//                      Deklarasi statement persiapan tiap query untuk digunakan
                        PreparedStatement pstmt1 = null;
                        PreparedStatement pstmt2 = null;
                        PreparedStatement pstmt3 = null;
                        PreparedStatement pstmt4 = null;
                        
//                      persiapan query untuk memasukkan data ke dalam table pemesanan
                        pstmt1 = kon.conn.prepareStatement(var.toInsertPemesanan());
                        
//                      persiapan query untuk memasukkan data ke dalam table pemesanan_constant
//                      hal ini dilakukan agar nomor otomatis dalam pemesanan selalu naik 
//                      meskipun nomor pesan telah dihapus di table pemesanan karena telah dilakukan
//                      dalam transaksi pembelian
                        pstmt4 = kon.conn.prepareStatement(var.toInsertPemesanan_constant());
                        
//                      persiapan query untuk memasukkan data ke table detail_pesan 
                        pstmt2 = kon.conn.prepareStatement(var.toInsertDetail_pesan());
                        
//                      persiapan query untuk membersihkan seluruh data di table sementara
                        pstmt3 = kon.conn.prepareStatement(var.toDeleteSementara());
                        
//                      Mengeksekusi seluruh preparestatement yang ada ( berjumlah 4 statement )
                        pstmt1.executeUpdate();
                        pstmt4.executeUpdate();
                        pstmt2.executeUpdate();
                        pstmt3.executeUpdate();
                        
//                      menampilkan pesan data berhasil disimpan dan dibalikkan ke pemesanan.jsp
                        out.print("<script>"
                        + "alert('Data Pemesanan Anda Berhasil Disimpan');"
                        + "document.location.href='beranda.jsp?halaman=tampilpsn';"
                        + "</script>");
                        
                    break;
                }
            }
        }
    }

    //   Ini sangat wajib untuk diperbaiki!!! 
    //   Caranya klik kotak logo tambah itu terus 
    //   klik lampu errornya dan pilih surround try catch statement
    //   Harusnya ada 2 lampu error yang perlu dibenarkan
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PemesananServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PemesananServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PemesananServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PemesananServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

//Jangan tambahkan apapun lagi di kodenya


