package com.example.laptop_market.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laptop_market.R;
import com.example.laptop_market.contracts.IAccountContract;
import com.example.laptop_market.model.account.Account;
import com.example.laptop_market.presenter.fragments.LoginFragmentPresenter;
import com.example.laptop_market.utils.elses.FragmentActivityType;
import com.example.laptop_market.utils.elses.PreferenceManager;
import com.example.laptop_market.utils.elses.ValidateData;
import com.example.laptop_market.view.activities.ConversationListActivity;
import com.example.laptop_market.view.activities.LoginActivity;
import com.example.laptop_market.view.activities.MainActivity;
import com.example.laptop_market.view.activities.NewPostActivity;
import com.example.laptop_market.view.activities.PostDetailActivity;
import com.example.laptop_market.view.adapters.PostSearchResult.PostSearchResult;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment implements IAccountContract.View.LoginFragmentView{
    public void setLoginActivity(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public LoginActivity loginActivity;
    private Button btnLoginBack;
    private TextView txtSignUp;
    private TextView txtForgotPassword;
    private AppCompatButton bttLogin;
    private TextView txtPassword;
    private TextView txtEmail;
    private PreferenceManager preferenceManager;
    private IAccountContract.Presenter.LoginFragmentPresenter presenter;
    private TextView txtViewBug;
    private int Previous_Intent;
    public LoginFragment(LoginActivity loginActivity) {
        // Required empty public constructor
        this.loginActivity = loginActivity;

    }
    public LoginFragment() {
        // Required empty public constructor

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments != null) {
                loginActivity = arguments.getParcelable("loginActivity");
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btnLoginBack = view.findViewById(R.id.btnLoginBack);
        txtSignUp = view.findViewById(R.id.txtSignUp);
        txtForgotPassword = view.findViewById(R.id.txtForgotPassword);
        bttLogin = view.findViewById(R.id.bttLogin);
        txtPassword = view.findViewById(R.id.txtPasswordLogin);
        txtEmail = view.findViewById(R.id.txtEmailLogin);
        txtViewBug = view.findViewById(R.id.txtViewBug);
        preferenceManager = new PreferenceManager(getContext());
        presenter = new LoginFragmentPresenter(this, getContext());
        // Disable button
        bttLogin.setEnabled(false);
        bttLogin.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.gray));

        // Add event for edit text
        txtEmail.addTextChangedListener(textWatcher);
        txtPassword.addTextChangedListener(textWatcher);
        setListener();
        return view;
    }
    private void setListener()
    {
        btnLoginBack.setOnClickListener(v -> {
            loginActivity.finish();
        });

        txtSignUp.setOnClickListener(v -> {
            if(loginActivity.signUpFragment==null)
            {
                loginActivity.signUpFragment = new SignUpFragment(loginActivity);
            }
            loginActivity.replaceFragment(loginActivity.signUpFragment);
        });

        txtForgotPassword.setOnClickListener(v -> {
            if (loginActivity.forgotPasswordFragment == null){
                loginActivity.forgotPasswordFragment = new ForgotPasswordFragment(loginActivity);
            }
            loginActivity.replaceFragment(loginActivity.forgotPasswordFragment);
        });

        bttLogin.setOnClickListener(view -> {
            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();
            hidKeyboard();
            boolean isValidEmail = ValidateData.isValidEmail(email);
            if(!isValidEmail){
                Toast.makeText(getContext(), "Vui lòng kiểm tra lại địa chỉ email", Toast.LENGTH_SHORT).show();
                txtViewBug.setText("Vui lòng kiểm tra lại địa chỉ email");
            }
            else {
                Account account = new Account();
                account.setEmail(email);
                account.setPassword(password);
                presenter.LoginButtonClicked(account);
            }
        });
    }

    //region Validate data format
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Check if both email and password fields are filled
            boolean isEmailFilled = !TextUtils.isEmpty(txtEmail.getText());
            boolean isPasswordFilled = !TextUtils.isEmpty(txtPassword.getText());

            // Enable or disable the login button and change its background color
            if (isEmailFilled && isPasswordFilled) {
                bttLogin.setEnabled(true);
                bttLogin.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.yellow));
            } else {
                bttLogin.setEnabled(false);
                bttLogin.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.gray));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    //endregion

    @Override
    public void LoginSuccess() {
        preferenceManager = new PreferenceManager(getContext());
        preferenceManager.putBoolean("isLogin",true);
        Intent intent = null;
        int PreviousFragment = preferenceManager.getInt(FragmentActivityType.FRAGMENT_ACTIVITY,0);
        switch (PreviousFragment)
        {
            case FragmentActivityType.NEW_POST_ACTIVITY:
                intent = new Intent(getContext(), NewPostActivity.class);
                break;
            case FragmentActivityType.POST_DETAILS_ACTIVITY:
                PostSearchResult postSearchResult = (PostSearchResult) preferenceManager.getSerializable("PostDetailActivity");
                intent = new Intent(getContext(), PostDetailActivity.class);
                intent.putExtra("PostDetailActivity", postSearchResult);
                preferenceManager.putBoolean("IsFromLogin", true);
                break;
            case FragmentActivityType.MANAGE_BUYING_ORDER:
            case FragmentActivityType.MANAGE_SELLING_ORDER:
            case FragmentActivityType.MANAGE_POST:
                intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                loginActivity.finish();
                return;
            case FragmentActivityType.CHAT_ACTIVITY:

                break;
            case FragmentActivityType.SELL_ORDER_STATISTIC_ACTIVITY:

                break;
            case FragmentActivityType.BUY_ORDER_STATISTIC_ACTIVITY:

                break;
            case FragmentActivityType.SAVED_POST_ACTIVITY:

                break;
            case FragmentActivityType.YOUR_RATING_ACTIVITY:

                break;
            case FragmentActivityType.ACCOUNT_SETTINGS_ACTIVITY:

                break;
            default:
                intent = new Intent(getContext(), MainActivity.class);
        }
        txtViewBug.setText("success");
        preferenceManager.putInt(FragmentActivityType.FRAGMENT_ACTIVITY,0);
        startActivity(intent);
        loginActivity.finish();
    }

    @Override
    public void LoginFailed(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
        txtViewBug.setText(message);
    }

    private void hidKeyboard(){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        FirebaseAuth.getInstance().signOut();
    }
}