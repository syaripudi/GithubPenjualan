/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Windows 10
 */
public class pemesanan {
    String no_pesan;
    String kd_brg;
    String qty_pesan;
    String subtotal;
    String tgl_pesan;
    String total;
    String kd_supp;
    
//  Awal Constructor
    public pemesanan(String no_pesan, String kd_brg, String qty_pesan,
                     String subtotal, String tgl_pesan, 
                     String total, String kd_supp){
        this.no_pesan = no_pesan;
        this.kd_brg = kd_brg;
        this.qty_pesan = qty_pesan;
        this.subtotal = subtotal;
        this.tgl_pesan = tgl_pesan;
        this.total = total;
        this.kd_supp = kd_supp;
    }
//  Akhir Constructor

//  Statement Query untuk menambahkan data pemesanan sementara ke 
//  table sementara 
    public String toInsertSementara(){
        return "INSERT INTO sementara VALUES ('" + no_pesan +
                                             "','" + kd_brg +
                                             "','" + qty_pesan +
                                             "','" + subtotal + "')";
    }
    
//  Statement Query untuk menghapus data pemesanan sementara yang ada
//  didalam table sementara
    public String toDeleteSementaraKondisi(){
        return "DELETE FROM sementara WHERE kd_brg='" + kd_brg + "'";
    }
    
//  Statement Query untuk menambahkan data pemesanan ke table pemesanan
    public String toInsertPemesanan(){
        return "INSERT INTO pemesanan VALUES ('" + no_pesan +
                                             "','" + tgl_pesan +
                                             "','" + total +
                                             "','" + kd_supp + "')";
    }
    
//  Statement Query untuk menambahkan data pemesanan ke table 
//  pemesanan_constant ( nantinya table ini akan menampung seluruh 
//  data pemesanan yang tersedia
    public String toInsertPemesanan_constant(){
        return "INSERT INTO pemesanan_constant VALUES ('" + no_pesan +
                                             "','" + tgl_pesan +
                                             "','" + total +
                                             "','" + kd_supp + "')";
    }
    
//  Statement Query untuk menambahkan data dari table sementara
//  ke table detail_pesan
    public String toInsertDetail_pesan(){
        return "INSERT INTO detail_pesan SELECT * FROM sementara";
    }
    
//  Statement Query untuk menghapus seluruh data di table sementara
    public String toDeleteSementara(){
        return "TRUNCATE TABLE sementara";
    }
    
//  Awal Setter dan Getter
    public String getTgl_pesan() {
        return tgl_pesan;
    }

    public void setTgl_pesan(String tgl_pesan) {
        this.tgl_pesan = tgl_pesan;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getKd_supp() {
        return kd_supp;
    }

    public void setKd_supp(String kd_supp) {
        this.kd_supp = kd_supp;
    }

    public String getNo_pesan() {
        return no_pesan;
    }

    public void setNo_pesan(String no_pesan) {
        this.no_pesan = no_pesan;
    }

    public String getKd_brg() {
        return kd_brg;
    }

    public void setKd_brg(String kd_brg) {
        this.kd_brg = kd_brg;
    }

    public String getQty_pesan() {
        return qty_pesan;
    }

    public void setQty_pesan(String qty_pesan) {
        this.qty_pesan = qty_pesan;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
//  Akhir Setter dan Getter
    
}


