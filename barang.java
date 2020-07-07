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
public class barang {
    String kd_brg;
    String nm_brg;
    String harga;
    String stok;
    
//  Awal Constructor ( Wajib Ada )
    public barang(String kd_brg, String nm_brg, String harga, String stok)
    {
        this.kd_brg = kd_brg;
        this.nm_brg = nm_brg;
        this.harga = harga;
        this.stok = stok;
    }
//  Akhir Constructor ( Wajib Ada )
    
//  Statement Query untuk menampilkan seluruh data di table barang
    public String toSelectBarang(){
        return "SELECT kd_brg, nm_brg FROM barang";
    }
    
//  Statement Query untuk menambahkan data ke table barang
    public String toInsertBarang(){
        return "INSERT INTO barang VALUES ('" + kd_brg +
                                          "','" + nm_brg +
                                          "','" + harga +
                                          "','" + stok + "')";
    }
    
//  Statement Query untuk menghapus data di table barang
    public String toDeleteBarang(){
        return "DELETE FROM barang WHERE kd_brg='" + kd_brg + "'";
    }
    
//  Statement Query untuk menubah data di table barang
    public String toUpdateBarang(){
        return "UPDATE barang SET nm_brg='" + nm_brg +
                                  "', harga=" + harga +
                                  ", stok=" + stok +
                                  " WHERE kd_brg='" + kd_brg + "'";
                                  
    }

//  Awal Setter dan Getter
    public String getKd_brg() {
        return kd_brg;
    }

    public void setKd_brg(String kd_brg) {
        this.kd_brg = kd_brg;
    }

    public String getNm_brg() {
        return nm_brg;
    }

    public void setNm_brg(String nm_brg) {
        this.nm_brg = nm_brg;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }
//  Akhir Setter dan Getter
    
}
