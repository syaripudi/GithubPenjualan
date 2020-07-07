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
public class pembelian {
    String no_pesan;
    String no_beli;
    String tgl_beli;
    String no_faktur;
    String total_beli;
    
//  Awal Constructor
    public pembelian(String no_pesan, String no_beli, String tgl_beli,
                     String no_faktur, String total_beli){
        this.no_pesan = no_pesan;
        this.no_beli = no_beli;
        this.tgl_beli = tgl_beli;
        this.no_faktur = no_faktur;
        this.total_beli = total_beli;
    }
//  Akhir Constructor
    
//  Statement Query untuk menampilkan no_beli dari table pembelian
    public String toSelectPembelian(){
        return "SELECT no_beli FROM pembelian";
    }
    
//  Statement Query untuk menambahkan data dari table detail_pesan 
//  berdasarkan no_pesan tertentu kedalam table sementara2 
    public String toInsertSementara2(){
        return "INSERT INTO sementara2 SELECT * FROM detail_pesan"
                + " WHERE no_pesan='" + no_pesan + "'";
    }
    
//  Statement Query untuk menghapus seluruh data di table sementara2
    public String toDeleteSementara2(){
        return "TRUNCATE TABLE sementara2";
    }
    
//  Statement Query untuk menambahkan data ke table pembelian
    public String toInsertPembelian(){
        return "INSERT INTO pembelian VALUES ('" + no_beli +
                                             "','" + tgl_beli +
                                             "','" + no_faktur +
                                             "','" + total_beli + "')";
    }
    
//  Statement Query untuk menambahkan data ke table detail_pembelian
//  dari table sementara2 
    public String toInsertDetailPembelian(){
        return "INSERT INTO detail_pembelian(no_pesan,kd_brg,qty_beli,sub_beli)"
                + " SELECT * FROM sementara2";
    }
    
//  Statement Query untuk mengubah no_beli yang ada di table 
//  detail_pembelian, hal ini dikarenakan method toInsertDetailPembelian()
//  tidak ikut menambahkan no_beli 
    public String toUpdateDetailPembelian(){
        return "UPDATE detail_pembelian SET no_beli='" + no_beli + "' ";
    }
    
//  Statement Query untuk menghapus data dari table pemesanan ( hal ini
//  akan berlaku jika no_pesan telah digunakan untuk pembelian sehingga
//  kedepannya no_pesan itu tidak akan bisa digunakan lagi ( menghilang )
    public String toDeletePemesananKondisi(){
        return "DELETE FROM pemesanan ";
    }
    
//  Statement Query untuk menghapus data dari table detail_pemesanan ( hal ini
//  sama seperti method toDeletePemesananKondisi()
    public String toDeleteDetail_pesanKondisi(){
        return "DELETE FROM detail_pesan ";
    }
    
//  Statement Query untuk mendapatkan no_beli yang ada di sementara2,
//  query ini berguna untuk method toUpdateDetailPembelian()
    public String toReadNoPesan(){
        return "SELECT DISTINCT(no_beli) FROM sementara2";
    }
    
//  Statement Query untuk mendapatkan jumlah baris yang ada di sementara2,
//  query ini juga berguna untuk method toUpdateDetailPembelian()
//  karena akan dilakukan sistem looping pada query toUpdateDetailPembelian()
    public String toCountNoPesan(){
        return "SELECT COUNT(DISTINCT no_beli) FROM sementara2";
    }
    
//  Statement Query untuk mengecek apakah nomor pesan yang dimasukkan sudah
//  pernah dimasukkan atau belum ( berguna ketika memasukkan no_pesan di
//  form pembelian )
    public String toCheckSementara2Kondisi(){
        return "SELECT COUNT(*) FROM sementara2 "
                + " WHERE no_beli='" + no_pesan + "'";
    }
    
//  Statement Query untuk mendapatkan jumlah pengulangan yang akan digunakan
//  pada method toUpdateStokBarang()
    public String toCountKodeBarang(){
        return "SELECT COUNT(DISTINCT kd_brg) FROM sementara2";
    }
    
//  Statement Query untuk mendapatkan seluruh kodebarang dan masing-masing
//  quantitas beli yang nanti nya akan dijadikan perulangan di
//  method toUpdateStokBarang()
    public String toReadKodeBarang(){
        return "SELECT kd_brg, SUM(qty_beli) FROM sementara2 GROUP BY kd_brg";
    }
    
//  Statement Query utuk mengubah stok persediaan bertambah berdasarkan data
//  yang dikirimkan oleh method toReadKodeBarang()
    public String toUpdateStokBarang(){
        return "UPDATE barang SET stok = CASE ";
    }

//  Awal Setter dan Getter
    public String getNo_pesan() {
        return no_pesan;
    }

    public void setNo_pesan(String no_pesan) {
        this.no_pesan = no_pesan;
    }

    public String getNo_beli() {
        return no_beli;
    }

    public void setNo_beli(String no_beli) {
        this.no_beli = no_beli;
    }

    public String getTgl_beli() {
        return tgl_beli;
    }

    public void setTgl_beli(String tgl_beli) {
        this.tgl_beli = tgl_beli;
    }

    public String getNo_faktur() {
        return no_faktur;
    }

    public void setNo_faktur(String no_faktur) {
        this.no_faktur = no_faktur;
    }

    public String getTotal_beli() {
        return total_beli;
    }

    public void setTotal_beli(String total_beli) {
        this.total_beli = total_beli;
    }
//  Akhir Setter dan Getter
    
}



