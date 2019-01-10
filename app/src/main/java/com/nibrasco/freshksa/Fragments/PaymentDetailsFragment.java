package com.nibrasco.freshksa.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nibrasco.freshksa.Model.Cart;
import com.nibrasco.freshksa.Model.Session;
import com.nibrasco.freshksa.Model.User;
import com.nibrasco.freshksa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentDetailsFragment extends Fragment {
    private TextView txtCount, txtTotal;
    TextInputEditText edtAccount;
    private Button btnConfirm, btnUpload;
    public PaymentDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(com.nibrasco.freshksa.R.layout.fragment_paymentdetails, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        final View v = getView();
        LoadContent(v);
    }

    private void LinkControls(View v){
        txtCount = (TextView)v.findViewById(com.nibrasco.freshksa.R.id.txtOrderCount);
        txtTotal = (TextView)v.findViewById(com.nibrasco.freshksa.R.id.txtOrderTotal);
        edtAccount = (TextInputEditText)v.findViewById(com.nibrasco.freshksa.R.id.edtPaymentAccount);
        btnConfirm = (Button)v.findViewById(com.nibrasco.freshksa.R.id.btnPaymentConfirm);
        btnUpload = (Button)v.findViewById(R.id.btnUpload);
    }
    private void LoadContent(View v){
        LinkControls(v);

        txtTotal.setText(Float.toString(Session.getInstance().Cart().GetTotal()));
        txtCount.setText(Integer.toString(Session.getInstance().Cart().GetCount()));

        LinkListeners(v);
    }
    private void LinkListeners(View v){
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = getResources().getString(com.nibrasco.freshksa.R.string.msgPaymentSaving);
                Snackbar snackbar = Snackbar.make(v, message, Snackbar.LENGTH_LONG);
                snackbar.show();
                String Account = edtAccount.getText().toString();

                    final Cart cart = Session.getInstance().Cart();
                    cart.setBankAccount(Account);
                    final User user = Session.getInstance().User();
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    final DatabaseReference tblCart = db.getReference("Cart");

                    tblCart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot cartSnap) {
                            String cartId = user.getCart();
                            cart.MapToDbRef(tblCart.child(cartId));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    final DatabaseReference tblUser = db.getReference("User");

                    tblUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot userSnap) {
                            String cartId = user.getCart(),
                                    usrId = user.getPhone();
                            user.AddOrder(cartId);
                            user.setCart("0");
                            user.MapToDbRef(tblUser.child(usrId));
                            Session.getInstance().User(user);
                            Session.getInstance().Cart(new Cart(cart.getAddress()));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    snackbar.dismiss();

                    CartFragment cartFragment = new CartFragment();
                    getActivity()
                            .getSupportFragmentManager().beginTransaction()
                            .replace(R.id.homeContainer, cartFragment)
                            .commit();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        Intent.createChooser(new Intent(Intent.ACTION_GET_CONTENT)
                            .setType("image/*"),
                        "Select an Image"),
                        1
                );
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        switch (requestCode){
            case 2:
                if(resultCode == getActivity().RESULT_OK){
                    StorageReference imgRef = storageRef.child(Session.getInstance().User().getCart());
                    imgRef.putFile(data.getData()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    });
                }
                break;
        }
    }
}
