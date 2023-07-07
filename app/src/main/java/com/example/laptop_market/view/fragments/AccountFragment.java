package com.example.laptop_market.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.laptop_market.R;
import com.example.laptop_market.contracts.IAccountContract;
import com.example.laptop_market.contracts.IFragmentListener;
import com.example.laptop_market.model.account.Account;
import com.example.laptop_market.presenter.fragments.AccountFragmentPresenter;
import com.example.laptop_market.utils.elses.FragmentActivityType;
import com.example.laptop_market.utils.MyDialog;
import com.example.laptop_market.utils.elses.PreferenceManager;
import com.example.laptop_market.view.activities.AccountSettingActivity;
import com.example.laptop_market.view.activities.LoginActivity;
import com.example.laptop_market.view.activities.MainActivity;
import com.example.laptop_market.view.activities.ProfileActivity;
import com.google.android.material.snackbar.Snackbar;


public class AccountFragment extends Fragment implements IAccountContract.View.AccountFragmentView {
    private ImageView imgAccount;
    private TextView txtAccountName;
    private IAccountContract.Presenter.AccountFragmentPresenter accountFragmentPresenter;
    private AppCompatButton bttLogout, btnSellOrder, btnBuyOrder, btnSavedPost, btnYourRating, btnAccountSettings, btnFeedback;
    private PreferenceManager preferenceManager;
    private IFragmentListener fragmentListener;
    private boolean isLogin = false;
    private static final int REQUEST_CODE_TRANSITION = 1;
    private boolean applySlideTransition = false;
    public AccountFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        preferenceManager = new PreferenceManager(getContext());
        imgAccount = view.findViewById(R.id.imgAccount);
        txtAccountName = view.findViewById(R.id.txtAccountName);
        btnSellOrder = view.findViewById(R.id.btnSellOrder);
        btnBuyOrder = view.findViewById(R.id.btnBuyOrder);
        btnSavedPost = view.findViewById(R.id.btnSavedPostList);
        btnYourRating = view.findViewById(R.id.btnYourRating);
        btnAccountSettings = view.findViewById(R.id.btnAccountSettings);
        btnFeedback = view.findViewById(R.id.btnFeedback);
        bttLogout = view.findViewById(R.id.bttLogout);
        accountFragmentPresenter = new AccountFragmentPresenter(this, getContext());
        setListener();
        return view;
    }

    private void setListener()
    {
        Glide.with(this)
                .load(R.drawable.slide_show1)
                .apply(RequestOptions.circleCropTransform())
                .into(imgAccount);
        txtAccountName.setOnClickListener(v -> {
            if(!isLogin) {
                Intent intent = new Intent(this.getActivity(), LoginActivity.class);
                PreferenceManager preferenceManager = new PreferenceManager(getContext());
                preferenceManager.putInt(FragmentActivityType.FRAGMENT_ACTIVITY, FragmentActivityType.ACCOUNT_FRAGMENT);
                startActivity(intent);
            }
            else {
//                accountBaseFragment.profileFragment = new ProfileFragment(accountBaseFragment);
//                accountBaseFragment.replaceFragment(accountBaseFragment.profileFragment);
                Intent intent = new Intent(this.getActivity(), ProfileActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getActivity(), R.anim.slide_in_right, R.anim.slide_out_left).toBundle();
                startActivityForResult(intent,REQUEST_CODE_TRANSITION, bundle);
            }
        });
        btnSellOrder.setOnClickListener(view -> {
            accountFragmentPresenter.ClickSellOrderStatistic();
        });
        btnBuyOrder.setOnClickListener(view -> {
            accountFragmentPresenter.ClickBuyOrderStatistic();
        });
        btnSavedPost.setOnClickListener(view -> {
            accountFragmentPresenter.ClickSavedPost();
        });
        btnYourRating.setOnClickListener(view -> {
            accountFragmentPresenter.ClickYourRating();
        });
        btnAccountSettings.setOnClickListener(view -> {
            accountFragmentPresenter.ClickAccountSettings();
        });
        btnFeedback.setOnClickListener(view -> {
            // Create feedback activity
        });
        bttLogout.setOnClickListener(view -> {
            MyDialog.showDialog(getContext(), "Bạn có chắc muốn đăng xuất không?", MyDialog.DialogType.YES_NO, new MyDialog.DialogClickListener() {
                @Override
                public void onYesClick() {
                    accountFragmentPresenter.LogoutAccount();
                    if (fragmentListener != null)
                        fragmentListener.OnLogoutListener();
                }

                @Override
                public void onNoClick() {
                    // Do nothing
                }

                @Override
                public void onOkClick() {
                    // Do nothing
                }
            });
        });
    }
    public void ShowToast(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
    @Override
    public void LoadAccount(Account account) {
        txtAccountName.setText(account.getAccountName());
        bttLogout.setVisibility(View.VISIBLE);
        isLogin=true;
    }

    @Override
    public void LogoutAccount() {
        ShowToast("Đăng xuất thành công");
        txtAccountName.setText("Đăng nhập / Đăng ký");
        bttLogout.setVisibility(View.GONE);
        isLogin = false;
    }

    @Override
    public void LoadNotLoginAccount() {
        txtAccountName.setText("Đăng nhập / Đăng ký");
        bttLogout.setVisibility(View.GONE);
        isLogin = false;
    }

    @Override
    public void LoginAccount() {
        Intent intent = new Intent(this.getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void LoadSellOrder() {

    }

    @Override
    public void LoadBuyOrder() {

    }

    @Override
    public void LoadSavedPost() {

    }

    @Override
    public void LoadYourRating() {

    }

    @Override
    public void LoadAccountSettings() {
        Intent intent = new Intent(getActivity(), AccountSettingActivity.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getActivity(), R.anim.slide_in_right, R.anim.slide_out_left).toBundle();
        startActivityForResult(intent,REQUEST_CODE_TRANSITION,bundle);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            boolean shouldApplySlideTransition = data.getBooleanExtra("applySlideTransition", false);
            if (shouldApplySlideTransition) {
                applySlideTransition = true;
            }
            else{
                applySlideTransition = false;
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof MainActivity && applySlideTransition) {
            MainActivity activity = (MainActivity) getActivity();
            activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        accountFragmentPresenter.LoadAccountStatus();
    }

    public void setFragmentListener(IFragmentListener listener){
        this.fragmentListener = listener;
    }
}