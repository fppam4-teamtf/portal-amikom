package com.teamtf.portalamikom.fragment;

import android.animation.Animator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teamtf.portalamikom.AddEventActivity;
import com.teamtf.portalamikom.AddNewsActivity;
import com.teamtf.portalamikom.MainActivity;
import com.teamtf.portalamikom.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment{

    private SharedPreferences prefs;

    private MainActivity main;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(){
        return new AccountFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account, container, false);

        prefs = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);

        RelativeLayout relativeLayout = v.findViewById(R.id.rl_fmt_account);

        main = (MainActivity) getActivity();

        TextView tvUserId = v.findViewById(R.id.tv_userid);
        TextView tvUserName = v.findViewById(R.id.tv_nama);
//        TextView tvGender = v.findViewById(R.id.tv_gender);
//        TextView tvAddress = v.findViewById(R.id.tv_address);

        if (!prefs.getAll().isEmpty()){
            tvUserId.setText(prefs.getString("userid","value"));
            tvUserName.setText(prefs.getString("name","value"));
//            tvGender.setText(prefs.getString("gender","value"));
//            tvAddress.setText(prefs.getString("address","value"));
        }

        CardView cvAddNews = v.findViewById(R.id.cv_add_news);
        cvAddNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddNewsActivity.class);
                startActivity(i);
            }
        });

        CardView cvAddEvent = v.findViewById(R.id.cv_add_event);
        cvAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddEventActivity.class);
                startActivity(i);
            }
        });

        Button btnLogout = v.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);
                builder.setTitle("LOGOUT");
                builder.setMessage("Anda Yakin?");
                builder.setPositiveButton(getActivity().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = prefs.edit();

                        editor.clear();
                        editor.apply();

                        main.reloadFragmnet();
                    }
                });
                builder.setNegativeButton(getActivity().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

}
