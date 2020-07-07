 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

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
import model.pembelian;
import control.koneksi;
import java.sql.*;
/**
 *
 * @author Windows 10
 */
public class PembelianServlet extends pesan {

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
            String aksi = request.getParameter("aksi");
            
            String no_pesan = request.getParameter("no_pesan");
            String no_beli = request.getParameter("no_beli");
            String tgl_beli = request.getParameter("tgl_beli");
            String no_faktur = request.getParameter("no_faktur");
            String total_beli = request.getParameter("totsubbel");
            
            String URL = "beranda.jsp?halaman=tampilpem";
            pembelian var = new pembelian(no_pesan,no_beli,tgl_beli,no_faktur,total_beli);
            
            ResultSet CekNoPesan = null;
            koneksi kon = new koneksi();
            int kondisiNoPesan = 0;
            
            if(aksi != null)
            {
                switch(aksi)
                {
                    case "Tambah":
                        CekNoPesan = kon.stmt.executeQuery(var.toCheckSementara2Kondisi());
                        CekNoPesan.first();
                        
                        kondisiNoPesan = CekNoPesan.getInt(1);
                        if(no_pesan.equalsIgnoreCase("-1")){
                            out.print("<script>"
                            + "alert('Maaf , Tolong Isi Nomor Pesan Anda!');"
                            + "document.location.href='beranda.jsp?halaman=tampilpem';"
                            + "</script>"); 
                        }else{
                            if(kondisiNoPesan > 0){
                                out.print("<script>"
                                + "alert('Maaf No Pesan Yang Anda Masukkan Sudah Terdaftar Sementara!');"
                                + "document.location.href='beranda.jsp?halaman=tampilpem';"
                                + "</script>"); 
                            } else {
                                out.print(super.proses6(var.toInsertSementara2(),URL,"Tambah"));
                            }
                        }
                        

                    break;
                    
                    case "Batal":
                        out.print(super.proses6(var.toDeleteSementara2(),URL,"Batal"));
                    break;
                    
                    case "Simpan":
                        PreparedStatement insertPembelian = null;
                        PreparedStatement insertDetailPembelian = null;
                        PreparedStatement updateLengkapDetailPembelian = null;
                        PreparedStatement deleteSementara2 = null;
                        PreparedStatement deletePemesananKondisi = null;
                        PreparedStatement deleteDetailPesanKondisi = null;
                        
//                      Query Untuk Menyimpan Langsung Ke Data Pembelian
                        insertPembelian = kon.conn.prepareStatement(var.toInsertPembelian());
                        
//                      Query Untuk Mempersiapkan Data Diinput Ke Data Detail Pembelian
                        
//                      Query Detail Pembelian 1.1 ( Memasukkan 3/4 Data Ke Dalam Table Detail Pembelian
                        insertDetailPembelian = kon.conn.prepareStatement(var.toInsertDetailPembelian());
                        
//                      Query Detail Pembelian 1.2 ( Membaca Seluruh No Pesan Yang Ada Di Table Sementara2
                        ResultSet readNoPesan = null;
                        ResultSet countNoPesan = null;
                        String noPesan = null;
                        String noPesanQuery = null;
                        String noPesanAkhir = null;
                        int nomor = 0;
                        int i = 0;
                        countNoPesan = kon.stmt.executeQuery(var.toCountNoPesan());
                        countNoPesan.last();
                        nomor = countNoPesan.getInt(1);
                        readNoPesan = kon.stmt.executeQuery(var.toReadNoPesan());
                        readNoPesan.next();         
                    
                        for(i = 0; i < nomor; i++){
                            noPesanQuery = readNoPesan.getString(1);
                            noPesan = noPesan + " no_pesan='" + noPesanQuery + "' OR ";
                            readNoPesan.next();   
                        }
                    
                        noPesanAkhir = noPesan.replaceAll("null","");
                        noPesanAkhir = " WHERE " + noPesanAkhir + " no_pesan='null'";
                        
//                      Query Detail Pembelian 1.3 ( Memasukkan 1/4 Data Ke Dalam Table Detail Pembelian
                        updateLengkapDetailPembelian = kon.conn.prepareStatement(var.toUpdateDetailPembelian() + noPesanAkhir);
                        
//                      Query Menghapus Pemesanan Yang Sudah Dilakukan Pada Table Pemesanan
                        deletePemesananKondisi = kon.conn.prepareStatement(var.toDeletePemesananKondisi() + noPesanAkhir);
                        
//                      Query Menghapus Detail Pesan Yang SUdah Dilakukan Pada Table Detail Pesan
                        deleteDetailPesanKondisi = kon.conn.prepareStatement(var.toDeleteDetail_pesanKondisi() + noPesanAkhir);
                        
//                      Query Membaca Seluruh Kd barang dan qty beli untuk di update ke Table barang nanti
                        PreparedStatement updateStokBarang = null;
                        ResultSet countKodeBarang = null;
                        ResultSet readKodeBarang = null;
                        String kodeBarang = null;
                        String kondisiKodeBarang = null;
                        String kodeBarangQuery = null;
                        String qtyBeliQuery = null;
                        String kondisiKodeBarangSementara = null;
                        String kodeBarangSementara = null;
                        String hasilAkhirKodeBarang = null;
                        int nomorkode = 0;
                        countKodeBarang = kon.stmt.executeQuery(var.toCountKodeBarang());
                        countKodeBarang.last();
                        nomorkode = countKodeBarang.getInt(1);
                        readKodeBarang = kon.stmt.executeQuery(var.toReadKodeBarang());
                        readKodeBarang.next();
                        
                        for(int a = 0; a < nomorkode; a++){
                            kodeBarangQuery = readKodeBarang.getString(1);
                            qtyBeliQuery = readKodeBarang.getString(2);
                            kodeBarang = kodeBarang + " WHEN kd_brg='" + kodeBarangQuery + "' THEN stok + " + qtyBeliQuery + " ";
                            kondisiKodeBarang = kondisiKodeBarang + "'" + kodeBarangQuery + "' ,";
                            readKodeBarang.next();
                        }
                        kondisiKodeBarangSementara = kondisiKodeBarang.replaceAll("null"," ");
                        kodeBarangSementara = kodeBarang.replaceAll("null"," ");
                       
                        hasilAkhirKodeBarang = kodeBarangSementara + " END WHERE kd_brg IN(" + kondisiKodeBarangSementara + "akatsuki)";
                        hasilAkhirKodeBarang = hasilAkhirKodeBarang.replaceAll(",akatsuki","");
                        updateStokBarang = kon.conn.prepareStatement(var.toUpdateStokBarang() + hasilAkhirKodeBarang);
                        
                        
                        
//                      Query Membersihkan table sementara2 
                        deleteSementara2 = kon.conn.prepareStatement(var.toDeleteSementara2());
                        
//                      Mengekseskusi seluruh query
                        insertPembelian.executeUpdate();
                        insertDetailPembelian.executeUpdate();
                        updateLengkapDetailPembelian.executeUpdate();
                        deletePemesananKondisi.executeUpdate();
                        deleteDetailPesanKondisi.executeUpdate();
                        updateStokBarang.executeUpdate();
                        deleteSementara2.executeUpdate();

                        
                        out.print("<script>"
                        + "alert('Data Pembelian Berhasil Dimasukkan!');"
                        + "document.location.href='beranda.jsp?halaman=tampilpem';"
                        + "</script>"); 
                        
                    break;
                }
            }
        }
    }

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
            Logger.getLogger(PembelianServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PembelianServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PembelianServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PembelianServlet.class.getName()).log(Level.SEVERE, null, ex);
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
