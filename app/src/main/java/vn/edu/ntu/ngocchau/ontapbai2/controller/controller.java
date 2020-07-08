package vn.edu.ntu.ngocchau.ontapbai2.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.ngocchau.ontapbai2.model.contact;

public class controller extends Application implements Icontroller {
    List<contact> contactList;

    public controller() {
        contactList = new ArrayList<>();
        contactList.add(new contact(1,"Tô Phước Thái","04/11/1991","12344509609","Nha Trang"));
        contactList.add(new contact(2,"Nguyễn Thái Tuấn","14/10/1999","12344509609","Nha Trang"));
        contactList.add(new contact(3,"Nguyễn Nữ Ngọc Châu","04/12/1998","12344509609","Nha Trang"));
    }

    @Override
    public List<contact> GetAllContact() {
        return contactList;
    }

    @Override
    public void addcontact(contact p) {
        contactList.add(p);
    }

    @Override
    public void edit(int id, contact p) {
        for(contact c: contactList)
        {
            if(c.getId() == id)
            {
                c.setName(p.getName());
                c.setPhone(p.getPhone());
                c.setBirth(p.getBirth());
                c.setAddress(p.getAddress());
            }
        }
    }
}
