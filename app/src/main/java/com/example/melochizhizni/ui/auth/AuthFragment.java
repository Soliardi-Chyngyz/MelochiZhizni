package com.example.melochizhizni.ui.auth;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.melochizhizni.MainActivity;
import com.example.melochizhizni.Prefs;
import com.example.melochizhizni.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

import java.util.concurrent.TimeUnit;

public class AuthFragment extends Fragment {


    private TextView codeCountry, massage;
    private EditText edName, edPhone,
            edSmsCode;
    private Button btnContinue, btnConfirm, btnRepeat;
    private OnVerificationStateChangedCallbacks callbacks;
    private String verificationId;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliSeconds = 60000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initCallBacks();

        btnContinue.setOnClickListener(v -> {
            requestSms();
        });

        exitOnBackClicked();

        btnConfirm.setOnClickListener(v -> {
            if (!edSmsCode.getText().toString().isEmpty()) {
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                        verificationId, edSmsCode.getText().toString().trim());
                signIn(phoneAuthCredential);
            }
        });
    }

    private void startTimer() {
        timeLeftInMilliSeconds = 60000;
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliSeconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                massage.setText("Если смс-код не пришел, проверьте соединение, номер и повторите заново");
                btnConfirm.setText("Подтвердить");
                massage.setVisibility(View.VISIBLE);
                edSmsCode.setVisibility(View.VISIBLE);
                codeCountry.setVisibility(View.GONE);
                edPhone.setVisibility(View.GONE);
                btnRepeat.setVisibility(View.VISIBLE);
                btnRepeat.setOnClickListener(v -> {
                    requestSms();
                    edSmsCode.setVisibility(View.GONE);
                    btnRepeat.setVisibility(View.GONE);
                    btnConfirm.setVisibility(View.GONE);
                    codeCountry.setVisibility(View.VISIBLE);
                    edPhone.setVisibility(View.VISIBLE);
                    btnContinue.setVisibility(View.VISIBLE);
                });
            }
        }.start();
    }

    private void updateTimer() {
        int seconds = (int) (timeLeftInMilliSeconds % 60000 / 1000);
        btnConfirm.setText(String.valueOf(seconds));
    }

    private void exitOnBackClicked() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    private void initCallBacks() {
        callbacks = new OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signIn(phoneAuthCredential);
                String smsCode = phoneAuthCredential.getSmsCode();
                if (smsCode != null)
                    edSmsCode.setText(smsCode);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                e.printStackTrace();
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
                Toast.makeText(getContext(), "Код отправлен!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        // создаем и авторизируемся
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigateUp();
            } else {
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestSms() {
        btnRepeat.setVisibility(View.GONE);
        String name = edName.getText().toString();
        String phoneNumber = "+996" + edPhone.getText().toString().trim();
        Prefs.instance.saveNumber(phoneNumber);
        if (phoneNumber.length() == 13 && !name.isEmpty() && name.length() >= 3) {
            smsSend(phoneNumber);
        } else if (phoneNumber.length() != 13) {
            yoyo(edPhone);
        } else if (name.isEmpty()) {
            yoyo(edName);
            edName.setError("Это поле не должно быть пустым");
        } else if (name.length() < 3) {
            yoyo(edName);
            edName.setError("Не так коротко");
        }
    }

    private void smsSend(String phoneNumber) {
        startTimer();
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder()
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(callbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        codeCountry.setVisibility(View.GONE);
        edName.setVisibility(View.GONE);
        massage.setVisibility(View.GONE);
        edPhone.setVisibility(View.GONE);
        btnContinue.setVisibility(View.GONE);
        edSmsCode.setVisibility(View.VISIBLE);
        btnConfirm.setVisibility(View.VISIBLE);
    }

    private void yoyo(View view) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(1)
                .playOn(view);
    }

    private void init(View view) {
        massage = view.findViewById(R.id.massage);
        codeCountry = view.findViewById(R.id.code_country);
        edName = view.findViewById(R.id.edit_name);
        edPhone = view.findViewById(R.id.edit_phone);
        btnContinue = view.findViewById(R.id.btn_continue);
        edSmsCode = view.findViewById(R.id.edit_confirm_phone_code);
        btnConfirm = view.findViewById(R.id.btn_confirm);
        btnRepeat = view.findViewById(R.id.btn_repeat);
    }
}