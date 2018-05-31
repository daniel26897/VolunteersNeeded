package g.pavi.volunteersneeded;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {


    private Button btnSignUp,btnLogin;
    private SignInButton signInButton;
    private EditText username,password;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_login, container, false);
        signInButton = (SignInButton) v.findViewById(R.id.sign_in_button);
        btnLogin = (Button) v.findViewById(R.id.btnLogin);
        btnSignUp = (Button) v.findViewById(R.id.btnSignUp);
        username = (EditText) v.findViewById(R.id.userName);
        password = (EditText) v.findViewById(R.id.password);

        //signInButton.setVisibility(View.GONE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            //ProgressDialog dialog = ProgressDialog.show(getContext(), "", "entering your account. Please wait...", true);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            username.setError("אימייל או סיסמא לא נכונים");

                        }
                    });

            }



        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mAuth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            ProgressDialog dialog = ProgressDialog.show(getContext(), "", "creating your account. Please wait...", true);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            username.setError("אימייל תפוס");
                        }
                    });

            }
        });



        return v;

    }






}
