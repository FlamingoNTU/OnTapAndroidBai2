package vn.edu.ntu.ngocchau.ontapbai2;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import vn.edu.ntu.ngocchau.ontapbai2.controller.Icontroller;
import vn.edu.ntu.ngocchau.ontapbai2.model.contact;

public class editFragment extends Fragment {

    EditText edtid, edtname, edtbirth, edtphone, edtaddress;
    Button btnsave;
    Toolbar toolbar;

    NavController navController;
    Icontroller controller;

    int id,kt;
    contact contact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        addview(view);
        getdata();
        addEvent();
        return view;
    }

    private void addview(View view) {
        edtname = view.findViewById(R.id.edtname);
        edtid = view.findViewById(R.id.edtid);
        edtphone = view.findViewById(R.id.edtphone);
        edtbirth = view.findViewById(R.id.edtbirth);
        edtaddress = view.findViewById(R.id.edtaddress);
        btnsave = view.findViewById(R.id.btnsave);
        toolbar = view.findViewById(R.id.tbedit);

        navController = NavHostFragment.findNavController(editFragment.this);
        controller = ((MainActivity)getActivity()).controller;
    }

    private void getdata() {
        Bundle data = getArguments();
        kt = data.getInt("kt");
        id = data.getInt("id");
        if (kt == 0)
        {
            edtid.setText(String.valueOf(id));
        }
        else
        {
            edtid.setText(String.valueOf(id));
            edtname.setText(data.getString("ten"));
            edtbirth.setText(data.getString("birth"));
            edtphone.setText(data.getString("phone"));
            edtaddress.setText(data.getString("address"));
        }
    }

    private void addEvent() {
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_editFragment_to_danhsachFragment);
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact = new contact(id,edtname.getText().toString(),edtbirth.getText().toString()
                ,edtphone.getText().toString(),edtaddress.getText().toString());

                if (kt == 0)
                    controller.addcontact(contact);
                else
                    controller.edit(id,contact);
            }
        });
    }
}