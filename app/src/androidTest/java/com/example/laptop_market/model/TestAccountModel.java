package com.example.laptop_market.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.laptop_market.contracts.IAccountContract;
import com.example.laptop_market.model.account.Account;
import com.example.laptop_market.model.account.AccountModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RunWith(AndroidJUnit4.class)

public class TestAccountModel {
    private FirebaseAuth firebaseAuth;
    private FirebaseApp firebaseApp;
    private Context appContext;
    private IAccountContract.Model accountModel;
    private Account verifiedAccount;
    @Before
    public void setUp()
    {
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(appContext);
        firebaseAuth = FirebaseAuth.getInstance();
        accountModel = new AccountModel(appContext,firebaseAuth);
    }

    @Test
    public void testLoginSuccessful() throws ExecutionException, InterruptedException {
        Account account = new Account();
        account.setPassword("123456789  ");
        account.setEmail("nguyen.phucbinh445@gmail.com");
        CompletableFuture<Boolean> loginResult = this.loginFunctionTest(account);
        assertTrue("Login success",loginResult.get());
    }
    @Test
    public void testLoginFailed() throws ExecutionException, InterruptedException {
        Account account = new Account();
        account.setPassword("InValidPassword");
        account.setEmail("ValidEmail");
        accountModel.Login(account,(isSuccess, message) -> {
            if(isSuccess)
                Log.d("Login_success","True");
            else
                Log.d("Login_success","False");
            assertFalse(message,isSuccess);
        });
    }
    @Test
    public void testLoginAccountNotVerified() throws ExecutionException, InterruptedException {
        Account account = new Account();
        account.setPassword("InValidPassword");
        account.setEmail("ValidEmail");
        CompletableFuture<Boolean> loginResult = this.loginFunctionTest(account);
        assertFalse("Login is failed",loginResult.get());;
    }
    private CompletableFuture<Boolean> loginFunctionTest(Account account)
    {
        CompletableFuture<Boolean> result = new CompletableFuture<>();
        accountModel.Login(account,(isSuccess, message) -> {
            result.complete(isSuccess);
        });
        return result;
    }

}
