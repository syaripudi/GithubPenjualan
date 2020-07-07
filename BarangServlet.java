/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
//Import untuk menghubungkan servlet dan sql jika dibutuhkan
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
//Import untuk mendapatkan getter dan setter dari barang dan retur
//Jika dibutuhkan
import model.barang;
import model.retur;
/**
 *
 * @author Windows 10
 */
public class BarangServlet extends pesan {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
//  Method untuk menerima proses yang dikirimkan dari Form input Barang
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
//         Mendapatkan nilai value aksi dari button simpan
           String aksi = request.getParameter("aksi");
//         Mendapatkan semua value dari data form input barang
           String kd_brg = request.getParameter("kd_brg");
           String nm_brg = request.getParameter("nm_brg");
           String harga = request.getParameter("harga");
           String stok = request.getParameter("stok");
           
//         Instansiasi objek barang ( var )
           barang var = new barang(kd_brg,nm_brg,harga,stok);
//         Ini nyasar, harusnya punya ReturServlet2 cuma males ganti
           String no_retur = request.getParameter("no_retur");
           String tgl_retur = null;
           String no_beli = null;
           String qty_retur = null;
           String sub_retur = null;
           String total_retur = null; 
           retur vars = new retur(no_retur,tgl_retur,no_beli,kd_brg,qty_retur,sub_retur,total_retur);
//         URL untuk redirect ke halaman tampilbrg
           String URL = "beranda.jsp?halaman=tampilbrg";
//         URL2 untuk redirect ke halaman tampilret
           String URL2 = "beranda.jsp?halaman=tampilret";
           
//         Jika aksi tidak sama dengan null maka jalankan aksinya
           if(aksi != null)
           {
//            Biar lebih gampang diliat pake kondisi switch
              switch(aksi)
              {
                    case "Simpan":
//                  Proses untuk simpan barang
                    out.print(super.proses(var.toInsertBarang(),URL,"Simpan"));
                    break;
                    
                    case "Hapus":
//                  Proses untuk hapus barang
                    out.print(super.proses(var.toDeleteBarang(),URL,"Hapus"));    
                    break;
                    
                    case "Hapus2":
//                  Proses untuk hapus sementara3
                    out.print(super.proses7(vars.toDeleteSementara3Kondisi(),URL2,"Hapus"));
                    break;
                    
                    case "Ganti":
//                  Proses untuk ubah data barang
                    out.print(super.proses(var.toUpdateBarang(),URL,"Ganti"));    
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
            Logger.getLogger(BarangServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BarangServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BarangServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BarangServlet.class.getName()).log(Level.SEVERE, null, ex);
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

//Jangan tambah apapun lagi kalau servletnya ngga mau error!!!


