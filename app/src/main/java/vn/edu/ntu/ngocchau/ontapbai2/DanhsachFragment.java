package vn.edu.ntu.ngocchau.ontapbai2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.edu.ntu.ngocchau.ontapbai2.controller.Icontroller;
import vn.edu.ntu.ngocchau.ontapbai2.model.contact;

public class DanhsachFragment extends Fragment {

    Toolbar toolbar;
    RecyclerView recyclerView;

    NavController navController;
    Icontroller controller;

    List<contact> contactList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danhsach, container, false);
        addview(view);
        getdata();
        addEvent();
        return view;
    }

    private void addview(View view) {
        toolbar = view.findViewById(R.id.tbds);
        toolbar.inflateMenu(R.menu.my_menu);
        recyclerView = view.findViewById(R.id.rcvlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachFragment.this.getActivity()));

        navController = NavHostFragment.findNavController(DanhsachFragment.this);
        controller = ((MainActivity)getActivity()).controller;
    }

    private void getdata() {
        contactList = controller.GetAllContact();
        ContactAdapter adapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(adapter);
    }

    private void addEvent() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Bundle data = new Bundle();
                data.putInt("kt", 0);
                data.putInt("id", contactList.size() + 1);
                navController.navigate(R.id.action_danhsachFragment_to_editFragment,data);
                return false;
            }
        });
    }

    private class ContactViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtten, txtbirth, txtphone;
        ImageView imgedit;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtten);
            txtbirth = itemView.findViewById(R.id.txtbirth);
            txtphone = itemView.findViewById(R.id.txtphone);
            imgedit = itemView.findViewById(R.id.imgedit);
        }

        public void bind(contact p)
        {
            txtten.setText(p.getName());
            txtbirth.setText(p.getBirth());
            txtphone.setText(p.getPhone());
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>
    {
        List<contact> listContacts;

        public ContactAdapter(List<contact> listProducts) {
            this.listContacts = listProducts;
        }


        @NonNull
        @Override
        public DanhsachFragment.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.contact, parent, false);
            return new ContactViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull DanhsachFragment.ContactViewHolder holder, final int position) {
            holder.bind(listContacts.get(position));
            holder.imgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle data = new Bundle();
                    data.putInt("kt", 1);
                    data.putInt("id", listContacts.get(position).getId());
                    data.putString("ten",listContacts.get(position).getName());
                    data.putString("birth",listContacts.get(position).getBirth());
                    data.putString("phone",listContacts.get(position).getPhone());
                    data.putString("address",listContacts.get(position).getAddress());
                    navController.navigate(R.id.action_danhsachFragment_to_editFragment,data);
                }
            });
        }

        @Override
        public int getItemCount() {
            return listContacts.size();
        }
    }
}