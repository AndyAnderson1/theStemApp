package com.example.thestemapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private SignInButton Google;
    private GoogleSignInClient mGoogleSignInClient;
    private DatabaseReference mDatabase;

    private static User currentUser = null;
    private static Teacher currentTeacher = null;

    //Remove
    private Button skip;
    private Button admin;

    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Google = (SignInButton) findViewById(R.id.btnGoo);

        skip = (Button) findViewById(R.id.skip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherLogin();
            }
        });

        admin = (Button) findViewById(R.id.admin);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminLogin();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("573992157091-kbmq4speoh25flir1uo254i3b55dvjls.apps.googleusercontent.com").requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnGoo:
                        signIn();
                        break;
                    // ...
                }
            }
        });

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void signIn()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task <GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            //System.out.println(account.getEmail());

            //writeNewUser(account.getId(), account.getGivenName(), account.getEmail());
            // Signed in successfully, show authenticated UI.
            if(account.getEmail().indexOf("@student.aisd.net") > -1) {
                updateUI(account);
            }
            else if(account.getEmail().indexOf("@aisd.net") > -1) {
                updateTeacher(account);
            }
            else {

            }
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void writeNewUser(String userId, String name, String email) {
        //User user = new User(name, email);
        //mDatabase = FirebaseDatabase.getInstance().getReference("android");
        //Map <String, User> userMap = new HashMap <>();
        //userMap.put(email.substring(0, email.indexOf("@")), user);
        //mDatabase.child("users").setValue(userMap);
    }

    private void updateUI(GoogleSignInAccount user) {
        if (user != null) {
            currentUser = new User(user.getDisplayName());
            Intent intent = new Intent(this, StudentActivity.class);
            startActivity(intent);
        } else {
        }
    }

    private void updateTeacher(GoogleSignInAccount user) {
        if (user != null) {
            currentTeacher = new Teacher(user.getDisplayName());
            Intent intent = new Intent(this, TeacherActivity.class);
            startActivity(intent);
        } else {
        }
    }

    public void teacherLogin()
    {
        currentTeacher = new Teacher("Nicholas");
        Intent intent = new Intent(this, TeacherActivity.class);
        startActivity(intent);
    }

    public void adminLogin()
    {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    public static User getCurrentUser()
    {
        return currentUser;
    }
    public static Teacher getCurrentTeacher(){ return currentTeacher; }
}
