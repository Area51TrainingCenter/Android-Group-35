package pe.area51.socialapp.screens.login.viewmodel;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import pe.area51.socialapp.R;
import pe.area51.socialapp.databinding.ActivityLoginBinding;
import pe.area51.socialapp.screens.login.view.LoginAdapter;

/**
 * Created by segundo on 12/09/17.
 */

public class LoginViewModel {

    Context context;
    ActivityLoginBinding binding;
    FragmentManager support;

    LoginAdapter adapter;


    public LoginViewModel(Context context, ActivityLoginBinding binding,
                          FragmentManager support) {

        this.binding = binding;
        this.context = context;
        this.support = support;

    }

    public void initView() {

        adapter = new LoginAdapter(support);
        binding.pager.setAdapter(adapter);
        binding.pager.setCurrentItem(0);

        binding.btnlogin.setTextColor(
                context.getResources().getColor(R.color.login_title_hover)
        );

        binding.btnsignup.setTextColor(
                context.getResources().getColor(R.color.login_title_normal)
        );


        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                binding.btnlogin.setTextColor(
                        context.getResources().getColor(R.color.login_title_normal)
                );

                binding.btnsignup.setTextColor(
                        context.getResources().getColor(R.color.login_title_normal)
                );

                if (position == 0) {
                    binding.btnlogin.setTextColor(
                            context.getResources().getColor(R.color.login_title_hover)
                    );
                } else {
                    binding.btnsignup.setTextColor(
                            context.getResources().getColor(R.color.login_title_hover)
                    );
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.pager.setCurrentItem(0);
            }
        });

        binding.btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.pager.setCurrentItem(1);
            }
        });


    }


}
