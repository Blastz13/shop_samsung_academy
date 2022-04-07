package com.example.shop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView signUp;
    private FrameLayout frameLayout;

    private EditText email;
    private EditText password;
    private TextView forgotPassword;
    private ImageButton close;
    private ProgressBar progressBar;
    private Button signInBtn;

    private FirebaseAuth firebaseAuth;

    private String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_sign_in, container, false);
        signUp = view.findViewById(R.id.or_sign_up_btn);
        frameLayout = getActivity().findViewById(R.id.registration_framelayout);

        email = view.findViewById(R.id.sign_in_email);
        password = view.findViewById(R.id.sign_in_password);
        forgotPassword = view.findViewById(R.id.sign_in_forgot_password);
        close = view.findViewById(R.id.sign_in_close);
        signInBtn = view.findViewById(R.id.sign_in_btn);
        progressBar = view.findViewById(R.id.sign_in_progressbar);
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SignInFragment", "Unregistered user");
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ResetPasswordFragment());
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                validateInputs();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                validateInputs();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmailAndPassword();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void validateInputs() {
        if (!TextUtils.isEmpty(email.getText())){
            if (!TextUtils.isEmpty(password.getText())  && password.length() >= 8){
                signInBtn.setEnabled(true);
                signInBtn.setTextColor(Color.rgb(255,255,255));
            }
            else {
                signInBtn.setEnabled(false);
                signInBtn.setTextColor(Color.rgb(55,255,255));
            }
        }
        else {
            signInBtn.setEnabled(false);
            signInBtn.setTextColor(Color.rgb(55,255,255));
        }
    }
    private void validateEmailAndPassword(){
        if (email.getText().toString().matches(emailRegex) &&
            !TextUtils.isEmpty(password.getText())  && password.length() >= 8){

            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),
                                                    password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();
                            }
                            else {
                                progressBar.setVisibility(View.INVISIBLE);

                                signInBtn.setEnabled(true);
                                signInBtn.setTextColor(Color.rgb(255,255,255));

                                Toast.makeText(getActivity(), task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
        else {
            Toast.makeText(getActivity(), "Incorrect email or password",
                           Toast.LENGTH_LONG).show();
        }
    }
}