/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthprac;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "ORDERS", catalog = "", schema = "LUKE")
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrderid", query = "SELECT o FROM Orders o WHERE o.orderid = :orderid"),
    @NamedQuery(name = "Orders.findByDay", query = "SELECT o FROM Orders o WHERE o.day = :day"),
    @NamedQuery(name = "Orders.findByAccid", query = "SELECT o FROM Orders o WHERE o.accid = :accid"),
    @NamedQuery(name = "Orders.findByNumfp", query = "SELECT o FROM Orders o WHERE o.numfp = :numfp"),
    @NamedQuery(name = "Orders.findByNumhp", query = "SELECT o FROM Orders o WHERE o.numhp = :numhp"),
    @NamedQuery(name = "Orders.findByNumm", query = "SELECT o FROM Orders o WHERE o.numm = :numm"),
    @NamedQuery(name = "Orders.findByNumsr", query = "SELECT o FROM Orders o WHERE o.numsr = :numsr"),
    @NamedQuery(name = "Orders.findByNumss", query = "SELECT o FROM Orders o WHERE o.numss = :numss"),
    @NamedQuery(name = "Orders.findByNumj", query = "SELECT o FROM Orders o WHERE o.numj = :numj")})
public class Orders implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ORDERID")
    private Integer orderid;
    @Column(name = "DAY")
    private String day;
    @Column(name = "ACCID")
    private String accid;
    @Column(name = "NUMFP")
    private Integer numfp;
    @Column(name = "NUMHP")
    private Integer numhp;
    @Column(name = "NUMM")
    private Integer numm;
    @Column(name = "NUMSR")
    private Integer numsr;
    @Column(name = "NUMSS")
    private Integer numss;
    @Column(name = "NUMJ")
    private Integer numj;

    public Orders() {
    }

    public Orders(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        Integer oldOrderid = this.orderid;
        this.orderid = orderid;
        changeSupport.firePropertyChange("orderid", oldOrderid, orderid);
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        String oldDay = this.day;
        this.day = day;
        changeSupport.firePropertyChange("day", oldDay, day);
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        String oldAccid = this.accid;
        this.accid = accid;
        changeSupport.firePropertyChange("accid", oldAccid, accid);
    }

    public Integer getNumfp() {
        return numfp;
    }

    public void setNumfp(Integer numfp) {
        Integer oldNumfp = this.numfp;
        this.numfp = numfp;
        changeSupport.firePropertyChange("numfp", oldNumfp, numfp);
    }

    public Integer getNumhp() {
        return numhp;
    }

    public void setNumhp(Integer numhp) {
        Integer oldNumhp = this.numhp;
        this.numhp = numhp;
        changeSupport.firePropertyChange("numhp", oldNumhp, numhp);
    }

    public Integer getNumm() {
        return numm;
    }

    public void setNumm(Integer numm) {
        Integer oldNumm = this.numm;
        this.numm = numm;
        changeSupport.firePropertyChange("numm", oldNumm, numm);
    }

    public Integer getNumsr() {
        return numsr;
    }

    public void setNumsr(Integer numsr) {
        Integer oldNumsr = this.numsr;
        this.numsr = numsr;
        changeSupport.firePropertyChange("numsr", oldNumsr, numsr);
    }

    public Integer getNumss() {
        return numss;
    }

    public void setNumss(Integer numss) {
        Integer oldNumss = this.numss;
        this.numss = numss;
        changeSupport.firePropertyChange("numss", oldNumss, numss);
    }

    public Integer getNumj() {
        return numj;
    }

    public void setNumj(Integer numj) {
        Integer oldNumj = this.numj;
        this.numj = numj;
        changeSupport.firePropertyChange("numj", oldNumj, numj);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "healthprac.Orders[ orderid=" + orderid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
