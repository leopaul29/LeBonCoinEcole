/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Seb
 */
@Entity
public class Announcement implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String title;
    
    @OneToMany
    private List<Category> categories;
   
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String description;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
    
    private Date startDate;
    
    private Date endDate;
    
    private float price;
    
    @ManyToOne
    private Student student;
    
    private boolean isAnnoucement;

    public Announcement() { }
    
    public Announcement(boolean isAnnouncement, String title, String description) {
        this.title = title;
        this.description = description;
        this.startDate = new Date(System.currentTimeMillis());
        this.isAnnoucement = isAnnouncement;
    }
    
    public Announcement(boolean isAnnouncement, Student student, String title, String description,
            float price, List<Category> categories, byte[] image ) {
        this.student = student;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
        this.startDate = new Date(System.currentTimeMillis());
        this.categories = categories;
        this.isAnnoucement = isAnnouncement;
    }
    
    public Announcement(String title, String description, Student student) {
        this.title = title;
        this.description = description;
        this.student = student;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getStartDate() {
        return startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Student getStudent() {
        return student;
    }

    public void setUser(Student student) {
        this.student = student;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAnnoucement() {
        return isAnnoucement;
    }
    
    public String getUrl(){
        if( image == null || image.length == 0 ){
            return "//static.leboncoin.fr/img/no-picture.png";
        }
        
        // link : pour remplacer Base64
        // http://stackoverflow.com/questions/14413169/which-java-library-provides-base64-encoding-decoding
        return "data:image/png;base64," + DatatypeConverter.printBase64Binary(image);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Announcement)) {
            return false;
        }
        Announcement other = (Announcement) object;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "modele.Announcement[ id=" + id + " ]";
    }
    
}
