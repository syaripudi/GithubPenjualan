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
public class supplier {
    String kd_supp;
    String nm_supp;
    String alamat;
    String telpon;
    
//  Awal Constructor
    public supplier(String kd_supp, String nm_supp, 
                    String alamat, String telpon)
    {
        this.kd_supp = kd_supp;
        this.nm_supp = nm_supp;
        this.alamat = alamat;
        this.telpon = telpon;
    }
//  Akhir Constructor
    
//  Statement Query untuk menambahkan data ke table supplier
    public String toInsertSupplier(){
        return "INSERT INTO supplier VALUES ('" + kd_supp +
                                             "','" + nm_supp +
                                             "','" + alamat +
                                             "','" + telpon + "')";
    }
    
//  Statement Query untuk menghapus data di table supplier
    public String toDeleteSupplier()
    {
        return "DELETE FROM supplier WHERE kd_supp='" + kd_supp + "'";
    }
    
//  Statement Query untuk mengubah data di table supplier
    public String toUpdateSupplier()
    {
        return "UPDATE supplier SET nm_supp='" + nm_supp +
                                "', alamat='" + alamat +
                                "', telpon='" + telpon +
                                "' WHERE kd_supp='" + kd_supp + "'";
    }
    
//  Awal Setter dan Getter
    public String getKd_supp() {
        return kd_supp;
    }

    public void setKd_supp(String kd_supp) {
        this.kd_supp = kd_supp;
    }

    public String getNm_supp() {
        return nm_supp;
    }

    public void setNm_supp(String nm_supp) {
        this.nm_supp = nm_supp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }
//  Akhir Setter dan Getter
    
}

