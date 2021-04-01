package com.triillionaire.dhruv.glocals;

/**
 * Created by Dhruv on 10/3/2016.
 */

public class Train {
    private String tno,tname,tfrom,tto,tatime,tdtime,ttype;

    public Train(String tno, String tname, String tfrom, String tto, String tatime, String tdtime, String ttype) {
        this.tno = tno;
        this.tname = tname;
        this.tfrom = tfrom;
        this.tto = tto;
        this.tatime = tatime;
        this.tdtime = tdtime;
        this.ttype = ttype;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTfrom() {
        return tfrom;
    }

    public void setTfrom(String tfrom) {
        this.tfrom = tfrom;
    }

    public String getTto() {
        return tto;
    }

    public void setTto(String tto) {
        this.tto = tto;
    }

    public String getTatime() {
        return tatime;
    }

    public void setTatime(String tatime) {
        this.tatime = tatime;
    }

    public String getTdtime() {
        return tdtime;
    }

    public void setTdtime(String tdtime) {
        this.tdtime = tdtime;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }





}
