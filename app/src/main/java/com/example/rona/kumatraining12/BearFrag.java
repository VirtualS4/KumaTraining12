package com.example.rona.kumatraining12;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;
public class BearFrag extends Fragment implements View.OnClickListener,BearAdapter.OnUserClickListener{
    RecyclerView recyclerView;
    EditText edtName,edtAge,edtid;
    Button btnSubmit;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<Bear> listPersonInfo;
    Bear currentbear = new Bear();

    public BearFrag() {
// Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bear, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=getActivity();
        recyclerView = view.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        edtid = view.findViewById(R.id.edtid);
        edtName = view.findViewById(R.id.edtname);
        edtAge = view.findViewById(R.id.edtage);
        btnSubmit = view.findViewById(R.id.btnsubmit);
        btnSubmit.setOnClickListener(this);
        setupRecyclerView();
    }
    private void setupRecyclerView() {
        BearHelp db = new BearHelp(context);
        listPersonInfo = db.selectUserData();
        BearAdapter adapter = new BearAdapter(context,listPersonInfo,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnsubmit){
            BearHelp db = new BearHelp(context);

            String btnStatus=btnSubmit.getText().toString();
            if(btnStatus.equals("Submit")) {
                currentbear.setId(Integer.parseInt(edtid.getText().toString()));
                currentbear.setName(edtName.getText().toString());
                currentbear.setAge(Integer.parseInt(edtAge.getText().toString()));
                db.insert(currentbear);
            }
            if(btnStatus.equals("Update")){
//                currentbear.setId(Integer.parseInt(edtid.getText().toString()));
                currentbear.setName(edtName.getText().toString());
                currentbear.setAge(Integer.parseInt(edtAge.getText().toString()));
                db.update(currentbear);
            }
            setupRecyclerView();
            edtid.setText("");
            edtName.setText("");
            edtAge.setText("");
            edtid.setFocusable(true);
            btnSubmit.setText("Submit");
        }
    }

    @Override
    public void onUserClick(Bear currentbear, String action) {
        if(action.equals("Edit")){
            edtid.setText(currentbear.getId()+"");
            edtName.setText(currentbear.getName());
//            edtid.setFocusable(false);
            edtAge.setText(currentbear.getAge()+"");
            this.currentbear.setId(Integer.parseInt(edtid.getText().toString()));
            btnSubmit.setText("Update");
        }
        if(action.equals("Delete")){
            BearHelp db=new BearHelp(context);
            db.delete(String.valueOf(currentbear.getId()));
            setupRecyclerView();
        }
    }
}